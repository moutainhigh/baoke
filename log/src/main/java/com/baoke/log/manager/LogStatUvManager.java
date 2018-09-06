package com.baoke.log.manager;

import com.baoke.log.domain.LogStatUv;

public interface LogStatUvManager {

	int selectByScen(LogStatUv logStatUv);

	int insert(LogStatUv logStatUv);

	int updateByScen(LogStatUv logStatUv);

}