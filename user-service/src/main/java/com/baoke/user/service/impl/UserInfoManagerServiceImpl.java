package com.baoke.user.service.impl;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.UploadFileEnum;
import com.baoke.common.constant.config.CommonConfig;
import com.baoke.common.domain.message.SiteMessage;
import com.baoke.common.dto.FileDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.DateUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.common.util.UploadFileUtil;
import com.baoke.user.constant.UserBannedEnum;
import com.baoke.user.constant.UserStatusEnum;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.SendSiteManager;
import com.baoke.user.manager.UserInfoManager;
import com.baoke.user.service.UserInfoManagerService;

@Service("userInfoManagerService")
@ServiceDefinition(value = "userInfoManagerService")
public class UserInfoManagerServiceImpl implements UserInfoManagerService {

	private static final Logger logger = Logger.getLogger(UserInfoManagerServiceImpl.class);

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private SendSiteManager sendSiteManager;

	@MethodDefinition(value = "saveUserSex", needLogin = true)
	@Override
	public BaseResultDto saveUserSex(UserInfoDto userInfoDto) throws MainException {
		if (null == userInfoDto || null == userInfoDto.getUserId()) {
			throw new ParamInvalidException("userId不能为空！");
		}

		if (userInfoDto.getSex() == null) {
			throw new ParamInvalidException("参数错误！");
		}

		UserInfo userInfo = new UserInfo(userInfoDto.getUserId(), userInfoDto.getSex());
		int result = userInfoManager.modifyUserInfoById(userInfo);
		if (result > 0) {
			return new BaseResultDto(true, null);
		}
		return new BaseResultDto(false, "保存失败");
	}

	@MethodDefinition(value = "saveUserNickName", needLogin = true)
	@Override
	public BaseResultDto saveUserNickName(UserInfoDto userInfoDto) throws MainException {
		UserInfo userInfo = userInfoManager.queryUserInfoById(userInfoDto);

		Date createTime = userInfo.getCreateTime();
		Date nickNameUpdateTime = userInfo.getNickNameUpdateTime();
		// 除首次修改外，每个自然月只能修改一次昵称
		if (null != nickNameUpdateTime && !DateUtil.format(createTime).equals(DateUtil.format(nickNameUpdateTime))) {
			if (DateUtil.dateYearMonthCompare(nickNameUpdateTime, createTime)) {
				return new BaseResultDto(false, "每个月只能修改一次昵称");
			}
		}

		// 昵称修改输入只支持中文、大小写英文和阿拉伯数字（不能使用符号和表情），修改昵称的时候系统检测提示是否重复。用户昵称设置长度为8个汉字
		if (StringUtil.isEmpty(userInfoDto.getNickName())) {
			throw new ParamInvalidException("参数错误！");
		}

		// && userInfoDto.getNickName().length() <= 8
		if (StringUtil.validateCharNumChinese(userInfoDto.getNickName())
				|| StringUtil.byteLengthByCode(userInfoDto.getNickName()) > CommonConfig.NICKNAME_MAXLENGTH) {
			throw new ParamInvalidException("昵称不符合规范！");
		}

		int countNum = userInfoManager.countUserInfoByNickName(userInfoDto.getNickName(), UserStatusEnum.NORMAL);
		if (countNum <= 0) {
			throw new ParamInvalidException("该昵称已存在");
		}

		int result = userInfoManager.modifyUserNickNameById(userInfo.getId(), userInfoDto.getNickName());
		if (result > 0) {
			return new BaseResultDto(true, "修改成功");
		}
		return new BaseResultDto(false, "保存失败");
	}

