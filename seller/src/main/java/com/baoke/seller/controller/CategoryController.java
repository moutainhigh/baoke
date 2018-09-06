package com.baoke.seller.controller;

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

/**
 *
 * @author ljj
 * @date: 2018年7月5日 下午3:54:22
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Resource
	private CategoryDictService categoryDictService;

	/**
	 * 查询类目分类
	 * 
	 * @author ljj
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/queryCategoryList", method = RequestMethod.POST)
	public ResponseResult queryCategoryList(CategoryDictDto categoryDto) {

		try {
			CategoryDictListDto categoryDictListDto = categoryDictService.queryCategoryDict(categoryDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", categoryDictListDto.getCategoryDictList());
		} catch (ParamInvalidException e) {
			logger.error("query categoryList error." + categoryDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("query categoryList error." + categoryDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

}
