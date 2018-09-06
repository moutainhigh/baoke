package com.baoke.log.dao;

import com.baoke.log.domain.LogStatPv;

public interface LogStatPvDao {

	int selectByScen(LogStatPv statLogPv);

	int insert(LogStatPv record);

	int updateByScen(LogStatPv statLogPv);
}