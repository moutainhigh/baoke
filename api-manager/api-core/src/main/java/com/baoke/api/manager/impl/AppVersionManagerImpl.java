package com.baoke.api.manager.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.api.dao.AppVersionDao;
import com.baoke.api.domain.AppVersion;
import com.baoke.api.manager.AppVersionManager;
import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.db.DataSource;

@Component
@DataSource("miscDataSource")
public class AppVersionManagerImpl implements AppVersionManager {

	@Resource
	private AppVersionDao appVersionDao;

	@Override
	public AppVersion queryNewVersionByChannelAndVersionNo(String channel, String versionNo) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("channel", channel);
		map.put("versionNo", versionNo);
		return appVersionDao.queryNewVersionByChannelAndVersionNo(map);
	}

	@Override
	public int queryNewVersionIsForceByChannelAndVersionNo(String channel, String versionNo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("channel", channel);
		map.put("versionNo", versionNo);
		map.put("isForce", String.valueOf(BooleanEnum.TRUE.getCode()));
		return appVersionDao.queryNewVersionIsForceByChannelAndVersionNo(map);
	}

}
