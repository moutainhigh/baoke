package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

public class AreaDictInfoDto extends BaseResultDto {
	private static final long serialVersionUID = -8659236373933557605L;

	// 行政机构编号
	private String code;

	// 上级机构
	private String parentCode;

	// 行政区划名称
	private String name;

	// 名称首字母
	private String firstEnName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstEnName() {
		return firstEnName;
	}

	public void setFirstEnName(String firstEnName) {
		this.firstEnName = firstEnName;
	}

}
