package com.baoke.trade.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.trade.dao.PostageDetailDao;
import com.baoke.trade.domain.PostageDetail;
import com.baoke.trade.manager.PostageDetailManager;

/**
 * 物流明细操作实现
 * 
 * @author: wyj
 * @date: 2018年7月3日 下午5:47:21
 */
@Component
@DataSource("coreDataSource")
public class PostageDetailManagerImpl implements PostageDetailManager {

	@Resource
	private PostageDetailDao postageDetailDao;

	@Override
	public List<PostageDetail> queryPostageDetailByPostageNo(String postageNo) {
		return postageDetailDao.queryPostageDetailByPostageNo(postageNo);
	}

	@Override
	public long addPostageDetail(PostageDetail postageDetail) {
		postageDetailDao.addPostageDetail(postageDetail);
		return postageDetail.getId();
	}

}
