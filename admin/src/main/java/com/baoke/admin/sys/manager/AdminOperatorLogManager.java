package com.baoke.admin.sys.manager;

import java.util.List;
import java.util.Map;

import com.baoke.admin.sys.domain.AdminOperatorLog;

/**
 * 操作日志manager
 * 
 * @author zjm
 * @date: 2018年7月19日 上午11:11:16
 */
public interface AdminOperatorLogManager {

	void addAdminOperatorLog(Map<String, Object> map);

	void updateAdminOperatorLog(Map<String, Object> map);

	List<AdminOperatorLog> getAdminOperatorLogByCondition(Map<String, Object> map);

	int countAdminOperatorLogByCondition(Map<String, String> map);

}
