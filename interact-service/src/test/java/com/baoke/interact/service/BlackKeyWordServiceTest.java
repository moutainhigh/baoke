package com.baoke.interact.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.BlackKeyWordDto;
import com.baoke.common.dto.seller.BlackKeyWordListDto;
import com.baoke.common.dto.seller.CommonQueryDto;

/**
 * 敏感词
 *
 * @author lcl
 * @date: 2018年7月25日 下午4:32:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:*.xml")
public class BlackKeyWordServiceTest {

	@Resource
	BlackKeyWordService blackKeyWordService;

	/**
	 * 敏感词列表
	 * 
	 * @author lcl
	 * @date: 2018年7月25日 下午4:46:17
	 */
	@Test
	public void testQueryBlackKeyWordInfoList() {
		try {
			BlackKeyWordDto blackKeyWordDto = new BlackKeyWordDto();
			blackKeyWordDto.setPageInfo(new PageInfo(1, 20));
			BlackKeyWordListDto blackKeyWordListDto = blackKeyWordService.queryBlackKeyWordListByPage(blackKeyWordDto);
			assertNotNull(blackKeyWordListDto);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSaveBlackKeyWord() {

		try {
			BlackKeyWordDto blackKeyWordDto = new BlackKeyWordDto();
			blackKeyWordDto.setName("敏感词测试14");
			blackKeyWordDto.setBkUserId(14L);

			long numId = blackKeyWordService.addBlackKeyWord(blackKeyWordDto);
			assertNotNull("查询数据不为空", numId);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 修改
	 * 
	 * @author lcl
	 * @date: 2018年7月26日 上午10:52:17
	 */
	@Test
	public void testDelBlackKeyWord() {

		try {
			CommonQueryDto commonQueryDto = new CommonQueryDto();
			List<Long> ids = new ArrayList<>();
			ids.add(3L);
			ids.add(5L);
			ids.add(6L);
			commonQueryDto.setIds(ids);

			int numId = blackKeyWordService.deleteBlackKeyWordByIds(ids, 12L);
			assertNotNull("查询数据不为空", numId);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
