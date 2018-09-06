package com.baoke.trade.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.OrderListDto;
import com.baoke.common.dto.api.OrderAddressDto;
import com.baoke.common.dto.api.OrderItemNumDto;
import com.baoke.common.dto.api.OrderPayDto;
import com.baoke.common.dto.api.OrderPaySignDto;
import com.baoke.common.dto.api.OrderSubmitDto;
import com.baoke.common.dto.api.RemindSendItemDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.OrderInfoDto;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.api.json.JsonApiUtil;
import com.baoke.trade.constant.IsShoppingCartEnum;
import com.baoke.trade.constant.OrderPayTypeEnum;
import com.baoke.trade.constant.OrderStatusEnum;

/**
 * 订单service测试类
 * 
 * @author zdy
 * @date: 2018年6月22日 下午1:57:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class OrderServiceTest {

	@Resource
	OrderService orderService;

	@Resource
	OrderSaveService orderSaveService;

	@Resource
	OrderPayService orderPayService;

	@Test
	public void testQueryMyOrder() {
		OrderInfoDto orderDto = new OrderInfoDto();
		orderDto.setUserId(2L);
		orderDto.setOrderStatus(OrderStatusEnum.WAIT_DELIVER.getCode());
		orderDto.setPageInfo(new PageInfo(1, 12));
		try {

			OrderListDto orderList = orderService.queryMyOrder(orderDto);
			System.out.println(JsonApiUtil.convertToJson(orderList));

			// check
			assertNotNull("查询数据不为空", orderList);
			assertNotNull("查询数据不为空", orderList.isSuccess());
		} catch (Exception e) {
			fail("error");
		}
	}

	@Test
	public void testQueryMyOrderDetail() {
		OrderInfoDto orderInfo = new OrderInfoDto();

		/*
		 * orderInfo.setUserId(2L); orderInfo.setOrderNo("SO5582960603007");
		 */

		orderInfo.setUserId(2L);
		orderInfo.setOrderNo("SO5582960603007");
		try {
			OrderInfoDto orderInfoDto = orderService.queryMyOrderDetail(orderInfo);

			assertNotNull("未查询到数据", orderInfoDto);
			assertNotNull("查询数据不为空", orderInfo.isSuccess());
		} catch (MainException e) {
			fail("error");
		}
	}

	@Test
	public void testSaveMyOrder() {
		OrderSubmitDto orderSubmitDto = new OrderSubmitDto();

		orderSubmitDto.setIsShoppingCart(IsShoppingCartEnum.NO.getCode());
		// orderSubmitDto.setShoppingCartIds("28");
		orderSubmitDto.setUserId(100006L);
		orderSubmitDto.setItemId(74L);
		orderSubmitDto.setItemDetailId(60L);
		orderSubmitDto.setNum(1);

		try {
			OrderPayDto orderPayDto = orderSaveService.saveMyOrder(orderSubmitDto);
			System.out.println(JsonApiUtil.convertToJson(orderPayDto));
		} catch (Exception e) {
			fail("error");
		}
	}

	@Test
	public void testSaveMyOrderItemNum() {
		OrderItemNumDto orderItemNumDto = new OrderItemNumDto();
		orderItemNumDto.setParentOrderNo("PO24878915951818");
		orderItemNumDto.setUserId(100006L);
		orderItemNumDto.setItemDetailId(60L);
		orderItemNumDto.setItemId(74L);
		orderItemNumDto.setNum(10);
		try {
			OrderPayDto orderPayDto = orderSaveService.saveMyOrderItemNum(orderItemNumDto);
			System.out.println(JsonApiUtil.convertToJson(orderPayDto));
		} catch (Exception e) {
			fail("error");
		}
	}

	@Test
	public void testSaveOrderAddress() {
		OrderAddressDto orderAddressDto = new OrderAddressDto();
		orderAddressDto.setUserId(100006L);
		orderAddressDto.setPayType(1);
		orderAddressDto.setAddressId(17L);
		orderAddressDto.setParentOrderNo("PO24878915951818");
		try {
			OrderPayDto orderPayDto = orderSaveService.saveMyOrderAddress(orderAddressDto);
			System.out.println(orderPayDto);
			assertNotNull("未查询到数据", orderPayDto);
		} catch (MainException e) {
			fail("error");
		}
	}

	@Test
	public void testPayMyOrder() {
		OrderPaySignDto orderPaySign = new OrderPaySignDto();
		orderPaySign.setUserId(2L);
		orderPaySign.setIp("192.168.1.1");
		orderPaySign.setPayType(OrderPayTypeEnum.ALIPAY.getCode());
		orderPaySign.setParentOrderNo("PO46713211717735");

		try {
			OrderPaySignDto payMyOrder = orderPayService.payMyOrder(orderPaySign);

			String result = JsonApiUtil.convertToJson(payMyOrder);
			System.out.println(result);

			// check
			assertNotNull("未查询到数据", payMyOrder);
		} catch (Exception e) {
			fail("error");
		}
	}

	/**
	 * 提醒发货
	 * 
	 * @author zjj
	 * @date 2018年7月20日 下午4:15:05
	 */
	@Test
	public void testRemindSendItem() {
		try {
			RemindSendItemDto remindSendItemDto = new RemindSendItemDto();
			remindSendItemDto.setOrderNo("SO967367941577613");
			remindSendItemDto.setUserId(2L);

			BaseResultDto baseResultDto = orderService.remindSendItem(remindSendItemDto);
			System.out.println(baseResultDto);
			assertNotNull("查询的数据不为空", baseResultDto);
		} catch (Exception e) {
			fail("error");
		}
	}

	/**
	 * 确认收货
	 * 
	 * @author zdy
	 * @date: 2018年7月12日 下午6:51:35
	 */
	@Test
	public void testConfirmReceiveItem() {
		OrderPaySignDto orderPaySign = new OrderPaySignDto();
		orderPaySign.setUserId(2L);

		OrderInfoDto orderInfoDto = new OrderInfoDto();
		orderInfoDto.setUserId(2L);
		orderInfoDto.setOrderNo("SO1100546080544");
		try {
			BaseResultDto baseResultDto = orderService.confirmReceiveItem(orderInfoDto);

			System.out.println(baseResultDto);
			assertNotNull("查询的数据不为空", baseResultDto);
		} catch (Exception e) {
			fail("error");
		}
	}

}
