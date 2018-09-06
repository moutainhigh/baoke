package com.baoke.api.service;

import com.baoke.common.dto.api.AppVersionDto;
import com.baoke.common.exception.base.MainException;

public interface VersionService {
	
	/**
	 * 新版本查询
	 * 
	 * @author wyh
	 * @date 2018年6月9日 下午9:39:09
	 * 
	 * @param currentVersion
	 * @param channel
	 * @return
	 */
	public AppVersionDto queryNewVersion(AppVersionDto appVersionDto) throws MainException;

}
