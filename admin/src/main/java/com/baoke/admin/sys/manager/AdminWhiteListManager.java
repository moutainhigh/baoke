package com.baoke.admin.sys.manager;

import java.util.List;
import java.util.Map;

import com.baoke.admin.sys.domain.AdminWhiteList;

/**
 * 白名单 manager
 * 
 * @author zjm
 * @date: 2018年7月19日 上午11:13:00
 */
public interface AdminWhiteListManager {

	List<AdminWhiteList> getAdminWhiteListByIsLogin(int isLogin);

	List<AdminWhiteList> getAdminWhiteListByCondition(Map<String, Object> map);

}
