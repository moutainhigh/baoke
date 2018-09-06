package com.baoke.trade.dao;

import java.util.List;

import com.baoke.trade.domain.PostageInfo;

/**
 * 物流表操作
 * 
 * @author: wyj
 * @date: 2018年7月3日 下午5:45:17
 */
public interface PostageInfoDao {

	List<PostageInfo> queryPostageInfoByOrderNo(String orderNo);

	int addPostageInfo(PostageInfo postageInfo);

}
