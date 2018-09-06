package com.baoke.extra.pay.controllery;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.util.HttpClientHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class AlipayNotifyTest {

	private static Logger logger = Logger.getLogger(AlipayNotifyTest.class);

	private static final String url = "http://127.0.0.1/alipay/notify";

	@Test
	public void test() {

		Map<String, String> map = new HashMap<>();
		map.put("trade_status", "TRADE_SUCCESS");
		map.put("out_trade_no", "PO19516785208271");
		map.put("subject", "test");
		map.put("total_amount", "61.11");
		map.put("trade_no", "111100001111");
		map.put("buyer_id", "111122222222");
		map.put("buyer_logon_id", "22222333");
		map.put("seller_id", "baoke522@163.com");
		map.put("seller_email", "baoke522@163.com");
		map.put("receipt_amount", "test");
		map.put("buyer_pay_amount", "test");
		map.put("appid", "2016091300502541");
		map.put("charset", "utf-8");

		String result = HttpClientHelper.postDataByUrl(url, true, "utf-8", map);

		logger.debug(result);
	}

}
