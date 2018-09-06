package com.baoke.trade.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.trade.dao.PostageInfoDao;
import com.baoke.trade.domain.PostageInfo;
import com.baoke.trade.manager.PostageInfoManager;

/**
 * 物流操作实现
 * 
 * @author: wyj
 * @date: 2018年7月3日 下午5:47:59
 */

@Component
@DataSource("coreDataSource")
public class PostageInfoManagerImpl implements PostageInfoManager {
	@Resource
	private PostageInfoDao postageInfoDao;

	@Override
	public List<PostageInfo> queryPostageInfoByOrderNo(String orderNo) {
		return postageInfoDao.queryPostageInfoByOrderNo(orderNo);
	}

	@Override
	public long addPostageInfo(PostageInfo postageInfo) {
		postageInfoDao.addPostageInfo(postageInfo);
		return postageInfo.getId();
	}

}
