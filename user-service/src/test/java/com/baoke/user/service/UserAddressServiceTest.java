package com.baoke.user.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.AddressListDto;
import com.baoke.common.dto.info.AddressInfoDto;

/**
 * 收货地址service测试类
 *
 * @author zdy
 * @date: 2018年6月22日 下午1:57:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class UserAddressServiceTest {

	@Resource
	UserAddressService userAddressService;

	@Test
	public void testQueryMyAddress() {
		AddressInfoDto addressInfoDto = new AddressInfoDto();
		addressInfoDto.setUserId(2L);
		try {
			AddressListDto addressListDto = userAddressService.queryAddressByUserId(addressInfoDto);

			System.out.println(addressListDto);
			assertNotNull(addressListDto);
		} catch (Exception e) {
			fail("error");
		}
	}

	@Test
	public void testSaveMyAddress() {
		try {
			AddressInfoDto addressInfoDto = new AddressInfoDto();
			addressInfoDto.setUserId(2L);
			addressInfoDto.setName("收货地址2");
			addressInfoDto.setProvince("湖北省");
			addressInfoDto.setCity("襄阳市");
			addressInfoDto.setArea("保康县");
			addressInfoDto.setAddress("小小窝");
			addressInfoDto.setPhone("15266663333");
			addressInfoDto.setDefaultFlag(1);
			addressInfoDto.setAddressId(4L);

			userAddressService.saveMyAddress(addressInfoDto);

		} catch (Exception e) {
			fail("error");
		}
	}

	@Test
	public void testSaveMyDefaultAddress() {
		try {
			AddressInfoDto addressInfoDto = new AddressInfoDto();
			addressInfoDto.setUserId(2L);
			addressInfoDto.setAddressId(2L);

			userAddressService.saveMyDefaultAddress(addressInfoDto);

			assertTrue(true);
		} catch (Exception e) {
			fail("error");
		}

	}

	@Test
	public void testDeleteMyAddress() {
		try {
			AddressInfoDto addressInfoDto = new AddressInfoDto();
			addressInfoDto.setAddressId(3L);
			userAddressService.deleteAddressById(addressInfoDto);
			assertTrue(true);
		} catch (Exception e) {
			fail("error");
		}
	}

}
