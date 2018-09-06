package com.baoke.user.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.dto.FileDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.util.DateUtil;

/**
 * 用户Managerservice实现的测试类
 *
 * @author lcl
 * @date 2018年7月19日 下午4:00:52
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*.xml" })
public class UserInfoManagerServiceTest {

	@Resource
	private UserInfoManagerService userInfoManagerService;

	/**
	 * 设置－保存用户性别
	 *
	 * @author zdy
	 * @date: 2018年7月12日 下午7:41:45
	 */
	@Test
	public void testSaveUserSex() {
		try {
			UserInfoDto userInfoDto = new UserInfoDto();
			userInfoDto.setUserId(2L);
			userInfoDto.setSex(2);

			BaseResultDto baseResultDto = userInfoManagerService.saveUserSex(userInfoDto);
			System.out.println(baseResultDto);

			assertNotNull("查询数据不为空", baseResultDto);

		} catch (Exception e) {
			fail("error");
		}
	}

	/**
	 * 设置－保存用户昵称
	 *
	 * @author zdy
	 * @date: 2018年7月12日 下午7:41:45
	 */
	@Test
	public void testSaveUserNickName() {
		try {
			UserInfoDto userInfoDto = new UserInfoDto();
			userInfoDto.setUserId(100006L);
			userInfoDto.setNickName("小姐姐1");

			BaseResultDto baseResultDto = userInfoManagerService.saveUserNickName(userInfoDto);
			System.out.println(baseResultDto);

			assertNotNull("查询数据不为空", baseResultDto);

		} catch (Exception e) {
			fail("测试失败");
		}
	}

	@Test
	public void testUploadUserHeadImg() {
		try {
			FileDto fileDto = new FileDto();
			// fileDto.setFile(base64Str.getBytes());
			fileDto.setFileType("image/jpeg");
			fileDto.setUserId(2L);

			UserInfoDto userInfoDto = userInfoManagerService.uploadUserHeadImg(fileDto);
			System.out.println(userInfoDto);

			assertNotNull(userInfoDto);
		} catch (Exception e) {
			fail("error");
		}
	}

	@Test
	public void testBannedUserByIds() throws Exception {

		try {
			CommonQueryDto commonQueryDto = new CommonQueryDto();
			commonQueryDto.setStatus(1);// 1禁言 0未禁言
			commonQueryDto.setUserBannedEndTime(DateUtil.parseForSecond("2018-07-26 00:00:00").getTime());// 当前时间
			List<Long> list = new ArrayList<>();
			list.add(100012L);
			commonQueryDto.setIds(list);

			BaseDto baseDto = userInfoManagerService.bannedUserByIds(commonQueryDto);
			System.out.println(baseDto);

			assertNotNull("用户信息不为空", baseDto);
		} catch (ParamInvalidException e) {
			fail("error");

		}

	}

}
