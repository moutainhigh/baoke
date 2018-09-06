package com.baoke.admin.sys.manager.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.admin.sys.dao.AdminOperatorLogDao;
import com.baoke.admin.sys.domain.AdminOperatorLog;
import com.baoke.admin.sys.manager.AdminOperatorLogManager;
import com.baoke.common.db.DataSource;

/**
 * 操作日志manager实现
 * 
 * @author zjm
 * @date: 2018年7月19日 上午11:13:30
 */
@DataSource(value = "miscDataSource")
@Component("adminOperatorLogManager")
public class AdminOperatorLogManagerImpl implements AdminOperatorLogManager {

	@Resource
	private AdminOperatorLogDao adminOperatorLogDao;

	@Override
	public void addAdminOperatorLog(Map<String, Object> map) {
		adminOperatorLogDao.addAdminOperatorLog(map);
	}

	@Override
	public void updateAdminOperatorLog(Map<String, Object> map) {
		adminOperatorLogDao.updateAdminOperatorLog(map);
	}

	@Override
	public List<AdminOperatorLog> getAdminOperatorLogByCondition(Map<String, Object> map) {
		return adminOperatorLogDao.getAdminOperatorLogByCondition(map);
	}

	@Override
	public int countAdminOperatorLogByCondition(Map<String, String> map) {
		return adminOperatorLogDao.countAdminOperatorLogByCondition(map);
	}

}
