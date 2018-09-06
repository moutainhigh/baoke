package com.baoke.item.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.item.domain.CategoryDict;

/**
 * 商品分类
 * 
 * @author ljj
 * @date: 2018年6月27日 下午12:26:24
 */
public interface CategoryDictDao {

	/**
	 * 类目列表
	 * 
	 * @author ljj
	 * @date: 2018年7月4日 下午1:26:24
	 */
	List<CategoryDict> queryCategoryDictList(@Param("parentId") Long parentId, @Param("status") Integer status);

	String queryCategoryDictByids(@Param("ids") List<String> ids);

}
