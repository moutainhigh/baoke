package com.baoke.user.service;

import com.baoke.common.dto.SellerAuditListDto;
import com.baoke.common.dto.info.SellerAuditInfoDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.dto.seller.SellerAudioStatusDto;
import com.baoke.common.exception.base.MainException;

/**
 * 店铺认证相关service
 * 
 * @author ljj
 * @date: 2018年6月26日 下午4:14:02
 */
public interface SellerInfoService {
	/**
	 * 主播认证信息
	 * 
	 * @author ljj
	 * @date: 2018年6月26日 下午4:14:02
	 */
	SellerAuditInfoDto querySellerAuditInfoBySellerId(Long sellerId) throws MainException;

	/**
	 * 分页查询主播认证信息
	 * 
	 * @author ljj
	 * @date: 2018年7月7日 下午8:05:04
	 * @param sellerInfoDto
	 *            nickName 不为空
	 * @return
	 * @throws MainException
	 */
	SellerAuditListDto querySellerAuditInfoByName(SellerInfoDto sellerInfoDto) throws MainException;

	/**
	 * 主播认证状态
	 * 
	 * @author ljj
	 * @date: 2018年6月26日 下午4:14:02
	 */
	SellerAudioStatusDto querySellerAudioStatus(long sellerId) throws MainException;

	/**
	 * 认证是添加主播信息
	 * 
	 * @author ljj
	 * @date: 2018年6月26日 下午4:14:02
	 */
	int saveSellerInfo(SellerAuditInfoDto sellerAuditInfoDto) throws MainException;

	/**
	 * 修改主播的认证状态
	 * 
	 * @author ljj
	 * @date: 2018年7月9日 上午10:05:04
	 * @param sellerInfoDto
	 *            id不能为空
	 * @return
	 * @throws MainException
	 */
	boolean modifySellerAuditResult(SellerAuditInfoDto sellerAuditInfoDto) throws MainException;
}
