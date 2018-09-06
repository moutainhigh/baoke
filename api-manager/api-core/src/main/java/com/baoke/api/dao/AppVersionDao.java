package com.baoke.api.dao;

import java.util.Map;

import com.baoke.api.domain.AppVersion;

/**
 * App 版本号Dao
 * 
 * @author wyh
 * @date: 2018年6月19日 下午9:05:45
 */
public interface AppVersionDao {

	public AppVersion queryNewVersionByChannelAndVersionNo(Map<String, String> map);
	
	public int queryNewVersionIsForceByChannelAndVersionNo(Map<String, String> map);

}
