package com.baoke.item.service;

import com.baoke.common.dto.info.MallRecommendInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 商城managerService
 * 
 * @author: wyj
 * @date: 2018年7月10日 下午3:28:15
 */
public interface MallManagerService {

	/**
	 * 保存明星推荐
	 * 
	 * @author: wyj
	 * @date: 2018年7月10日 下午3:33:58
	 */
	void saveMallRecommendInfo(MallRecommendInfoDto mallRecommendInfoDto) throws MainException;

}
