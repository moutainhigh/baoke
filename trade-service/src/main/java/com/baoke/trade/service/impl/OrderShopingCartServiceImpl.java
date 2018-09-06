package com.baoke.trade.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.dto.api.ShoppingCartDto;
import com.baoke.common.dto.api.ShoppingCartItemDetailDto;
import com.baoke.common.dto.api.ShoppingCartItemDto;
import com.baoke.common.dto.api.ShoppingCartItemListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.util.MoneyUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.item.domain.ItemDetailInfo;
import com.baoke.item.domain.ItemInfo;
import com.baoke.item.manager.ItemDetailInfoManager;
import com.baoke.item.manager.ItemInfoManager;
import com.baoke.item.manager.ItemPropDictManager;
import com.baoke.trade.constant.IsShoppingCartEnum;
import com.baoke.trade.domain.OrderItem;
import com.baoke.trade.domain.OrderPay;
import com.baoke.trade.domain.OrderShoppingCart;
import com.baoke.trade.manager.OrderItemManager;
import com.baoke.trade.manager.OrderShoppingCartManager;
import com.baoke.trade.service.OrderShoppingCartService;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.UserInfoManager;

@ServiceDefinition(value = "orderShoppingCartService")
@Service("orderShoppingCartService")
public class OrderShopingCartServiceImpl implements OrderShoppingCartService {

	@Resource
	private OrderShoppingCartManager orderShoppingCartManager;
	@Resource
	private UserInfoManager userInfoManager;
	@Resource
	private ItemInfoManager itemInfoManager;
	@Resource
	private ItemDetailInfoManager itemDetailInfoManager;
	@Resource
	private ItemPropDictManager itemPropDictManager;
	@Resource
	private OrderItemManager orderItemManager;

	/**
	 * 查询购物车
	 * 
	 * @author: wyj
	 * @date: 2018年6月20日 下午3:45:21
	 */
	@Override
	@MethodDefinition(value = "queryShoppingCart", needLogin = true)
	public ShoppingCartItemListDto queryShoppingCart(ShoppingCartDto shoppingCartDto) throws ParamInvalidException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(shoppingCartDto);

		List<OrderShoppingCart> shoppingCartList = orderShoppingCartManager.queryShoppingCartByUserId(userInfo.getId());
		int size = shoppingCartList.size();
		if (size == 0) {
			return new ShoppingCartItemListDto(true, "您的购物车没有商品");
		}

		// 购物车列表按主播分组
		Map<Long, List<ShoppingCartItemDetailDto>> sellerShopCartListMap = new HashMap<Long, List<ShoppingCartItemDetailDto>>();
		for (OrderShoppingCart cart : shoppingCartList) {
			if (size == 0) {
				break;
			}
			if (null == cart.getItemId() || null == cart.getItemDetailId()) {
				size--;
				continue;
			}
			ItemInfo itemInfo = itemInfoManager.queryItemInfoById(cart.getItemId());
			ItemDetailInfo itemDetailInfo = itemDetailInfoManager.queryItemDetailInfoById(cart.getItemDetailId());
			if (null == itemInfo || null == itemDetailInfo) {
				size--;
				continue;
			}

			ShoppingCartItemDetailDto shoppingCartItemDetailDto = new ShoppingCartItemDetailDto();
			shoppingCartItemDetailDto.setShoppingCartId(cart.getId());
			shoppingCartItemDetailDto.setItemId(itemInfo.getId());
			shoppingCartItemDetailDto.setItemDetailId(itemDetailInfo.getId());
			shoppingCartItemDetailDto.setMainImgUrl(itemInfo.getMainImgUrl());
			shoppingCartItemDetailDto.setTitle(itemInfo.getTitle());
			shoppingCartItemDetailDto.setPriceYuan(MoneyUtil.changeF2Y(itemDetailInfo.getPrice()));
			shoppingCartItemDetailDto.setPriceFen(itemDetailInfo.getPrice());
			shoppingCartItemDetailDto.setNum(cart.getNum());
			shoppingCartItemDetailDto.setTotalNum(itemDetailInfo.getTotalNum());
			shoppingCartItemDetailDto.setAttr1Name("通用");
			shoppingCartItemDetailDto.setAttr2Name("通用");
			shoppingCartItemDetailDto.setItemStatus(itemInfo.getStatus());

			List<ShoppingCartItemDetailDto> shopCartList = sellerShopCartListMap.get(itemInfo.getSellerId());
			if (shopCartList == null) {
				shopCartList = new ArrayList<ShoppingCartItemDetailDto>();
			}
			shopCartList.add(shoppingCartItemDetailDto);
			sellerShopCartListMap.put(itemInfo.getSellerId(), shopCartList);
		}

