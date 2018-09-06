package com.baoke.interact.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.api.VideoComplaintDictListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoComplaintInfoDto;
import com.baoke.common.exception.base.MainException;
import com.baoke.interact.constant.VideoComplaintDictTypeEnum;

/**
 * 视频举报
 *
 * @author zjj
 * @date 2018年6月21日 下午5:47:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:*.xml")
public class VideoComplaintServiceTest {

	@Resource
	VideoComplaintService videoComplaintService;

	/**
	 * 获取举报字典
	 * 
	 * @author zjj
	 * @date 2018年6月21日 下午5:49:25
	 */
	@Test
	public void testQueryComplaint() {
		VideoComplaintInfoDto videoComplaintInfoDto = new VideoComplaintInfoDto();
		videoComplaintInfoDto.setVideoId(1L);
		videoComplaintInfoDto.setUserId(2L);
		VideoComplaintDictListDto complaintListDto;
		try {
			complaintListDto = videoComplaintService.queryVideoComplaint(videoComplaintInfoDto);

			assertNotNull("查询数据不为空", complaintListDto);
			assertNotNull("查询数据不为空", complaintListDto.isSuccess());
		} catch (MainException e) {
			fail("error");
		}
	}

	/**
	 * 保存举报（吐槽）
	 * 
	 * @author zjj
	 * @date 2018年6月21日 下午6:02:39
	 */
	@Test
	public void testSaveComplaint() {
		try {
			VideoComplaintInfoDto complaintDto = new VideoComplaintInfoDto();
			complaintDto.setUserId(2L);
			complaintDto.setVideoId(1L);
			complaintDto.setType(VideoComplaintDictTypeEnum.INPUT.getCode());
			complaintDto.setContent("内容无趣...........");

			BaseResultDto baseResultDto = videoComplaintService.saveComplaint(complaintDto);

			assertNotNull("查询数据不为空", baseResultDto);
			assertNotNull("查询数据不为空", baseResultDto.isSuccess());
		} catch (Exception e) {
			fail("error");
		}

	}

}
