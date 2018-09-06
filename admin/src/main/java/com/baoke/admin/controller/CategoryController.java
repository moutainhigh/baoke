package com.baoke.admin.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.CategoryDictListDto;
import com.baoke.common.dto.info.CategoryDictDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.item.service.CategoryDictService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Resource
	private CategoryDictService categoryDictService;

	/**
	 * 类目查询
	 * 
	 * @author ljj
	 * @date: 2018年7月18日 下午2:31:30
	 * @param categoryDto
	 * @return
	 */
	@RequestMapping(value = "/queryCategoryList", method = RequestMethod.POST)
	public ResponseResult queryCategoryList(CategoryDictDto categoryDto) {

		try {
			CategoryDictListDto categoryDictListDto = categoryDictService.queryCategoryDict(categoryDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", categoryDictListDto.getCategoryDictList());
		} catch (ParamInvalidException e) {
			logger.error("query category list error." + categoryDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getResponseMsg());
		} catch (Exception e) {
			logger.error("query category list error." + categoryDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());

		}
	}
}
