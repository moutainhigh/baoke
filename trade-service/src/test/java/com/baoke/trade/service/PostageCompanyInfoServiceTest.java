package com.baoke.trade.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.PostageCompanyListDto;
import com.baoke.common.util.api.json.JsonApiUtil;

/**
 * 物流公司查询
 *
 * @author lcl
 * @date 2018年7月23日 上午11:20:50
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:*.xml")
public class PostageCompanyInfoServiceTest {

	@Resource
	private PostageCompanyInfoService postageCompanyInfoService;

	/**
	 *
	 * 查询物流公司
	 * 
	 * @author lcl
	 * @date 2018年7月23日 上午11:22:48
	 *
	 */
	@Test
	public void testQueryPostageCompanyInfoList() {

		try {
			PostageCompanyListDto postageCompanyListDto = postageCompanyInfoService.queryPostageCompanyInfoList();
			String json = JsonApiUtil.convertToJson(postageCompanyListDto);
			System.out.println("json->" + json);
			assertNotNull("不为空", postageCompanyListDto);
		} catch (Exception e) {
			fail("error");
		}

	}

}
