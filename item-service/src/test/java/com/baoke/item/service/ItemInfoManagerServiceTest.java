package com.baoke.item.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.constant.PostageFreeStatusEnum;
import com.baoke.common.dto.ItemListDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.ItemDetailInfoDto;
import com.baoke.common.dto.seller.ItemPostageDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 商品信息管理
 *
 * @author lcl
 * @date 2018年7月23日 下午3:19:57
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:*.xml")
public class ItemInfoManagerServiceTest {

	@Resource
	private ItemInfoManagerService itemInfoManagerService;

	/**
	 * 首页商品预警少于10件的商品
	 *
	 * @author lcl
	 * @date 2018年7月23日 下午3:22:59
	 *
	 */
	@Test
	public void testQueryItemDetailInfoLessTenBySellerId() {

		try {
			long sellerId = 2L;
			PageInfo pageInfo = new PageInfo(0, 5);

			ItemListDto itemListDto = itemInfoManagerService.queryItemDetailInfoLessTenBySellerId(sellerId, pageInfo);

			assertNotNull("查询结果不为空", itemListDto);
		} catch (Exception e) {
			fail("error");
		}

	}

	/**
	 * 修改商品邮费
	 *
	 * @author lcl
	 * @date 2018年7月23日 下午4:22:36
	 *
	 */
	@Test
	public void testModifyItemPostage() {

		try {
			ItemPostageDto itemInfoPostDto = new ItemPostageDto();
			itemInfoPostDto.setItemId(2L);
			// itemInfoPostDto.setType(PostageFreeStatusEnum.FREE.getCode());
			itemInfoPostDto.setType(PostageFreeStatusEnum.UN_FREE.getCode());
			itemInfoPostDto.setPostage(1);
			itemInfoPostDto.setAppendPostageArea(null);
			itemInfoPostDto.setAppendPostage(Integer.toString(2));

			itemInfoManagerService.modifyItemPostage(itemInfoPostDto);

			assertTrue(true);
		} catch (Exception e) {
			fail("error");

		}

	}

	/**
	 * 修改商品详情表的数量
	 *
	 * @author lcl
	 * @date 2018年7月23日 下午4:59:21
	 *
	 */
	@Test
	public void testModifyItemDetailNum() {

		try {
			ItemDetailInfoDto itemDetailInfoDto = new ItemDetailInfoDto();
			itemDetailInfoDto.setItemId(2L);
			itemDetailInfoDto.setItemDetailId(48L);
			itemDetailInfoDto.setTotalNum(55);

			itemInfoManagerService.modifyItemDetailNum(itemDetailInfoDto);

			assertTrue(true);
		} catch (ParamInvalidException e) {
			fail("error");
		}

	}

}
