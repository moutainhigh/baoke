package com.baoke.trade.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.domain.result.Result;

/**
 * 微信统一下单接口测试
 *
 * @author zjj
 * @date 2018年7月9日 下午2:07:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class WechatServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(WechatServiceTest.class);

	@Resource
	WechatService wechatService;

	@Test
	public void testWechatService() {
		Result result = null;
		try {
			result = wechatService.wechatPay("PO5575249062706", "test", 100, "192.168.1.1");
		} catch (Exception e) {
			logger.error("获取支付口令失败，请刷新重试！");
		}
		if (!result.isSuccess()) {
			logger.error("获取支付口令失败:" + result.getMessage());
		} else {
			String s = (String) result.getBody();
			logger.info(s);
		}

	}

}
