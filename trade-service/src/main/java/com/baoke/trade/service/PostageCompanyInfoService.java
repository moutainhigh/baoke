package com.baoke.trade.service;

import com.baoke.common.dto.PostageCompanyListDto;

/**
 * 快递公司service
 * 
 * @author zjm
 * @date: 2018年7月3日 下午7:27:42
 */
public interface PostageCompanyInfoService {

	/**
	 * 查询快递公司列表
	 * 
	 * @author zjm
	 * @date: 2018年7月3日 下午7:33:18
	 * @return
	 */
	PostageCompanyListDto queryPostageCompanyInfoList();

}
