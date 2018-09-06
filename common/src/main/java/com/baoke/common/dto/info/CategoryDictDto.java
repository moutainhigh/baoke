package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 类目
 * 
 * @author zjm
 * @date: 2018年6月21日 下午5:21:58
 */
public class CategoryDictDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private Long categoryId;

	/** 类目名称 */
	private String categoryName;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
