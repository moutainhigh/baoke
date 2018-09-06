package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.PostageCompanyInfoDto;

/**
 * 快递公司列表
 * 
 * @author zjm
 * @date: 2018年7月3日 下午7:32:06
 */
public class PostageCompanyListDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	/** 快递公司列表 */
	List<PostageCompanyInfoDto> postageCompanyList;

	public PostageCompanyListDto(List<PostageCompanyInfoDto> postageCompanyList) {
		this.postageCompanyList = postageCompanyList;
	}

	public PostageCompanyListDto(boolean success, String message, List<PostageCompanyInfoDto> postageCompanyList) {
		super(success, message);
		this.postageCompanyList = postageCompanyList;
	}

	public List<PostageCompanyInfoDto> getPostageCompanyList() {
		return postageCompanyList;
	}

	public void setPostageCompanyList(List<PostageCompanyInfoDto> postageCompanyList) {
		this.postageCompanyList = postageCompanyList;
	}

}
