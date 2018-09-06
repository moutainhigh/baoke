package com.baoke.user.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.SellerAuditListDto;
import com.baoke.common.dto.info.SellerAuditInfoDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.dto.seller.SellerAudioStatusDto;

/**
 * 卖家信息
 *
 * @author lcl
 * @date 2018年7月20日 下午1:41:46
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class SellerInfoServiceTest {

	@Resource
	private SellerInfoService sellerInfoservice;

	@Test
	public void querySellerAuditInfo() {
		Long sellerId = 2L;
		try {
			SellerAuditInfoDto sellerAuditInfoDto = sellerInfoservice.querySellerAuditInfoBySellerId(sellerId);
			System.out.println(sellerAuditInfoDto);
			assertNotNull("查到数据为空", sellerAuditInfoDto);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 查询卖家状态
	 *
	 * @author lcl
	 * @date 2018年7月19日
	 * @param:
	 * @return: void
	 * @throws
	 */
	@Test
	public void querySellerAudioStatus() {
		try {
			Long sellerId = 100017L;
			SellerAudioStatusDto sellerAudioStatusDto = sellerInfoservice.querySellerAudioStatus(sellerId);
			assertNotNull("查到数据不为空", sellerAudioStatusDto);
		} catch (Exception e) {
			fail("error");
		}
	}

	/**
	 * 根据名字查询卖家审核信息
	 *
	 * @author lcl
	 * @date 2018年7月19日
	 * @param:
	 * @return: void
	 * @throws
	 */
	@Test
	public void querySellerAuditInfoByName() {
		try {
			SellerInfoDto sellerInfoDto = new SellerInfoDto();
			// 如果卖家信息为空
			SellerAuditListDto ellerAuditListDto = sellerInfoservice.querySellerAuditInfoByName(sellerInfoDto);
			assertNotNull("数据不为空", ellerAuditListDto);
		} catch (Exception e) {
			fail("error");
		}

	}

}
