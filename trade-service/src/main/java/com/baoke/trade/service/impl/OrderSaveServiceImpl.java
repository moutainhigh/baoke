package com.baoke.trade.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.domain.result.Result;
import com.baoke.common.dto.api.OrderAddressDto;
import com.baoke.common.dto.api.OrderItemNumDto;
import com.baoke.common.dto.api.OrderPayDto;
import com.baoke.common.dto.api.OrderSubmitDto;
import com.baoke.common.dto.info.AddressInfoDto;
import com.baoke.common.dto.info.OrderInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.MoneyUtil;
import com.baoke.common.util.OrderNoUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.item.constant.ItemDetailStatusEnum;
import com.baoke.item.constant.ItemStatusEnum;
import com.baoke.item.domain.ItemDetailInfo;
import com.baoke.item.domain.ItemInfo;
import com.baoke.item.manager.ItemDetailInfoManager;
import com.baoke.item.manager.ItemInfoManager;
import com.baoke.trade.constant.IsShoppingCartEnum;
import com.baoke.trade.constant.OrderPayStatusEnum;
import com.baoke.trade.constant.OrderPayTypeEnum;
import com.baoke.trade.constant.OrderStatusEnum;
import com.baoke.trade.constant.SaveOrderErrorType;
import com.baoke.trade.domain.Order;
import com.baoke.trade.domain.OrderItem;
import com.baoke.trade.domain.OrderPay;
import com.baoke.trade.domain.OrderShoppingCart;
import com.baoke.trade.manager.OrderItemManager;
import com.baoke.trade.manager.OrderManager;
import com.baoke.trade.manager.OrderPayManager;
import com.baoke.trade.manager.OrderShoppingCartManager;
import com.baoke.trade.service.OrderSaveService;
import com.baoke.trade.service.OrderService;
import com.baoke.user.domain.AreaDictInfo;
import com.baoke.user.domain.UserAddress;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.AreaDictManager;
import com.baoke.user.manager.UserAddressManager;
import com.baoke.user.manager.UserInfoManager;

/**
 * 订单保存实现
 *
 * @author zjj
 * @date 2018年7月4日 上午11:40:18
 */
@ServiceDefinition(value = "orderSaveService")
@Service("orderSaveService")
public class OrderSaveServiceImpl implements OrderSaveService {

	private static final Logger logger = Logger.getLogger(OrderSaveServiceImpl.class);

	@Resource
	PlatformTransactionManager transactionManager;
	@Resource
	DefaultTransactionDefinition transactionDefinition;
	@Resource
	UserInfoManager userInfoManager;
	@Resource
	UserAddressManager userAddressManager;
	@Resource
	OrderItemManager orderItemManager;
	@Resource
	OrderShoppingCartManager orderShoppingCartManager;
	@Resource
	OrderPayManager orderPayManager;
	@Resource
	OrderManager orderManager;
	@Resource
	ItemInfoManager itemInfoManager;
	@Resource
	ItemDetailInfoManager itemDetailInfoManager;
	@Resource
	OrderService orderService;
	@Resource
	AreaDictManager areaDictManager;

	@Override
	@MethodDefinition(value = "saveMyOrder", needLogin = true)
	public OrderPayDto saveMyOrder(OrderSubmitDto orderSubmitDto) throws MainException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(orderSubmitDto);
		Integer isShoppingCart = orderSubmitDto.getIsShoppingCart();
		if (!IsShoppingCartEnum.isExist(isShoppingCart)) {
			throw new ParamInvalidException("是否来自购物车标识只能为[0:否;1:是]");
		}

		UserAddress defaultAddress = userAddressManager.queryDefaultAddressByUserId(userInfo.getId(), BooleanEnum.TRUE);
		if (null == defaultAddress) {
			logger.error("saveMyOrder error, default address is null, userId=" + userInfo.getId());
			return new OrderPayDto(false, "请先设置默认地址", SaveOrderErrorType.NO_ADDRESS.getCode());
		}
		AreaDictInfo provinceInfo = areaDictManager.queryAddressByCode(defaultAddress.getProvince(), BooleanEnum.TRUE);
		AreaDictInfo cityInfo = areaDictManager.queryAddressByCode(defaultAddress.getCity(), BooleanEnum.TRUE);
		AreaDictInfo areaInfo = areaDictManager.queryAddressByCode(defaultAddress.getArea(), BooleanEnum.TRUE);
		if (null == provinceInfo || null == cityInfo || null == areaInfo) {
			return new OrderPayDto(false, "地址信息有误", SaveOrderErrorType.NO_ADDRESS.getCode());
		}
		AddressInfoDto addressInfoDto = defaultAddress.convert(provinceInfo, cityInfo, areaInfo);

