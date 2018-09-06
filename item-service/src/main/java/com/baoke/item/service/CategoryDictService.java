package com.baoke.item.service;

import com.baoke.common.dto.CategoryDictListDto;
import com.baoke.common.dto.info.CategoryDictDto;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 类目列表
 * 
 * @author ljj
 * @date: 2018年7月5日 下午8:42:53
 */
public interface CategoryDictService {

	CategoryDictListDto queryCategoryDict(CategoryDictDto categoryDto) throws ParamInvalidException;

}
