package com.baoke.interact.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.api.VideoGreatDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 视频点赞(喜欢)
 *
 * @author zjj
 * @date 2018年6月21日 下午4:41:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class VideoGreatServiceTest {

	@Resource
	VideoGreatService videoGreatService;

	/**
	 * 视频点赞、喜欢（不喜欢）
	 * 
	 * @author zjj
	 * @date 2018年6月21日 下午4:40:34
	 * @throws ParamInvalidException
	 */
	@Test
	public void testSaveVideoGreat() {
		try {
			VideoGreatDto videoGreatDto = new VideoGreatDto();
			videoGreatDto.setUserId(2L);
			videoGreatDto.setVideoId(3L);
			videoGreatDto.setIsGreat(0);
			// videoGreatDto.setIsDelike(1);

			BaseResultDto baseResultDto = videoGreatService.saveVideoGreat(videoGreatDto);

			assertNotNull("查询数据不为空", baseResultDto);
			assertNotNull("查询数据不为空", baseResultDto.isSuccess());
		} catch (Exception e) {
			fail("error");
		}
	}

}
