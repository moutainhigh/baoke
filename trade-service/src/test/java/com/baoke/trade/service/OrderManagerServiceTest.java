package com.baoke.trade.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.trade.domain.Order;
import com.baoke.trade.domain.OrderPay;
import com.baoke.trade.domain.PayAlipayLog;

/**
 * 订单管理
 *
 * @author lcl
 * @date 2018年7月20日 下午3:37:23
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class OrderManagerServiceTest {

	@Resource
	private OrderManagerService orderManagerService;

	/**
	 * 修改商品数量和状态(success)
	 *
	 * @author lcl
	 * @date 2018年7月20日 下午3:41:44
	 *
	 */
	@Test
	public void testModifyItemNumAndOrderStatus() {
		try {
			Order order = new Order();
			order.setId(13L);
			order.setOrderNo("92581cdcf1134891b124983e7ba4f9d9");

			boolean b = orderManagerService.modifyItemNumAndOrderStatus(order);
			// 断言 这个是正确的
			assertTrue(b);
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	/**
	 * 修改订单支付状态(success)
	 *
	 * @author lcl
	 * @date 2018年7月20日 下午5:16:56
	 *
	 */
	@Test
	public void testModifyOrderPayStatus() {

		try {

			OrderPay orderPay = new OrderPay();
			orderPay.setId(10L);
			PayAlipayLog payAlipayLog = new PayAlipayLog();
			payAlipayLog.setTradeStatus("TRADE_SUCCESS");

			boolean b = orderManagerService.modifyOrderPayStatus(orderPay, payAlipayLog);
			assertTrue(b);
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
