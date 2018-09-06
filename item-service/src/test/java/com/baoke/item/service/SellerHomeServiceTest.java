package com.baoke.item.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.seller.SellerHomeDto;
import com.baoke.common.exception.base.MainException;

/**
 * seller首页
 *
 * @author lcl
 * @date 2018年7月24日 上午9:25:19
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:*.xml")
public class SellerHomeServiceTest {

	@Resource
	private SellerHomeService sellerHomeService;

	/**
	 * seller首页(success)
	 * 
	 * @author lcl
	 * @date: 2018年7月24日 下午9:35:57
	 */
	@Test
	public void testSellerHomeStatistics() {

		try {
			long sellerId = 2L;
			SellerHomeDto sellerHomeDto = sellerHomeService.sellerHomeStatistics(sellerId);
			System.out.println(sellerHomeDto);

			assertNotNull("查询结果不为空", sellerHomeDto);
		} catch (MainException e) {
			fail(e.getMessage());
		}
	}
}
