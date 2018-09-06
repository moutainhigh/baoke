package com.baoke.item.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.baoke.common.dto.SellerVideoListDto;
import com.baoke.common.dto.VideoListDto;
import com.baoke.common.dto.api.RecommendHomeDto;
import com.baoke.common.dto.api.VideoDetailDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.item.constant.VideoDetailQueryScenEnum;

/**
 * 视频接口测试类
 *
 * @author zjj
 * @date 2018年6月19日 上午10:44:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
@WebAppConfiguration
public class VideoInfoServiceTest {

	@Autowired
	private VideoInfoService videoInfoService;

	/**
	 * 查询首页推荐列表
	 * 
	 * @author lcl
	 * @date: 2018年7月24日 下午9:33:30
	 */
	@Test
	public void testQueryRecommendHome() {
		try {
			VideoInfoDto videoInfoDto = new VideoInfoDto();
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurrent(1);
			videoInfoDto.setPageInfo(pageInfo);
			RecommendHomeDto recommendHomeDto = videoInfoService.queryRecommendHome(videoInfoDto);
			System.out.println(recommendHomeDto);

			assertNotNull("未查询到数据", recommendHomeDto);
			assertNotNull("未查询到banner信息", recommendHomeDto.getBannerList());
			assertNotNull("未查询到视频信息", recommendHomeDto.getVideoList());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 查询首页关注列表 (success)
	 * 
	 * @author lcl
	 * @date: 2018年7月24日 下午9:31:20
	 */
	@Test
	public void testQueryFollowVideo() {
		VideoInfoDto videoInfoDto = new VideoInfoDto();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrent(1);
		videoInfoDto.setPageInfo(pageInfo);
		videoInfoDto.setUserId(9L);

		try {
			VideoListDto videoInfoListDto = videoInfoService.queryFollowVideo(videoInfoDto);
			System.out.println(videoInfoDto);

			assertNotNull("未查询到数据", videoInfoListDto);
			assertNotNull("暂时还没有关注的视频", videoInfoListDto.getVideoList());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 查询视频详情(success)
	 * 
	 * @author lcl
	 * @date: 2018年7月24日 下午9:30:00
	 */
	@Test
	public void testQueryVideoDetail() {
		VideoDetailDto videoDetailDto = new VideoDetailDto();
		videoDetailDto.setVideoId(68L);
		videoDetailDto.setUserId(100006L);
		videoDetailDto.setSellerId(9L);
		videoDetailDto.setQueryScen(VideoDetailQueryScenEnum.SELLER_HOME.getCode());
		try {
			VideoListDto videoListDto = videoInfoService.queryVideoDetail(videoDetailDto);
			System.out.println(videoListDto);

			assertNotNull("未查询到数据", videoListDto);
			assertNotNull("未查询到视频", videoListDto.getVideoInfo());
		} catch (Exception e) {
			fail("error");
		}
	}

	/**
	 * 查询播主（卖家）首页（视频页）(success)
	 * 
	 * @author lcl
	 * @date: 2018年7月24日 下午9:27:56
	 */
	@Test
	public void testQuerySellerVideo() {
		int curPageNo = 2;
		SellerInfoDto sellerInfoDto = new SellerInfoDto(curPageNo, 12);
		sellerInfoDto.setUserId(2L);
		sellerInfoDto.setSellerId(9L);
		try {
			SellerVideoListDto sellerVideoListDto = videoInfoService.querySellerVideo(sellerInfoDto);
			System.out.println(sellerVideoListDto);

			assertNotNull("未查询到数据", sellerVideoListDto);
			assertNotNull("该主播下无视频信息", sellerVideoListDto.getVideoList());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	//
	// @Test
	// public void testQueryFindVideo() {
	// fail("Not yet implemented");
	// }
	/**
	 * 查询我喜欢的视频 (success)
	 * 
	 * @author lcl
	 * @date: 2018年7月24日 下午9:24:53
	 */
	@Test
	public void testQueryMyGreatVideo() {
		VideoInfoDto videoInfoDto = new VideoInfoDto();
		videoInfoDto.setUserId(100006L);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrent(2);
		try {
			VideoListDto videoInfoListDto = videoInfoService.queryMyGreatVideo(videoInfoDto);
			System.out.println(videoInfoListDto);

			assertNotNull("未查询到数据", videoInfoListDto);
			assertNotNull("我还没有点赞喜欢的视频", videoInfoListDto.getVideoList());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
