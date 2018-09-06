package com.baoke.act.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.BannerDto;
import com.baoke.common.dto.BannerListDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.BannerConfigDto;

/**
 * banner配置信息
 * 
 * @author zdy
 * @date: 2018年7月11日 下午8:45:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class BannerConfigServiceTest {

	@Resource
	private BannerConfigService bannerConfigService;

	@Test
	public void testQueryStartPageInfo() {
		BannerConfigDto bannerConfigDto = bannerConfigService.queryStartPageInfo(new BaseDto());

		System.out.println(bannerConfigDto);

		// check
		assertNotNull("未查询到数据", bannerConfigDto);
	}

	@Test
	private void testQueryBannerConfigByStatusOrScenType() {
		BannerConfigDto bannerConfigDto = new BannerConfigDto();
		bannerConfigDto.setScenType("APP_INDEX_TOP");
		try {
			BannerListDto bannerListDto = bannerConfigService
					.queryBannerConfigByStatusOrScenType(new BannerDto(bannerConfigDto, new PageInfo(1, 10)));
			System.out.println(bannerListDto);
			assertNotNull("未查询到数据", bannerListDto);
		} catch (Exception e) {
			fail("error");
		}
	}
}
