package com.baoke.trade.manager;

import java.util.List;

import com.baoke.trade.domain.PostageCompanyInfo;

/**
 * 快递公司manager
 * 
 * @author zjm
 * @date: 2018年7月3日 下午7:24:01
 */
public interface PostageCompanyInfoManager {

	/**
	 * 查询快递公司列表
	 * 
	 * @author zjm
	 * @date: 2018年7月3日 下午7:24:25
	 * @return
	 */
	List<PostageCompanyInfo> queryPostageCompanyInfoList();

	/**
	 * 根据Code或区物流公司
	 * 
	 * @author: wyj
	 * @date: 2018年7月4日 上午11:13:55
	 */
	PostageCompanyInfo queryPostageCompanyInfoByCode(String company);

}
