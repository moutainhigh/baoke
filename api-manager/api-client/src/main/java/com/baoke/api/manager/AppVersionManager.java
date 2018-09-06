package com.baoke.api.manager;

import com.baoke.api.domain.AppVersion;

/**
 * 新版本号操作
 * 
 * @author wyh
 * @date 2018年6月11日 下午9:00:44
 *
 */
public interface AppVersionManager {

	/**
	 * 查询当前渠道的最新版本号
	 * 
	 * @author wyh
	 * @date 2018年6月11日 下午9:05:10
	 * 
	 * @param channel
	 * @param versionNo
	 * @return
	 */
	public AppVersion queryNewVersionByChannelAndVersionNo(String channel, String versionNo);

	/**
	 * 查询是否需要强制升级
	 * 
	 * @author wyh
	 * @date 2018年6月13日 下午3:41:39
	 * 
	 * @param channel
	 * @param versionNo
	 * @return
	 */
	public int queryNewVersionIsForceByChannelAndVersionNo(String channel, String versionNo);

}