		String parentOrderNo = OrderNoUtil.getOrderNo(OrderNoUtil.PARENT_ORDER_PREFIX);

		// 子订单编号map, key为sellerId, value为subOrderNo
		Map<Long, String> subOrderNoMap = new HashMap<Long, String>();

		// 订单map, key为subOrderNo，value为order对象
		Map<String, Order> orderMap = new HashMap<>();

		Result result = null;
		OrderPay orderPay = new OrderPay(userInfo.getId(), parentOrderNo, isShoppingCart,
				OrderPayStatusEnum.WAIT.getCode(), OrderPayTypeEnum.WECHATPAY.getCode());
		List<OrderItem> orderItemList = new ArrayList<>();
		StringBuffer sb = new StringBuffer("星球-");

		if (isShoppingCart.equals(IsShoppingCartEnum.YES.getCode())) { /** 来自购物车 */
			String shoppingCartIds = orderSubmitDto.getShoppingCartIds();
			if (StringUtil.isBlank(shoppingCartIds)) {
				logger.error("saveMyOrder error, shoppingCartIds is null, orderSubmitDto=" + orderSubmitDto);
				throw new ParamInvalidException("订单来自购物时，购物车ID不可为空");
			}
			for (String cartId : shoppingCartIds.split(",")) {
				cartId = cartId.trim();
				if (!StringUtil.isPositiveNumber(cartId)) {
					logger.error("saveMyOrder error, cartId is not valid, orderSubmitDto=" + orderSubmitDto);
					throw new ParamInvalidException("购物车ID必须是数字");
				}
				OrderShoppingCart shoppingCart = orderShoppingCartManager.queryShoppingCartById(Long.valueOf(cartId));
				if (null == shoppingCart) {
					logger.error(
							"saveMyOrder error, shoppingCart is null, userId=" + userInfo.getId() + "cartId=" + cartId);
					return new OrderPayDto(false, "编号为" + cartId + "的购物车信息无法获取，请刷新重试！",
							SaveOrderErrorType.NORMAL.getCode());
				}
				OrderItem orderItem = new OrderItem(userInfo.getId(), parentOrderNo, shoppingCart.getItemId(),
						shoppingCart.getItemDetailId());
				// 生成子订单
				result = createOrderItem(orderItem, shoppingCart.getNum(), subOrderNoMap, orderMap, addressInfoDto);
				if (!result.isSuccess()) {
					return new OrderPayDto(false, result.getMessage(),
							((SaveOrderErrorType) result.getBody()).getCode());
				}
				// 构建orderMap集合
				orderMap = buildOrderMap(orderItem, addressInfoDto, orderMap);

				sb.append(orderItem.getItemTitle()).append(",");
				orderItemList.add(orderItem);
			}
		} else { /** 来自商品详情页 */
			Long itemId = orderSubmitDto.getItemId();
			Long itemDetailId = orderSubmitDto.getItemDetailId();
			Integer num = orderSubmitDto.getNum();
			if (null == itemId || null == itemDetailId || null == num) {
				logger.error("saveMyOrder error, itemId or itemDetailId or num is null, userId=" + userInfo.getId()
						+ ",itemId=" + itemId + ",itemDetailId=" + itemDetailId + ",num=" + num);
				throw new ParamInvalidException("当订单来自商品详情页时，商品编号、商品详情编号和购买数量都不能为空！");
			}
			OrderItem orderItem = new OrderItem(userInfo.getId(), parentOrderNo, itemId, itemDetailId);
			// 生成子订单
			result = createOrderItem(orderItem, num, subOrderNoMap, orderMap, addressInfoDto);
			if (!result.isSuccess()) {
				return new OrderPayDto(false, result.getMessage(), ((SaveOrderErrorType) result.getBody()).getCode());
			}
			// 构建orderMap集合
			orderMap = buildOrderMap(orderItem, addressInfoDto, orderMap);

			sb.append(orderItem.getItemTitle());
			orderItemList.add(orderItem);
		}

