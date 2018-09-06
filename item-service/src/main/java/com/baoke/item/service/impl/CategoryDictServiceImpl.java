package com.baoke.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.dto.CategoryDictListDto;
import com.baoke.common.dto.info.CategoryDictDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.item.domain.CategoryDict;
import com.baoke.item.manager.CategoryDictManager;
import com.baoke.item.service.CategoryDictService;

@Service("categoryDictService")
public class CategoryDictServiceImpl implements CategoryDictService {

	@Resource
	private CategoryDictManager categoryDictManager;

	@Override
	public CategoryDictListDto queryCategoryDict(CategoryDictDto categoryDto) throws ParamInvalidException {
		Long parentId = categoryDto.getCategoryId();
		List<CategoryDict> categoryDict = categoryDictManager.queryCategoryDictList(parentId, BooleanEnum.TRUE);

		if (categoryDict == null || categoryDict.size() == 0) {
			throw new ParamInvalidException("暂无类目信息");
		}

		List<CategoryDictDto> categoryDictDtoList = new ArrayList<>();
		for (int i = 0; i < categoryDict.size(); i++) {
			categoryDictDtoList.add(categoryDict.get(i).convert());
		}
		CategoryDictListDto categoryDictListDto = new CategoryDictListDto(true, "");
		categoryDictListDto.setCategoryDictList(categoryDictDtoList);
		return categoryDictListDto;
	}

}
