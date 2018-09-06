package com.baoke.interact.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.api.VideoCommentListDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.VideoCommentInfoDto;
import com.baoke.common.dto.info.VideoInfoDto;

/**
 * 视频评论 Service
 * 
 * @author zdy
 * @date: 2018年6月23日 上午9:49:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class VideoCommentServiceTest {

	@Resource
	VideoCommentService videoCommentService;

	@Test
	public void testQueryVideoComment() {
		VideoInfoDto videoInfoDto = new VideoInfoDto();
		videoInfoDto.setVideoId(73L);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrent(1);
		videoInfoDto.setPageInfo(pageInfo);
		try {
			VideoCommentListDto videoCommentListDto = videoCommentService.queryVideoCommentListByVideoId(videoInfoDto);

			// check
			assertNotNull("查询数据不为空", videoCommentListDto);
			assertNotNull("查询数据不为空", videoCommentListDto.getCommentList());
			assertNotNull(videoCommentListDto.isSuccess());
		} catch (Exception e) {
			fail("error");
		}
	}

	@Test
	public void testQueryReplyVideoComment() {
		VideoCommentInfoDto videoCommentDto = new VideoCommentInfoDto();
		videoCommentDto.setSecondParentId(106L);
		videoCommentDto.setVideoId(68l);
		videoCommentDto.setPageInfo(new PageInfo(1, 12));
		try {

			VideoCommentListDto videoCommentListDto = videoCommentService
					.queryReplyVideoCommentListById(videoCommentDto);

			// check
			assertNotNull("未查询到数据", videoCommentListDto);
			assertNotNull("查询失败", videoCommentListDto.getCommentInfo());
			assertNotNull("该视频无评论信息", videoCommentListDto.getCommentList());
			assertNotNull(videoCommentListDto.isSuccess());
		} catch (Exception e) {
			fail("error");
		}
	}

}
