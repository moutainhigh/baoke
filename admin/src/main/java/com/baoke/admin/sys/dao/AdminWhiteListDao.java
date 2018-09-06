package com.baoke.admin.sys.dao;

import java.util.List;
import java.util.Map;

import com.baoke.admin.sys.domain.AdminWhiteList;

public interface AdminWhiteListDao {

	public List<AdminWhiteList> getAdminWhiteListByIsLogin(int isLogin);

	public List<AdminWhiteList> getAdminWhiteListByCondition(Map<String, Object> map);

}