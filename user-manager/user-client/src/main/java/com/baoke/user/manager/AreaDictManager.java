package com.baoke.user.manager;

import java.util.List;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.user.domain.AreaDictInfo;

/**
 * 地区字典表manager
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:31:59
 */
public interface AreaDictManager {

	/**
	 * 根据地区编码获取地区信息
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:32:13
	 */
	AreaDictInfo queryAddressByCode(String code, BooleanEnum booleanEnum);

	/**
	 * 根据parentCode查询地区字典集合
	 * 
	 * @author zdy
	 * @date: 2018年7月9日 下午5:43:43
	 * @param parentCode
	 * @return
	 */
	List<AreaDictInfo> queryAddressDictByParentCode(String parentCode);
}
