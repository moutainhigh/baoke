package com.baoke.item.manager;

import java.util.List;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.item.domain.CategoryDict;

/**
 * 商品类目字典
 * 
 * @author ljj
 * @date: 2018年6月27日 下午12:33:49
 */
public interface CategoryDictManager {

	/**
	 * 商品类目查询
	 * 
	 * @author: ljj
	 * @date: 2018年7月4日 上午11:33:25
	 * @param parentId
	 *            如果为空，表示查询全部类目
	 * @return
	 */
	List<CategoryDict> queryCategoryDictList(Long parentId, BooleanEnum booleanEnum);

	/**
	 * 根据ID查询类目名称
	 * 
	 * @author ljj
	 * @date 2018年7月4日 上午11:33:25
	 * 
	 * @param ids
	 * @return
	 */
	String queryCategoryDictByids(List<String> ids);

}
