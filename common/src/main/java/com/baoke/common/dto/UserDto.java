package com.baoke.common.dto;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.UserInfoDto;

/**
 * 用户信息
 * 
 * @author zjm
 * @date: 2018年7月4日 下午4:02:56
 */
public class UserDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	/** 用户信息 */
	private UserInfoDto userInfo;

	/** 分页数据 */
	private PageInfo pageInfo;

	public UserDto(UserInfoDto userInfoDto, PageInfo pageInfo) {
		super();
		this.userInfo = userInfoDto;
		this.pageInfo = pageInfo;
	}

	public UserInfoDto getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoDto userInfo) {
		this.userInfo = userInfo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
