package com.baoke.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.user.domain.SellerInfo;

/**
 * 店铺认证信息查询
 * 
 * @author ljj
 * @date: 2018年6月26日 下午5:22:10
 */
public interface SellerInfoDao {

	SellerInfo querySellerInfoBySellerId(long sellerId);

	List<SellerInfo> querySellerInfoByNickNameAndStatus(@Param("categoryIds") String categoryIds,
			@Param("status") Integer status, @Param("nickName") String nickName,
			@Param(value = "pageInfo") PageInfo pageInfo);

	int countSellerInfoByNickNameAndStatus(@Param("categoryIds") String categoryIds, @Param("status") Integer status,
			@Param("nickName") String nickName);

	int countSellerInfoByNickName(@Param("status") Integer status, @Param("nickName") String nickName);

	int queryAuditIngSellerNum(Integer status);

	int addSellerInfo(SellerInfo SellerInfo);

	int modifySellerInfoBySellerId(SellerInfo sellerInfo);

}
