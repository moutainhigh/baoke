package com.baoke.user.service;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.info.DeviceInfoDto;

/**
 * 用户设备信息service
 *
 * @author zdy
 * @date: 2018年7月7日 上午14:26:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class DeviceInfoServiceTest {

	@Resource
	private DeviceInfoService deviceInfoService;

	@Test
	public void testCreateDeviceInfo() {
		DeviceInfoDto deviceInfoDtoQuery = new DeviceInfoDto();
		deviceInfoDtoQuery.setUserId(2L);
		// deviceInfoDto.setUuid("12");
		// deviceInfoDto.setDeviceId(1L);
		deviceInfoDtoQuery.setOs("ios");
		deviceInfoDtoQuery.setChannel("appStore");
		deviceInfoDtoQuery.setAppVersion("1.0");
		deviceInfoDtoQuery.setOsVersion("11.4");
		deviceInfoDtoQuery.setModel("x86_64");
		deviceInfoDtoQuery.setIfa("062A2038-E094-444D-B637-2B8DBC3DAFA5");
		deviceInfoDtoQuery.setNetwork("WIFI");

		try {
			DeviceInfoDto deviceInfoDto = deviceInfoService.createDeviceInfo(deviceInfoDtoQuery);
			System.out.println(deviceInfoDto);

			if (deviceInfoDto == null || !deviceInfoDto.isSuccess()) {
				fail("上报设备信息并获取设备CODE信息失败");
			}

			if (null == deviceInfoDto.getDeviceId() || deviceInfoDto.getDeviceId() <= 0) {
				fail("error");
			}

		} catch (Exception e) {
			fail("error");
		}
	}

}
