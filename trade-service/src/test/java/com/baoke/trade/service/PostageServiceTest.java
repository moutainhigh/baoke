package com.baoke.trade.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.info.PostageInfoDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class PostageServiceTest {

	@Resource
	private PostageService postageService;

	/**
	 * 物流
	 * 
	 * @author zdy
	 * @date 2018年6月22日 上午10:37:04
	 */
	@Test
	public void testQueryOrderPostage() {
		try {
			PostageInfoDto postageInfoDto = new PostageInfoDto();
			postageInfoDto.setOrderNo("SO46118958004621");
			postageInfoDto.setUserId(2L);

			postageInfoDto = postageService.queryOrderPostage(postageInfoDto);
			System.out.println(postageInfoDto);
			assertNotNull("查询数据不为空", postageInfoDto);
		} catch (Exception e) {
			fail("error");
		}
	}

}
