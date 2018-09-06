package com.baoke.interact.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.api.VideoCommentGreatDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoCommentInfoDto;

/**
 * 视频评论
 * 
 * @author zdy
 * @date: 2018年6月23日 上午9:49:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class VideoCommentManagerServiceTest {

	@Resource
	VideoCommentManagerService videoCommentManagerService;

	/**
	 * 保存视频评论点赞(站内信暂时没通 其他success)
	 * 
	 * @author lcl
	 * @date: 2018年7月27日 上午10:53:00
	 */
	@Test
	public void testSaveVideoCommentGreat() {
		VideoCommentGreatDto videoCommentGreatDto = new VideoCommentGreatDto();
		videoCommentGreatDto.setCommentId(5L);
		videoCommentGreatDto.setIsGreat(1);
		videoCommentGreatDto.setUserId(9L);
		try {
			BaseResultDto baseResultDto = videoCommentManagerService.saveVideoCommentGreat(videoCommentGreatDto);

			assertNotNull("查询数据不为空", baseResultDto);
			assertNotNull("查询数据不为空", baseResultDto.isSuccess());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 保存视频评论（站内信暂时没通 其他success）
	 * 
	 * @author lcl
	 * @date: 2018年7月27日 上午10:56:26
	 */
	@Test
	public void testSaveVideoComment() {
		VideoCommentInfoDto videoCommentDto = new VideoCommentInfoDto();
		// videoCommentDto.setParentId(2L);
		videoCommentDto.setUserId(2L);
		videoCommentDto.setVideoId(73L);
		videoCommentDto.setStatus(1);
		videoCommentDto.setContent("测试用例 1 !");
		try {
			BaseResultDto baseResultDto = videoCommentManagerService.saveVideoComment(videoCommentDto);

			assertNotNull("查询数据不为空", baseResultDto);
			assertNotNull("查询数据不为空", baseResultDto.isSuccess());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