		String payDesc = sb.toString();
		if (payDesc.lastIndexOf(",") != -1) {
			payDesc = payDesc.substring(0, payDesc.lastIndexOf(",")).toString();
		}

		List<Order> orderList = new ArrayList<>(orderMap.values());
		Integer totalPrice = 0;
		Integer totalPostage = 0;
		for (Order order : orderList) {
			totalPrice += order.getTotalItemPrice();
			totalPostage += order.getTotalPostage();
		}

		// 生成父订单
		orderPay = createOrderPay(orderPay, addressInfoDto, parentOrderNo, totalPrice, totalPostage, payDesc);

		// 订单状态集合,key为子订单orderNo,value为子订单状态值
		Map<String, Integer> orderStatusMap = new HashMap<>();
		for (Order order : orderList) {
			orderStatusMap.put(order.getOrderNo(), order.getStatus());
		}

		/** 保存父订单、子订单 、订单 */
		TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
		try {
			orderPayManager.addOrderPay(orderPay);
			orderManager.addBatchOrder(orderList);
			orderItemManager.addBatchOrderItem(orderItemList);
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			logger.error("saveMyOrder error, save data to database error, userId = " + userInfo.getId(), e);
			return new OrderPayDto(false, "网络超时，请稍后重试", SaveOrderErrorType.NORMAL.getCode());
		}

