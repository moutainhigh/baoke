package com.baoke.item.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.CategoryDictListDto;
import com.baoke.common.dto.info.CategoryDictDto;

/**
 * 类目列表
 *
 * @author lcl
 * @date 2018年7月23日 下午1:44:37
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:*.xml")
public class CategoryDictServiceTest {

	@Resource
	private CategoryDictService categoryDictService;

	@Test
	public void testQueryCategoryDict() {

		try {
			CategoryDictDto categoryDto = new CategoryDictDto();
			categoryDto.setCategoryId(0L);

			CategoryDictListDto categoryDictListDto = categoryDictService.queryCategoryDict(categoryDto);

			System.out.println(categoryDictListDto);
			assertNotNull("断言不为空", categoryDictListDto);
		} catch (Exception e) {
			fail("error");
		}
	}

}
