package com.baoke.interact.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.domain.SellerFocus;

/**
 * 关注商户
 * 
 * @author zdy
 * @date 2018年6月15日 下午1:52:28
 */
public interface SellerFocusDao {
	List<SellerFocus> querySellerFocusPage(@Param("sellerFocus") SellerFocus sellerFocus,
			@Param("pageInfo") PageInfo pageInfo);

	List<SellerFocus> querySellerFocusList(SellerFocus sellerFocus);

	int queryLastdayAddFansNum(@Param("sellerId") long sellerId, @Param("status") Integer status);

	int countSellerFocus(SellerFocus sellerFocus);

	List<Long> queryAllFocusID(@Param("sellerId") long sellerId, @Param("status") Integer status);

	int addSellerFocus(SellerFocus sellerFocus);

	int modifySellerFocus(SellerFocus sellerFocus);

}