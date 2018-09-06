package com.baoke.trade.manager;

import java.util.List;

import com.baoke.trade.domain.PostageDetail;

/**
 * 物流明细表操作
 * 
 * @author: wyj
 * @date: 2018年7月3日 下午5:42:24
 */
public interface PostageDetailManager {

	/**
	 * 根据快递单号查询物流明细(时间逆排序)
	 * 
	 * @author: wyj
	 * @date: 2018年7月4日 上午11:02:10
	 */
	List<PostageDetail> queryPostageDetailByPostageNo(String postageNo);

	/**
	 * 新增一条明细
	 * 
	 * @author: wyj
	 * @date: 2018年7月3日 下午5:43:41
	 */
	long addPostageDetail(PostageDetail postageDetail);
}
