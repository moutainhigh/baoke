package com.baoke.item.manager;

import com.baoke.item.domain.ItemPropDict;

/**
 * 商品属性Manager
 * 
 * @author: zdy
 * @date: 2018年6月13日 上午11:34:27
 */
public interface ItemPropDictManager {

	/**
	 * 根据ID查询商品属性对象
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午1:47:38
	 * @param id
	 * @return
	 */
	ItemPropDict queryItemPropDictById(long id);

}
