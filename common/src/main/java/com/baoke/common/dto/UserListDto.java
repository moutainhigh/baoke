package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.UserInfoDto;

/**
 * 用户信息列表
 * 
 * @author zjm
 * @date: 2018年7月4日 下午3:26:31
 */
public class UserListDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private List<UserInfoDto> userList;

	private PageInfo pageInfo;

	public UserListDto(List<UserInfoDto> userInfoList, PageInfo pageInfo) {
		super();
		this.userList = userInfoList;
		this.pageInfo = pageInfo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<UserInfoDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInfoDto> userList) {
		this.userList = userList;
	}

}
