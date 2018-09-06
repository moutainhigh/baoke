package com.baoke.log.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.log.dao.LogStatPvDao;
import com.baoke.log.domain.LogStatPv;
import com.baoke.log.manager.LogStatPvManager;

@Component
@DataSource("logDataSource")
public class LogStatPvManagerImpl implements LogStatPvManager {
	@Resource
	private LogStatPvDao logStatPvDao;

	@Override
	public int selectByScen(LogStatPv logStatPv) {
		return logStatPvDao.selectByScen(logStatPv);
	}

	@Override
	public int insert(LogStatPv logStatPv) {
		return logStatPvDao.insert(logStatPv);
	}

	@Override
	public int updateByScen(LogStatPv logStatPv) {
		return logStatPvDao.updateByScen(logStatPv);
	}

}
