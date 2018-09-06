package com.baoke.user.manager;

import com.baoke.user.domain.DeviceInfo;

/**
 * 设备信息manager
 * 
 * @author zjm
 * @Date: 2018年6月5日 下午8:02:53
 */
public interface DeviceInfoManager {

	/**
	 * 查询设备信息
	 * 
	 * @author wyh
	 * @date 2018年6月13日 下午5:14:34
	 * 
	 * @param id
	 * @return
	 */
	DeviceInfo queryDeviceInfoById(long id);

	/**
	 * 根据条件查询设备信息
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:23:18
	 * @param deviceInfo
	 * @return
	 */
	DeviceInfo queryDeviceInfo(DeviceInfo deviceInfo);

	/**
	 * 保存设备信息并返回设备码
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:23:08
	 * @param deviceInfo
	 * @return
	 */
	long addDeviceInfo(DeviceInfo deviceInfo);

	/**
	 * 更新设备信息
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午2:23:24
	 * @param deviceInfo
	 * @return
	 */
	int modifyDeviceInfo(DeviceInfo deviceInfo);

}
