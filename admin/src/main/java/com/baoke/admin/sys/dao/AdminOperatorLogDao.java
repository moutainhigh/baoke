package com.baoke.admin.sys.dao;

import java.util.List;
import java.util.Map;

import com.baoke.admin.sys.domain.AdminOperatorLog;

public interface AdminOperatorLogDao {

	public void addAdminOperatorLog(Map<String, Object> map);

	public void updateAdminOperatorLog(Map<String, Object> map);

	public List<AdminOperatorLog> getAdminOperatorLogByCondition(Map<String, Object> map);

	public int countAdminOperatorLogByCondition(Map<String, String> map);
}