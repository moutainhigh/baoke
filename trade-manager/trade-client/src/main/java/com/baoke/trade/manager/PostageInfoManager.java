package com.baoke.trade.manager;

import java.util.List;

import com.baoke.trade.domain.PostageInfo;

/**
 * 物流表操作
 * 
 * @author: wyj
 * @date: 2018年7月3日 下午5:41:38
 */
public interface PostageInfoManager {

	/**
	 * 根据订单号查询物流
	 * 
	 * @author: wyj
	 * @date: 2018年7月4日 上午10:55:44
	 */
	List<PostageInfo> queryPostageInfoByOrderNo(String orderNo);

	/**
	 * 新增物流信息
	 * 
	 * @author: wyj
	 * @date: 2018年7月3日 下午5:44:18
	 */
	long addPostageInfo(PostageInfo postageInfo);

}
