package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.CategoryDictDto;

/**
 * 商品类目信息
 * 
 * @author zjm
 * @date: 2018年6月21日 下午5:23:18
 */
public class CategoryDictListDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private List<CategoryDictDto> categoryDictList;

	public CategoryDictListDto() {
	}

	public CategoryDictListDto(boolean success, String message) {
		super(success, message);
	}

	public List<CategoryDictDto> getCategoryDictList() {
		return categoryDictList;
	}

	public void setCategoryDictList(List<CategoryDictDto> categoryDictList) {
		this.categoryDictList = categoryDictList;
	}

}
