package com.baoke.interact.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.SellerListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.util.api.json.JsonApiUtil;

/**
 * 主播关注 Service
 * 
 * @author zdy
 * @date: 2018年6月23日 上午9:49:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class SellerFocusServiceTest {

	@Resource
	private SellerFocusService sellerFocusService;

	/**
	 * 查询我的关注(suceess)
	 * 
	 * @author lcl
	 * @date: 2018年7月27日 上午10:11:51
	 */
	@Test
	public void testQueryMyFocusSeller() {
		SellerInfoDto sellerInfoDto = new SellerInfoDto();
		sellerInfoDto.setUserId(2L);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrent(1);
		sellerInfoDto.setPageInfo(pageInfo);
		try {
			SellerListDto sellerInfoListDto = sellerFocusService.queryMyFocusSeller(sellerInfoDto);
			System.out.println(JsonApiUtil.convertToJson(sellerInfoListDto));

			assertNotNull("查询数据不为空", sellerInfoListDto);
			assertNotNull("查询数据不为空", sellerInfoListDto.isSuccess());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 保存关注播主(success)
	 * 
	 * @author lcl
	 * @date: 2018年7月27日 上午10:13:08
	 */
	@Test
	public void testSaveFocusSeller() {
		SellerInfoDto sellerInfoDto = new SellerInfoDto();
		sellerInfoDto.setUserId(2L);
		sellerInfoDto.setSellerId(9L);
		sellerInfoDto.setIsFocus(1);
		try {
			BaseResultDto baseResultDto = sellerFocusService.saveFocusSeller(sellerInfoDto);
			System.out.println(JsonApiUtil.convertToJson(baseResultDto));

			assertNotNull("查询数据不为空", baseResultDto);
			assertNotNull("查询数据不为空", baseResultDto.isSuccess());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
