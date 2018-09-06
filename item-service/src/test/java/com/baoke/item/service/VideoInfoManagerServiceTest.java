package com.baoke.item.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.SellerVideoListDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.ItemInfoDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.item.constant.VideoStatusEnum;

/**
 * 视频seller增删改service
 *
 * @author lcl
 * @date 2018年7月24日 上午9:49:49
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:*.xml")
public class VideoInfoManagerServiceTest {

	@Resource
	private VideoInfoManagerService videoInfoManagerService;

	/**
	 * 根据条件查询播主视频
	 *
	 * @author lcl
	 * @date 2018年7月24日 上午9:58:18
	 *
	 */
	@Test
	public void testQuerySellerVideoList() {
		try {
			CommonQueryDto commonQueryDto = new CommonQueryDto();
			List<Long> list = new ArrayList<>();
			list.add(1L);
			list.add(2L);
			commonQueryDto.setIds(list);
			commonQueryDto.setStatus(60);
			commonQueryDto.setUserId(2L);
			SellerVideoListDto sellerVideoListDto = videoInfoManagerService.querySellerVideoList(commonQueryDto);

			assertNotNull("查询数据不为空", sellerVideoListDto);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 保存视频状态
	 *
	 * @author lcl
	 * @date: 2018年7月24日 下午2:10:05
	 */
	@Test
	public void testSaveVideoStatus() {
		try {
			CommonQueryDto commonQueryDto = new CommonQueryDto();
			List<Long> list = new ArrayList<>();
			list.add(1L);
			list.add(2L);
			commonQueryDto.setIds(list);
			commonQueryDto.setStatus(VideoStatusEnum.DOWNLINE.getCode());
			commonQueryDto.setUserId(2L);
			BaseResultDto baseResultDto = videoInfoManagerService.saveVideoStatus(commonQueryDto);
			assertNotNull("查询数据不能为空", baseResultDto);

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	/**
	 * 视频审核
	 *
	 * @author lcl
	 * @date: 2018年7月24日 下午1:35:01
	 */
	@Test
	public void testAuditVideoInfo() {
		try {
			CommonQueryDto commonQueryDto = new CommonQueryDto();
			commonQueryDto.setVideoId(2L);
			commonQueryDto.setStatus(VideoStatusEnum.AUDIT_FAIL.getCode());
			commonQueryDto.setDownLineVideoFlag(false);
			commonQueryDto.setUserId(2L);
			BaseDto baseDto = videoInfoManagerService.auditVideoInfo(commonQueryDto);

			assertNotNull("查询数据不为空", baseDto);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 发布视频
	 *
	 * @author lcl
	 * @date: 2018年7月24日 下午4:18:59
	 */
	@Test
	public void testSaveVideoInfo() {
		try {
			VideoInfoDto videoInfoDto = new VideoInfoDto();
			videoInfoDto.setTitle("方世玉2");
			videoInfoDto.setUrl("www.baidu.com");
			videoInfoDto.setIconImgUrl("www.baid.com");
			videoInfoDto.setUserId(2L);
			List<ItemInfoDto> itemList = new ArrayList<ItemInfoDto>();
			ItemInfoDto itemInfoDto = new ItemInfoDto();
			itemInfoDto.setItemId(1L);
			itemList.add(itemInfoDto);
			videoInfoDto.setItemList(itemList);

			videoInfoManagerService.saveVideoInfo(videoInfoDto);

			assertTrue(true);
		} catch (Exception e) {
			fail("error");
		}
	}
}
