package com.baoke.item.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.info.MallRecommendInfoDto;

/**
 * 商城managerServiceTest
 *
 * @author lcl
 * @date 2018年7月23日 下午8:11:42
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class MallManagerServiceTest {

	@Resource
	private MallManagerService mallManagerService;

	/**
	 * 保存明星推荐
	 *
	 * @author lcl
	 * @date 2018年7月23日 下午8:12:54
	 *
	 */
	@Test
	public void testSaveMallRecommendInfo() {

		try {
			MallRecommendInfoDto mallRecommendInfoDto = new MallRecommendInfoDto();
			mallRecommendInfoDto.setMallItemRecommendId(1L);
			mallRecommendInfoDto.setImgUrl(null);
			mallRecommendInfoDto.setSort(1);
			mallRecommendInfoDto.setTitle("林允儿推荐2");

			mallManagerService.saveMallRecommendInfo(mallRecommendInfoDto);

			assertTrue(true);
		} catch (Exception e) {
			fail("error");
		}
	}
}