		if (sellerShopCartListMap.size() == 0) {
			return new ShoppingCartItemListDto(true, "您的购物车没有商品");
		}

		List<ShoppingCartItemDto> shoppingCartItemList = new ArrayList<ShoppingCartItemDto>();
		for (Entry<Long, List<ShoppingCartItemDetailDto>> entry : sellerShopCartListMap.entrySet()) {
			UserInfo sellerInfo = userInfoManager.queryUserInfoById(entry.getKey());
			if (null == sellerInfo) {
				size = size - entry.getValue().size();
				continue;
			}
			ShoppingCartItemDto shoppingCartItemDto = new ShoppingCartItemDto();
			shoppingCartItemDto.setSellerInfo(
					new SellerInfoDto(sellerInfo.getId(), sellerInfo.getNickName(), sellerInfo.getHeadImgUrl()));
			shoppingCartItemDto.setShoppingCartItemDetailList(sellerShopCartListMap.get(entry.getKey()));
			shoppingCartItemList.add(shoppingCartItemDto);
		}

		ShoppingCartItemListDto shoppingCartItemListDto = new ShoppingCartItemListDto(true, "已展示全部购物车商品");
		shoppingCartItemListDto.setShoppingCartItemList(shoppingCartItemList);
		shoppingCartItemListDto.setItemNum(size);
		return shoppingCartItemListDto;
	}

	@Override
	@MethodDefinition(value = "addShoppingCart", needLogin = true)
	public BaseResultDto addShoppingCart(ShoppingCartDto shoppingCartDto) throws ParamInvalidException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(shoppingCartDto);

		Long itemId = shoppingCartDto.getItemId();
		Long itemDetailId = shoppingCartDto.getItemDetailId();
		Integer num = shoppingCartDto.getNum();

		if (null == itemId || null == itemDetailId || null == num) {
			throw new ParamInvalidException("商品id,商品明细id,购买数量均不能为空");
		}

		ItemInfo itemInfo = itemInfoManager.queryItemInfoById(itemId);
		ItemDetailInfo itemDetailInfo = itemDetailInfoManager.queryItemDetailInfoById(itemDetailId);
		if (null == itemInfo || null == itemDetailInfo) {
			return new BaseResultDto(false, "该商品已下架");
		}

		int itemTotalNum = itemDetailInfo.getTotalNum();
		if (itemTotalNum < num) {
			return new BaseResultDto(false, "该商品库存不足");
		}

		OrderShoppingCart shoppingCart = new OrderShoppingCart(userInfo.getId(), itemId, itemDetailId);
		OrderShoppingCart result = orderShoppingCartManager.queryShoppingCart(shoppingCart);
		if (null == result) { // 新增购物项
			shoppingCart.setPrice(itemInfo.getPrice());
			shoppingCart.setTotalPrice(itemInfo.getPrice() * num);
			shoppingCart.setNum(num);
			orderShoppingCartManager.addShoppingCart(shoppingCart);
		} else { // 修改数量
			shoppingCart.setId(result.getId());
			int newNum = result.getNum() + num;
			if (itemTotalNum < newNum) {
				return new BaseResultDto(false, "该商品库存不足");
			}
			shoppingCart.setNum(newNum);
			shoppingCart.setTotalPrice(itemInfo.getPrice() * newNum);
			orderShoppingCartManager.modifyShoppingCart(shoppingCart);
		}
		return new BaseResultDto(true, "已加入购物车");
	}

	@Override
	@MethodDefinition(value = "deleteShoppingCart", needLogin = true)
	public BaseResultDto deleteShoppingCart(ShoppingCartDto shoppingCartDto) throws ParamInvalidException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(shoppingCartDto);

		String cartIds = shoppingCartDto.getShoppingCartIds();
		if (StringUtil.isBlank(cartIds)) {
			throw new ParamInvalidException("购物车ID不能为空");
		}

		String[] cartIdArray = cartIds.split(",");
		if (cartIdArray != null && cartIdArray.length > 0) {
			int successNum = orderShoppingCartManager.deleteShoppingCart(cartIdArray, userInfo.getId());
			if (successNum != cartIdArray.length) {
				return new BaseResultDto(false,
						"成功" + successNum + "条,失败" + (cartIdArray.length - successNum) + "条,请刷新继续操作！");
			}
		}

		return new BaseResultDto(true, "");
	}

	@Override
	@MethodDefinition(value = "updateShoppingCartNum", needLogin = true)
	public BaseResultDto updateShoppingCartNum(ShoppingCartDto shoppingCartDto) throws ParamInvalidException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(shoppingCartDto);

		String content = shoppingCartDto.getContent();
		if (StringUtil.isBlank(content)) {
			throw new ParamInvalidException("购物车ID、购买数量不能为空");
		}

		String[] idAndNums = content.split(";");
		for (String idAndNum : idAndNums) {
			String[] temp = idAndNum.split(":");
			if (temp.length != 2) {
				throw new ParamInvalidException("购物车ID、购买数量格式不合法");
			}
			if (!StringUtil.isPositiveNumber(temp[0]) || !StringUtil.isPositiveNumber(temp[1])) {
				throw new ParamInvalidException("购物车ID、购买数量格式不合法");
			}
			Long id = Long.valueOf(temp[0]); // 购物车ID
			Integer num = Integer.valueOf(temp[1]); // 购物车数量

			OrderShoppingCart shoppingCart = orderShoppingCartManager.queryShoppingCartById(id);
			if (null == shoppingCart) {
				return new BaseResultDto(false, "购物车信息有误，请刷新重试");
			}

			if (!userInfo.getId().equals(shoppingCart.getUserId())) {
				throw new ParamInvalidException("购物车ID、用户ID不匹配");
			}

			ItemInfo itemInfo = itemInfoManager.queryItemInfoById(shoppingCart.getItemId());
			if (null == itemInfo) {
				return new BaseResultDto(false, "编号为" + shoppingCart.getItemId() + "商品已下架，请刷新");
			}

			ItemDetailInfo itemDetailInfo = itemDetailInfoManager
					.queryItemDetailInfoById(shoppingCart.getItemDetailId());
			if (null == itemDetailInfo) {
				return new BaseResultDto(false, itemInfo.getTitle() + "已下架");
			}

			if (itemDetailInfo.getTotalNum() < num) {
				return new BaseResultDto(false, itemInfo.getTitle() + "库存不足");
			}

			shoppingCart.setNum(num);
			shoppingCart.setTotalPrice(num * shoppingCart.getPrice());

			orderShoppingCartManager.modifyShoppingCart(shoppingCart);
		}

		return new BaseResultDto(true, "操作成功");
	}

	@Override
	public boolean deleteShoppingCart(OrderPay orderPay) {
		if (orderPay.getFromShoppingCart() == IsShoppingCartEnum.YES.getCode()) {
			List<OrderItem> orderItemList = orderItemManager.queryOrderItemByParentOrderNo(orderPay.getParentOrderNo());
			for (OrderItem orderItem : orderItemList) {
				OrderShoppingCart shoppingCart = new OrderShoppingCart();
				shoppingCart.setUserId(orderItem.getUserId());
				shoppingCart.setItemId(orderItem.getItemId());
				shoppingCart.setItemDetailId(orderItem.getItemDetailId());
				orderShoppingCartManager.deleteShoppingCartByUserIdAndItemId(shoppingCart);
			}
			return true;
		}
		return false;
	}
}