	@MethodDefinition(value = "uploadUserHeadImg", needLogin = true)
	@Override
	public UserInfoDto uploadUserHeadImg(FileDto fileDto) throws MainException, IOException {
		if (fileDto == null || fileDto.getUserId() == null) {
			throw new ParamInvalidException("登陆已过期，请重新登陆！");
		}

		if (fileDto.getFile() == null || fileDto.getFile().length <= 0) {
			throw new ParamInvalidException("请上传您的头像！");
		}

		String url = UploadFileUtil.uploadTencentCloud(fileDto.getFile(), fileDto.getFileType(), fileDto.getUserId(),
				UploadFileEnum.USER_NICK)[0];

		if (StringUtil.isNotBlank(url)) {
			// 保存头像地址
			int result = userInfoManager.modifyUserHeadImgById(fileDto.getUserId(), url);
			if (result > 0) {
				UserInfoDto userInfoDto = new UserInfoDto(true, null);
				userInfoDto.setHeadImgUrl(url);
				return userInfoDto;
			}
		}
		return new UserInfoDto(false, "上传失败！");
	}

	@Override
	public BaseDto bannedUserByIds(CommonQueryDto commonQueryDto) throws ParamInvalidException {

		if (null == commonQueryDto || null == commonQueryDto.getStatus()) {
			logger.error("UserInfoManagerService.banned error , UserInfoDto ParamInvalidException ");
			throw new ParamInvalidException("参数不能为空");
		}

		if (null == commonQueryDto.getUserBannedEndTime()
				&& commonQueryDto.getStatus() == UserBannedEnum.YES.getCode()) {
			logger.error(
					MessageFormat.format("UserInfoManagerService.banned error , BannedEndTime invalid , userId={0}",
							commonQueryDto.getUserId()));
			throw new ParamInvalidException("禁言时间不能为空");
		}

		if (!UserBannedEnum.isExists(commonQueryDto.getStatus())) {
			logger.error(MessageFormat.format(
					"UserInfoManagerService.banned error , BannedStatus invalid , BannedStatus={0}",
					commonQueryDto.getStatus()));
			throw new ParamInvalidException("状态值非法");
		}

		if (null == commonQueryDto.getIds() || commonQueryDto.getIds().size() <= 0) {
			logger.error("UserInfoManagerService.banned error , userIds invalid");
			throw new ParamInvalidException("用户为空");
		}
		// 解除禁言
		if (commonQueryDto.getStatus() == UserBannedEnum.NO.getCode()) {
			commonQueryDto.setUserBannedReason(null);
			commonQueryDto.setUserBannedEndTime(new Date().getTime());
		}
		// 永久禁言
		if (commonQueryDto.getStatus() == UserBannedEnum.ALWAYS.getCode()) {
			try {
				commonQueryDto.setUserBannedEndTime(DateUtil.parseForSecond(CommonConfig.DEFAULT_END_DATE).getTime());
			} catch (ParseException e) {
				logger.error("UserInfoManagerService banned error.", e);
			}
		}

		userInfoManager.modifyUserInfoByIds(commonQueryDto.getIds(), new Date(commonQueryDto.getUserBannedEndTime()),
				commonQueryDto.getStatus(), commonQueryDto.getUserBannedReason());

		String content = "";
		if (commonQueryDto.getStatus() == UserBannedEnum.YES.getCode()
				|| commonQueryDto.getStatus() == UserBannedEnum.ALWAYS.getCode()) {
			content = "已禁言，禁言原因：" + commonQueryDto.getUserBannedReason() + "，禁言截止时间："
					+ DateUtil.format(new Date(commonQueryDto.getUserBannedEndTime()), DateUtil.DATE_TIME_FORMAT_STR);
		} else {

			content = "解除禁言！";
		}
		for (Long userId : commonQueryDto.getIds()) {
			SiteMessage siteMessage = SiteMessage.createSystemSiteMessage(CommonConfig.SYSTEM_USER_ID, userId, null,
					"用户禁言", content);
			sendSiteManager.sendSite(siteMessage);
		}

		return new BaseDto();
	}

	@Override
	public int modifyUserBeBannedStatusByJob() {

		return userInfoManager.modifyUserBeBannedStatusByJob();
	}

}