		return buildOrderPayDto(parentOrderNo, totalPrice, totalPostage, addressInfoDto, isShoppingCart,
				orderStatusMap);
	}

	private OrderPayDto buildOrderPayDto(String parentOrderNo, Integer totalPrice, Integer totalPostage,
			AddressInfoDto addressInfoDto, Integer isShoppingCart, Map<String, Integer> orderStatusMap) {
		OrderPayDto orderPayDto = new OrderPayDto(true, "", SaveOrderErrorType.NORMAL.getCode());
		orderPayDto.setParentOrderNo(parentOrderNo);
		orderPayDto.setTotalPrice(MoneyUtil.changeF2Y(totalPrice + totalPostage));
		orderPayDto.setPayType(OrderPayTypeEnum.WECHATPAY.getCode());
		orderPayDto.setAddressInfo(addressInfoDto);
		orderPayDto.setShoppingCart(isShoppingCart.equals(IsShoppingCartEnum.YES.getCode()));

		List<OrderItem> orderItems = orderItemManager.queryOrderItemByParentOrderNo(parentOrderNo);
		List<OrderInfoDto> orderInfoDtoList = orderService.buildOrderInfoDtoList(orderItems, orderStatusMap);
		orderPayDto.setOrderList(orderInfoDtoList);
		return orderPayDto;
	}

	/**
	 * 生成子订单
	 * 
	 * @author zjj
	 * @date 2018年7月1日 下午2:36:29
	 */
	private Result createOrderItem(OrderItem orderItem, int num, Map<Long, String> subOrderNoMap,
			Map<String, Order> orderMap, AddressInfoDto addressInfoDto) {
		ItemInfo itemInfo = itemInfoManager.queryItemInfoByIdWithStatus(orderItem.getItemId(), ItemStatusEnum.ONLINE);
		if (null == itemInfo) {
			logger.warn("create subOrder error, itemInfo is null, itemId=" + orderItem.getItemId() + ",itemDetailId="
					+ orderItem.getItemDetailId());
			return new Result(false, "商品ID为" + orderItem.getItemId() + "的商品已下架，请刷新重试！", SaveOrderErrorType.NORMAL);
		}
		ItemDetailInfo itemDetailInfo = itemDetailInfoManager.queryItemDetailInfoById(orderItem.getItemDetailId());
		if (null == itemDetailInfo) {
			logger.warn("create subOrder error, itemDetailInfo is null, itemId=" + orderItem.getItemId()
					+ ",itemDetailId=" + orderItem.getItemDetailId());
			return new Result(false, "商品明细ID为" + orderItem.getItemDetailId() + "的商品已下架，请刷新重试！",
					SaveOrderErrorType.NORMAL);
		}
		if (itemDetailInfo.getTotalNum() == null || itemDetailInfo.getTotalNum() < num) {
			logger.warn("create subOrder error, item is not enough, itemId=" + orderItem.getItemId() + ",itemDetailId="
					+ orderItem.getItemDetailId() + ",num=" + num);
			return new Result(false, "商品【" + itemInfo.getTitle() + "】库存不足！", SaveOrderErrorType.ITEM_NOT_ENOUGH);
		}
		if (itemDetailInfo.getStatus() != ItemDetailStatusEnum.ENABLE.getCode()) {
			logger.warn("create subOrder error, itemDetailInfo status not support, itemId=" + orderItem.getItemId()
					+ ",itemDetailId=" + orderItem.getItemDetailId() + ",num=" + num + ",itemDetail status="
					+ ItemStatusEnum.getByCode(itemDetailInfo.getStatus()));
			return new Result(false,
					"商品【" + itemInfo.getTitle() + "】状态为"
							+ ItemStatusEnum.getByCode(itemDetailInfo.getStatus()).getName() + ",请刷新重试",
					SaveOrderErrorType.NORMAL);
		}

		orderItem.setSellerId(itemInfo.getSellerId());
		String subOrderNo = subOrderNoMap.get(itemInfo.getSellerId());
		if (StringUtil.isBlank(subOrderNo)) {
			subOrderNo = OrderNoUtil.getOrderNo(OrderNoUtil.SUB_ORDER_PREFIX);
			subOrderNoMap.put(itemInfo.getSellerId(), subOrderNo);
		}
		orderItem.setOrderNo(subOrderNo);
		orderItem.setItemId(itemInfo.getId());
		orderItem.setItemDetailId(itemDetailInfo.getId());
		orderItem.setItemTitle(itemInfo.getTitle());
		orderItem.setItemImgUrl(itemInfo.getMainImgUrl());
		orderItem.setItemAttr1Code(itemDetailInfo.getAttr1());
		orderItem.setItemAttr1Name("通用");
		orderItem.setItemAttr2Code(itemDetailInfo.getAttr2());
		orderItem.setItemAttr2Name("通用");
		orderItem.setItemTotalNum(num);
		orderItem.setItemPrice(itemDetailInfo.getPrice());
		orderItem.setItemTotalPrice(itemDetailInfo.getPrice() * num);
		orderItem.setPostage(getItemPostage(addressInfoDto.getAddressId(), orderItem.getItemId()));

		return new Result(true, "");
	}

	/**
	 * 构建orderMap集合
	 * 
	 * @author zjj
	 * @date 2018年7月3日 下午2:39:59
	 */
	private Map<String, Order> buildOrderMap(OrderItem orderItem, AddressInfoDto addressInfoDto,
			Map<String, Order> orderMap) {
		Order order = orderMap.get(orderItem.getOrderNo());
		if (null == order) {
			order = new Order(addressInfoDto.getUserId(), orderItem.getSellerId(), orderItem.getParentOrderNo(),
					orderItem.getOrderNo(), addressInfoDto.getAddressId(), addressInfoDto.getName(),
					addressInfoDto.getPhone(), OrderStatusEnum.WAIT_PAY.getCode(), 0, 0, orderItem.getPostage());
			order.setAddressDetail(addressInfoDto.getProvince(), addressInfoDto.getCity(), addressInfoDto.getArea(),
					addressInfoDto.getAddress());
		}
		order.setTotalItemPrice(order.getTotalItemPrice() + orderItem.getItemTotalPrice());
		order.setTotalItemNum(order.getTotalItemNum() + orderItem.getItemTotalNum());
		order.setTotalPostage(Math.min(order.getTotalPostage(), orderItem.getPostage())); // 多个商品，总运费取最小值
		if (!orderMap.containsKey(orderItem.getOrderNo())) {
			orderMap.put(orderItem.getOrderNo(), order);
		}
		return orderMap;
	}

	/**
	 * 根据收货区域获取单个商品运费
	 * 
	 * @author zjj
	 * @date 2018年7月6日 下午4:18:12
	 */
	private Integer getItemPostage(Long adderssId, Long itemId) {
		ItemInfo itemInfo = itemInfoManager.queryItemInfoById(itemId);
		if (null == itemInfo) {
			return 0;
		}
		String areaCodes = itemInfo.getAddAreaCodes();
		if (StringUtil.isBlank(areaCodes)) {
			return itemInfo.getPostage();
		}

		UserAddress userAddress = userAddressManager.queryUserAddressById(adderssId, BooleanEnum.TRUE);
		if (null == userAddress) {
			return 0;
		}
		String userAddressProvinceCode = userAddress.getProvince();

		List<String> codeList = Arrays.asList(areaCodes.split(";"));
		if (codeList.contains(userAddressProvinceCode)) {
			return itemInfo.getAddAreaPostage();
		}

		return itemInfo.getPostage();
	}

	/**
	 * 生成父订单
	 * 
	 * @author zjj
	 * @date 2018年7月1日 下午3:09:29
	 */
	private OrderPay createOrderPay(OrderPay orderPay, AddressInfoDto addressInfoDto, String parentOrderNo,
			Integer totalPrice, Integer totalPostage, String payDesc) {
		orderPay.setTotalPrice(totalPrice);
		orderPay.setTotalPostage(totalPostage);
		orderPay.setPayDesc(payDesc);
		orderPay.setAddressId(addressInfoDto.getAddressId());
		orderPay.setAddressName(addressInfoDto.getName());
		orderPay.setAddressPhone(addressInfoDto.getPhone());
		orderPay.setAddressDetail(addressInfoDto.getProvince(), addressInfoDto.getCity(), addressInfoDto.getArea(),
				addressInfoDto.getAddress());
		return orderPay;
	}

	@Override
	@MethodDefinition(value = "saveMyOrderAddress", needLogin = true)
	public OrderPayDto saveMyOrderAddress(OrderAddressDto orderAddressDto) throws MainException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(orderAddressDto);
		Integer payType = orderAddressDto.getPayType();
		if (!OrderPayTypeEnum.isExist(payType)) {
			logger.error("saveMyOrderAddress error, payType not vaild, userId = " + userInfo.getId() + " ,payType = "
					+ payType);
			throw new ParamInvalidException("支付方式只支持微信和支付宝！");
		}
		String parentOrderNo = orderAddressDto.getParentOrderNo();
		if (StringUtil.isBlank(parentOrderNo)) {
			throw new ParamInvalidException("订单编号不能为空！");
		}
		OrderPay orderPay = orderPayManager.queryOrderPayByParentOrderNo(parentOrderNo);
		if (null == orderPay) {
			logger.error("saveMyOrderAddress error, order is null, userId = " + userInfo.getId() + " ,parentOrderNo = "
					+ parentOrderNo + " ,payType = " + payType);
			return new OrderPayDto(false, "无法找到订单信息，请刷新重试！");
		}
		Long addressId = orderAddressDto.getAddressId();
		if (null == addressId) {
			throw new ParamInvalidException("地址ID不能为空！");
		}
		UserAddress userAddress = userAddressManager.queryUserAddressById(addressId, BooleanEnum.TRUE);
		if (null == userAddress) {
			logger.error("saveMyOrderAddress error, order is null, userId = " + userInfo.getId() + " ,parentOrderNo = "
					+ parentOrderNo + " ,payType = " + payType + ", addressId = " + addressId);
			return new OrderPayDto(false, "无法找到地址信息，请刷新重试！");
		}
		AreaDictInfo provinceInfo = areaDictManager.queryAddressByCode(userAddress.getProvince(), BooleanEnum.TRUE);
		AreaDictInfo cityInfo = areaDictManager.queryAddressByCode(userAddress.getCity(), BooleanEnum.TRUE);
		AreaDictInfo areaInfo = areaDictManager.queryAddressByCode(userAddress.getArea(), BooleanEnum.TRUE);
		if (null == provinceInfo || null == cityInfo || null == areaInfo) {
			return new OrderPayDto(false, "地址信息有误，请重新设置地址");
		}
		AddressInfoDto addressInfoDto = userAddress.convert(provinceInfo, cityInfo, areaInfo);

		if (!userInfo.getId().equals(userAddress.getUserId())) {
			logger.error("saveMyOrderAddress error, userAddress is match to userInfo, userId = " + userInfo.getId()
					+ " ,parentOrderNo = " + parentOrderNo + " ,payType = " + payType + ", addressId = " + addressId);
			return new OrderPayDto(false, "地址信息与用户信息不匹配！");
		}

		// 订单邮费集合,key为订单orderNo,value为订单postage
		Map<String, Integer> orderPostageMap = new HashMap<>();
		// 订单状态集合,key为订单orderNo,value为订单状态值
		Map<String, Integer> orderStatusMap = new HashMap<>();
		Integer totalPostage = 0;

		// 重设orderItem的postage
		List<OrderItem> orderItemList = orderItemManager.queryOrderItemByParentOrderNo(parentOrderNo);
		for (OrderItem orderItem : orderItemList) {
			Integer postage = getItemPostage(addressId, orderItem.getItemId());
			orderItem.setPostage(postage);
			resetOrderPostage(orderItem.getOrderNo(), postage, orderPostageMap);
		}

		// 重设order的totalPostage和地址信息
		List<Order> orderList = orderManager.queryOrderByParentOrderNo(parentOrderNo);
		for (Order order : orderList) {
			orderStatusMap.put(order.getOrderNo(), order.getStatus()); // 订单状态
			Integer orderPostage = orderPostageMap.get(order.getOrderNo()) == null ? 0
					: orderPostageMap.get(order.getOrderNo());
			order.setTotalItemPrice(orderPostage);
			order.setAddressId(addressId);
			order.setAddressName(userAddress.getName());
			order.setAddressPhone(userAddress.getPhone());
			order.setAddressDetail(userAddress.getProvince(), userAddress.getCity(), userAddress.getArea(),
					userAddress.getAddress());
			totalPostage += orderPostage;
		}

		// 重设orderPay的支付方式、totalPostage和地址信息
		orderPay.setPayType(payType);
		orderPay.setTotalPostage(totalPostage);
		orderPay.setAddressId(addressId);
		orderPay.setAddressName(userAddress.getName());
		orderPay.setAddressPhone(userAddress.getPhone());
		orderPay.setAddressDetail(userAddress.getProvince(), userAddress.getCity(), userAddress.getArea(),
				userAddress.getAddress());

		// 修改orderPay order表
		TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
		try {
			orderPayManager.modifyOrderPayTypeAndAddressAndPostage(orderPay);
			orderManager.modifyBatchOrderPostageAndAddress(orderList);
			orderItemManager.modifyBatchOrderItemPostage(orderItemList);
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			logger.error("saveMyOrderAddress error, sava data to database error,userId=" + userInfo.getId(), e);
			return new OrderPayDto(false, "网络超时，请稍后重试");
		}

		return buildOrderPayDto(parentOrderNo, orderPay.getTotalPrice(), orderPay.getTotalPostage(), addressInfoDto,
				orderPay.getFromShoppingCart(), orderStatusMap);
	}

	private void resetOrderPostage(String orderNo, Integer postage, Map<String, Integer> orderPostageMap) {
		Integer oldPostage = orderPostageMap.get(orderNo);
		if (null == oldPostage) {
			orderPostageMap.put(orderNo, postage);
		} else {
			orderPostageMap.put(orderNo, Math.min(oldPostage, postage));
		}
	}

	@MethodDefinition(value = "saveMyOrderItemNum", needLogin = true)
	@Override
	public OrderPayDto saveMyOrderItemNum(OrderItemNumDto orderItemNumDto) throws MainException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(orderItemNumDto);

		String parentOrderNo = orderItemNumDto.getParentOrderNo();
		if (StringUtil.isBlank(parentOrderNo)) {
			throw new ParamInvalidException("订单编号不能为空！");
		}
		OrderPay orderPay = orderPayManager.queryOrderPayByParentOrderNo(parentOrderNo);
		if (null == orderPay) {
			logger.error("saveMyOrderItemNum error, order is null, userId = " + userInfo.getId() + " ,parentOrderNo = "
					+ parentOrderNo);
			return new OrderPayDto(false, "无法找到订单信息，请刷新重试！");
		}

		UserAddress userAddress = userAddressManager.queryUserAddressById(orderPay.getAddressId(), BooleanEnum.TRUE);
		if (null == userAddress) {
			return new OrderPayDto(false, "地址信息有误，请重新设置地址");
		}
		AreaDictInfo provinceInfo = areaDictManager.queryAddressByCode(userAddress.getProvince(), BooleanEnum.TRUE);
		AreaDictInfo cityInfo = areaDictManager.queryAddressByCode(userAddress.getCity(), BooleanEnum.TRUE);
		AreaDictInfo areaInfo = areaDictManager.queryAddressByCode(userAddress.getArea(), BooleanEnum.TRUE);
		if (null == provinceInfo || null == cityInfo || null == areaInfo) {
			return new OrderPayDto(false, "地址信息有误，请重新设置地址");
		}
		AddressInfoDto addressInfoDto = userAddress.convert(provinceInfo, cityInfo, areaInfo);

		if (orderPay.getFromShoppingCart().equals(IsShoppingCartEnum.YES.getCode())) {
			return new OrderPayDto(false, "当前订单无法修改商品数量！");
		}
		Long itemId = orderItemNumDto.getItemId();
		Long itemDetailId = orderItemNumDto.getItemDetailId();
		Integer num = orderItemNumDto.getNum();
		if (null == itemId || null == itemDetailId || null == num) {
			throw new ParamInvalidException("商品ID、商品详情ID和购买数量均不能为空！");
		}
		if (num < 1) {
			return new OrderPayDto(false, "购买数量不得少于1件！");
		}
		ItemDetailInfo itemDetailInfo = itemDetailInfoManager.queryItemDetailInfoById(itemDetailId);
		if (null == itemDetailInfo) {
			logger.error("saveMyOrderItemNum error, itemDetailInfo is null, itemId = " + itemId + ",itemDetailId = "
					+ itemDetailId);
			return new OrderPayDto(false, "商品明细ID为" + itemDetailId + "的商品已下架，请刷新重试！");
		}
		if (num > itemDetailInfo.getTotalNum()) {
			return new OrderPayDto(false, "库存只有" + itemDetailInfo.getTotalNum() + "件了！");
		}
		ItemInfo itemInfo = itemInfoManager.queryItemInfoById(itemId);
		if (null == itemInfo) {
			logger.error("saveMyOrderItemNum error, itemInfo is null, itemId = " + itemId + ",itemDetailId = "
					+ itemDetailId);
			return new OrderPayDto(false, "商品ID为" + itemId + "的商品已下架，请刷新重试！");
		}

		// 修改orderItem中数量和总价格
		OrderItem orderItem = orderItemManager.queryOrderItemByParentOrderNoAndItemId(parentOrderNo, itemId,
				itemDetailId);
		if (null == orderItem) {
			logger.error("saveMyOrderItemNum error, orderItem is null, itemId = " + itemId + ", itemDetailId = "
					+ itemDetailId + ", parentOrderNo = " + parentOrderNo);
			return new OrderPayDto(false, "商品ID、商品详情ID和订单ID不相匹配！");
		}
		orderItem.setItemTotalNum(num);
		orderItem.setItemTotalPrice(num * orderItem.getItemPrice());

		// 修改order中数量和总价格，只有来自商品详情页的订单才可修改数量，这种订单中只会有一个orderItem，所以可以直接修改数量和总价格
		Order order = orderManager.queryOrderByOrderNo(orderItem.getOrderNo());
		if (null == order) {
			logger.error("saveMyOrderItemNum error, orderItem is null, itemId = " + itemId + ", itemDetailId = "
					+ itemDetailId + ", parentOrderNo = " + parentOrderNo + ", orderNo = " + orderItem.getOrderNo());
			return new OrderPayDto(false, "无法找到order信息，请刷新重试！");
		}
		order.setTotalItemNum(orderItem.getItemTotalNum());
		order.setTotalItemPrice(orderItem.getItemTotalPrice());

		// 修改orderPay中的总价格，只有来自商品详情页的订单才可修改数量，这种订单中只会有一个order，所以可以直接修改总价格
		orderPay.setTotalPrice(order.getTotalItemPrice());

		/** 修改父订单、子订单 、订单 */
		TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
		try {
			orderPayManager.modifyOrderPayTotalPrice(orderPay);
			orderManager.modifyOrderPriceAndNum(order);
			orderItemManager.modifyOrderItemPriceAndNum(orderItem);
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			logger.error("saveMyOrder error, save data to database error, userId = " + userInfo.getId(), e);
			return new OrderPayDto(false, "网络超时，请稍后重试");
		}

		// 订单状态集合,key为子订单orderNo,value为子订单状态值
		Map<String, Integer> orderStatusMap = new HashMap<>();
		List<Order> orderList = orderManager.queryOrderByParentOrderNo(parentOrderNo);
		for (Order o : orderList) {
			orderStatusMap.put(o.getOrderNo(), o.getStatus());
		}

		return buildOrderPayDto(parentOrderNo, orderPay.getTotalPrice(), orderPay.getTotalPostage(), addressInfoDto,
				orderPay.getFromShoppingCart(), orderStatusMap);
	}

}
