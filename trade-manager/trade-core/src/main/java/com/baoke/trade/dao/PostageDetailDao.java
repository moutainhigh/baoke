package com.baoke.trade.dao;

import java.util.List;

import com.baoke.trade.domain.PostageDetail;

/**
 * 物流明细操作
 * 
 * @author: wyj
 * @date: 2018年7月3日 下午5:46:18
 */
public interface PostageDetailDao {

	List<PostageDetail> queryPostageDetailByPostageNo(String postageNo);

	int addPostageDetail(PostageDetail postageDetail);

}
