package com.baoke.trade.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.trade.constant.OrderPayTypeEnum;
import com.baoke.trade.domain.Order;

/**
 * 卖家收入明细
 *
 * @author lcl
 * @date 2018年7月23日 上午9:41:34
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:*.xml")
public class SellerIncomeRecordServiceTest {

	@Resource
	private SellerIncomeRecordService sellerIncomeRecordService;

	/**
	 *
	 *
	 * @author lcl
	 * @date 2018年7月23日 上午9:43:52
	 *
	 */
	@Test
	public void testAddSellerIncomeRecord() {

		try {
			// 全部数据都传过去
			Order order = new Order();
			Long logId = 5L;
			OrderPayTypeEnum payType = OrderPayTypeEnum.ALIPAY;
			order.setUserId(2L);
			order.setSellerId(2L);
			order.setTotalItemPrice(330);
			order.setTotalPostage(0);
			order.setParentOrderNo("PO50733244562279");
			order.setOrderNo("SO50733548919478");

			sellerIncomeRecordService.addSellerIncomeRecord(order, logId, payType);
			assertTrue(true);
		} catch (Exception e) {
			fail("error");
		}
	}

	/**
	 * 当有某个参数为null时 数据库是否报错 数据库字段不允许为空 是否做判空验证
	 *
	 * @author lcl
	 * @date 2018年7月23日 上午11:01:27
	 *
	 */
	@Test
	public void testAddSellerIncomeRecord2() {

		try {
			// 全部数据都传过去 数据库 字段也不允许为空
			Order order = new Order();
			Long logId = 5L;
			OrderPayTypeEnum payType = OrderPayTypeEnum.ALIPAY;
			order.setUserId(2L);
			order.setSellerId(2L);
			order.setTotalItemPrice(330);
			order.setTotalPostage(0);
			order.setParentOrderNo("PO50733244562279");
			// order.setOrderNo("SO50733548919478");

			sellerIncomeRecordService.addSellerIncomeRecord(order, logId, payType);

		} catch (Exception e) {
			fail("error");
		}

	}

}
