package com.baoke.user.manager;

import java.util.List;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.user.constant.SellerStatusEnum;
import com.baoke.user.domain.SellerInfo;

/**
 * 认证相关查询
 * 
 * @author ljj
 * @date: 2018年6月26日 下午4:35:20
 */
public interface SellerInfoManager {

	/**
	 * 根据sellerId查询主播信息
	 * 
	 * @author zjm
	 * @date: 2018年7月18日 下午1:55:08
	 * @param sellerId
	 * @return
	 */
	SellerInfo querySellerInfoBySellerId(long sellerId);

	/**
	 * 根据昵称和状态分页查询主播信息
	 * 
	 * @author zjm
	 * @date: 2018年7月18日 下午1:55:31
	 * @param sellerStatusEnum
	 * @param nickName
	 * @param pageInfo
	 * @return
	 */
	List<SellerInfo> querySellerInfoByNickNameAndStatus(String categoryIds, SellerStatusEnum sellerStatusEnum,
			String nickName, PageInfo pageInfo);

	/**
	 * 根据昵称和状态分页查询总数
	 * 
	 * @author zjm
	 * @date: 2018年7月18日 下午1:56:04
	 * @param nickName
	 * @return
	 */
	int countSellerInfoByNickNameAndStatus(String categoryIds, SellerStatusEnum sellerStatusEnum, String nickName);

	/**
	 * 根据准确昵称查询主播数量
	 * 
	 * @author zjm
	 * @date: 2018年7月24日 下午4:44:57
	 * @param status
	 * @param nickName
	 * @return
	 */
	int countSellerInfoByNickName(SellerStatusEnum status, String nickName);

	/**
	 * 根据状态查询审核中的主播数量
	 * 
	 * @author zjm
	 * @date: 2018年7月18日 下午1:56:43
	 * @param sellerStatusEnum
	 * @return
	 */
	int queryAuditIngSellerNum(SellerStatusEnum sellerStatusEnum);

	/**
	 * 新增
	 * 
	 * @author zjm
	 * @date: 2018年7月18日 下午1:57:53
	 * @param sellerInfo
	 * @return
	 */
	int addSellerInfo(SellerInfo sellerInfo);

	/**
	 * 根据sellerId修改主播信息
	 * 
	 * @author zjm
	 * @date: 2018年7月18日 下午1:58:09
	 * @param sellerInfo
	 * @return
	 */
	int modifySellerInfoBySellerId(SellerInfo sellerInfo);
}
