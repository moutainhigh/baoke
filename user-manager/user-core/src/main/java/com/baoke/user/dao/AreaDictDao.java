package com.baoke.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.user.domain.AreaDictInfo;

public interface AreaDictDao {

	List<AreaDictInfo> queryAreaDictList(AreaDictInfo areaDictInfo);

	AreaDictInfo queryAreaDictByCode(@Param("code") String code, @Param("status") int status);

}
