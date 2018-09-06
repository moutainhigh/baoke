package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.DeviceInfoDto;
import com.baoke.common.exception.base.MainException;

import net.sf.cglib.beans.BeanCopier;

/**
 * 上报设备信息并获取设备CODE请求体
 * 
 * @author zjm
 * @Date: 2018年6月5日 下午7:40:40
 */
public class CreateDeviceInfoRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	/** 手机唯一标示 */
	private String uuid;

	/** */
	private String imsi;

	/** */
	private String imei;

	/** 网卡地址 */
	private String mac;

	/** 手机设备的标识 */
	private String ifa;

	/** 手机型号 */
	private String model;

	/** 渠道ID */
	private String channel;

	/** 操作系统 */
	private String os;

	/** 操作系统版本号 */
	private String osVersion;

	/** 用户安装客户端的版本号 */
	private String appVersion;

	/** 网络类型 */
	private String network;

	/** 手机屏幕分辩率 */
	private String display;

	/** 是否root/越狱 */
	private boolean isRoot;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIfa() {
		return ifa;
	}

	public void setIfa(String ifa) {
		this.ifa = ifa;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	@Override
	public BaseDto convert() throws MainException {
		DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
		final BeanCopier bc = BeanCopier.create(this.getClass(), deviceInfoDto.getClass(), false);
		bc.copy(this, deviceInfoDto, null);

		return deviceInfoDto;
	}
}
