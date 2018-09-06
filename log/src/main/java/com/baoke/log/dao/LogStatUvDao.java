package com.baoke.log.dao;

import com.baoke.log.domain.LogStatUv;

public interface LogStatUvDao {

	int selectByScen(LogStatUv statLogUv);

	int insert(LogStatUv record);

	int updateByScen(LogStatUv statLogUv);

}