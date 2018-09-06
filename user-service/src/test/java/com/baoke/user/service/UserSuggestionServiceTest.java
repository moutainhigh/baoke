package com.baoke.user.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.api.UserSuggestionDto;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 用户建议与投诉
 *
 * @author zdy
 * @date: 2018年7月12日 下午7:25:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class UserSuggestionServiceTest {

	@Resource
	private UserSuggestionService userSuggestionService;

	/**
	 * 设置-报错投诉与建议
	 *
	 * @author zdy
	 * @date: 2018年7月12日 下午7:25:31
	 */
	@Test
	public void testSaveSuggestion() {
		try {
			UserSuggestionDto suggestionDto = new UserSuggestionDto();
			suggestionDto.setContent("LLL测试");
			suggestionDto.setUserId(6L);

			BaseResultDto baseResultDto = userSuggestionService.saveSuggestion(suggestionDto);
			System.out.println(baseResultDto);

			assertNotNull("查询数据不为空", baseResultDto);
		} catch (Exception e) {
			fail("error");
		}
	}

}
