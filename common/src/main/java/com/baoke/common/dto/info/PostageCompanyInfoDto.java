package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 快递公司Dto
 * 
 * @author zjm
 * @date: 2018年7月3日 下午7:29:55
 */
public class PostageCompanyInfoDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private Long postageCompanyId;

	private String code;

	/** 简称 */
	private String simpleName;

	/** 全称 */
	private String name;

	/** logo地址 */
	private String logoUrl;

	public Long getPostageCompanyId() {
		return postageCompanyId;
	}

	public void setPostageCompanyId(Long postageCompanyId) {
		this.postageCompanyId = postageCompanyId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName == null ? null : simpleName.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl == null ? null : logoUrl.trim();
	}

}
