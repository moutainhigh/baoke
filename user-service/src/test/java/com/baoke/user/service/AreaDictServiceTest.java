package com.baoke.user.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.api.AddressDictListDto;
import com.baoke.common.dto.info.AreaDictInfoDto;

/**
 * 地区字典
 *
 * @author zdy
 * @date: 2018年7月9日 下午7:16:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class AreaDictServiceTest {

	@Resource
	private AreaDictService areaDictService;

	@Test
	public void testQueryAddressDict() {
		try {
			AreaDictInfoDto areaDictInfoDto = new AreaDictInfoDto();
			areaDictInfoDto.setParentCode("86");
			AddressDictListDto addressDictListDto = areaDictService.queryAddressDictByParentCode(areaDictInfoDto);
			System.out.println(addressDictListDto);

			assertNotNull("未查询到数据", addressDictListDto);
			assertNotNull("未查询到地区数据", addressDictListDto.getAddressList());
		} catch (Exception e) {
			fail("error");
		}
	}

}
