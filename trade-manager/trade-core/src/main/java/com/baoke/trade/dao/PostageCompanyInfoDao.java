package com.baoke.trade.dao;

import java.util.List;

import com.baoke.trade.domain.PostageCompanyInfo;

public interface PostageCompanyInfoDao {

	PostageCompanyInfo queryPostageCompanyInfoByCode(String code);

	List<PostageCompanyInfo> queryPostageCompanyInfoList();

	int addPostageCompanyInfo(PostageCompanyInfo postageCompanyInfo);

}