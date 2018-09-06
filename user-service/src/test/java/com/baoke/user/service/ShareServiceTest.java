package com.baoke.user.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.ChannelShareDto;
import com.baoke.common.dto.base.BaseDto;

/**
 * 渠道分享Service
 *
 * @author zdy
 * @date: 2018年7月12日 下午7:08:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class ShareServiceTest {

	@Resource
	private ShareService shareService;

	@Test
	public void testQueryShareType() {
		try {
			BaseDto baseDto = new BaseDto();

			ChannelShareDto channelShareDto = shareService.queryShareType(baseDto);
			System.out.println(channelShareDto);

			assertNotNull("未查询到数据", channelShareDto);
		} catch (Exception e) {
			fail("error");
		}
	}

}
