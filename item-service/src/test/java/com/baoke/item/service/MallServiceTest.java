package com.baoke.item.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.api.MallHomeDto;
import com.baoke.common.dto.base.BaseDto;

/**
 * 商城
 * 
 * @author zdy
 * @date: 2018年7月5日 下午5:24:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class MallServiceTest {

	@Resource
	private MallService mallService;

	/**
	 * 商城－查询商城首页(success)
	 * 
	 * @author zdy
	 * @date: 2018年7月4日 下午4:47:56
	 * @return
	 */
	@Test
	public void testQueryMallHome() {
		try {
			BaseDto baseDto = new BaseDto();
			MallHomeDto mallHomeDto = mallService.queryMallHome(baseDto);
			System.out.println(mallHomeDto);

			assertNotNull("未查询到数据", mallHomeDto);
			assertNotNull("未查询到 人气热门商品信息", mallHomeDto.getItemHotList());
			assertNotNull("未查询到 新品信息", mallHomeDto.getItemNewList());
			assertNotNull("未查询到本周明星推荐 信息", mallHomeDto.getRecommendList());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
