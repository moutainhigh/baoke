package com.baoke.log.manager;

import com.baoke.log.domain.LogStatPv;

public interface LogStatPvManager {

	int selectByScen(LogStatPv logStatPv);

	int insert(LogStatPv logStatPv);

	int updateByScen(LogStatPv logStatPv);
}