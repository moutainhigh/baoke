package com.baoke.user.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.user.constant.SellerStatusEnum;
import com.baoke.user.dao.SellerInfoDao;
import com.baoke.user.domain.SellerInfo;
import com.baoke.user.manager.SellerInfoManager;

/**
 * 店铺认证信息manager实现
 * 
 * @author ljj
 * @date: 2018年6月26日 下午4:45:03
 */
@Component
@DataSource("coreDataSource")
public class SellerInfoManagerImpl implements SellerInfoManager {
	@Resource
	private SellerInfoDao sellerInfoDao;

	@Override
	public SellerInfo querySellerInfoBySellerId(long sellerId) {

		return sellerInfoDao.querySellerInfoBySellerId(sellerId);
	}

	@Override
	public int queryAuditIngSellerNum(SellerStatusEnum sellerStatusEnum) {
		return sellerInfoDao.queryAuditIngSellerNum(sellerStatusEnum.getCode());
	}

	@Override
	public List<SellerInfo> querySellerInfoByNickNameAndStatus(String categoryIds, SellerStatusEnum sellerStatusEnum,
			String nickName, PageInfo pageInfo) {
		return sellerInfoDao.querySellerInfoByNickNameAndStatus(categoryIds,
				null == sellerStatusEnum ? null : sellerStatusEnum.getCode(), nickName, pageInfo);
	}

	@Override
	public int countSellerInfoByNickNameAndStatus(String categoryIds, SellerStatusEnum sellerStatusEnum,
			String nickName) {
		return sellerInfoDao.countSellerInfoByNickNameAndStatus(categoryIds,
				null == sellerStatusEnum ? null : sellerStatusEnum.getCode(), nickName);
	}

	@Override
	public int countSellerInfoByNickName(SellerStatusEnum status, String nickName) {
		return sellerInfoDao.countSellerInfoByNickName(status.getCode(), nickName);
	}

	@Override
	public int addSellerInfo(SellerInfo sellerInfo) {
		return sellerInfoDao.addSellerInfo(sellerInfo);
	}

	@Override
	public int modifySellerInfoBySellerId(SellerInfo sellerInfo) {
		return sellerInfoDao.modifySellerInfoBySellerId(sellerInfo);
	}

}
