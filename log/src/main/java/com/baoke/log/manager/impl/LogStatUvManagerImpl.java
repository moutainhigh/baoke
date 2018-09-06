package com.baoke.log.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.log.dao.LogStatUvDao;
import com.baoke.log.domain.LogStatUv;
import com.baoke.log.manager.LogStatUvManager;

@Component
@DataSource("logDataSource")
public class LogStatUvManagerImpl implements LogStatUvManager {
	@Resource
	private LogStatUvDao logStatUvDao;

	@Override
	public int selectByScen(LogStatUv logStatUv) {
		return logStatUvDao.selectByScen(logStatUv);
	}

	@Override
	public int insert(LogStatUv logStatUv) {
		return logStatUvDao.insert(logStatUv);
	}

	@Override
	public int updateByScen(LogStatUv logStatUv) {
		return logStatUvDao.updateByScen(logStatUv);
	}

}
