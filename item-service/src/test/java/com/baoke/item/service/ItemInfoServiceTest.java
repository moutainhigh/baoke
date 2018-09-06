package com.baoke.item.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.ItemDetailDto;
import com.baoke.common.dto.SellerItemListDto;
import com.baoke.common.dto.info.ItemInfoDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 商品测试列
 *
 * @author zjj
 * @date 2018年6月19日 上午10:46:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class ItemInfoServiceTest {

	@Resource
	private ItemInfoService itemInfoService;

	@Test
	public void testQueryItemDetail() {
		try {
			ItemInfoDto itemInfoDto = new ItemInfoDto();
			itemInfoDto.setItemId(2L);
			ItemDetailDto itemDetail = itemInfoService.queryItemByItemId(itemInfoDto);
			System.out.println(itemDetail);

			assertNotNull("未查询到数据", itemDetail);
			assertNotNull("商品信息不存在", itemDetail.getItemInfo());
			assertNotNull("商品对应的播主不存在", itemDetail.getSellerInfo());

			assertEquals("查询错误", itemInfoDto.getItemId(), itemDetail.getItemInfo().getItemId());
		} catch (Exception e) {
			fail("error");
		}
	}

	@Test
	public void testQuerySellerItem() throws ParamInvalidException {
		try {
			SellerInfoDto sellerInfoDto = new SellerInfoDto(1, 12);
			sellerInfoDto.setSellerId(2L);
			SellerItemListDto sellerItemListDto = itemInfoService.queryItemListBySellerId(sellerInfoDto);
			System.out.println(sellerItemListDto);

			assertNotNull("未查询到数据", sellerItemListDto);
			assertNotNull("该主播下无商品", sellerItemListDto.getItemList());
		} catch (Exception e) {
			fail("error");
		}
	}

}
