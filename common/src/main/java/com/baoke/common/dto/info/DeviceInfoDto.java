package com.baoke.common.dto.info;

import java.util.Date;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 上报设备信息返回
 * 
 * @author zjm
 * @Date: 2018年6月11日 下午2:10:40
 */
public class DeviceInfoDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private Long deviceId;

	/** 设备唯一码 */
	private String uuid;

	/** 应用版本 */
	private String appVersion;

	/** 渠道 */
	private String channel;

	/** 设备型号 */
	private String model;

	/** 设备系统 ios/android */
	private String os;

	/** 设备系统版本 */
	private String osVersion;

	/** 网络类型 */
	private String network;

	/** 手机屏幕分辩率 */
	private String display;

	/** 是否root/越狱 0:否；1：是 */
	private Integer root;

	/** */
	private String imsi;

	/** */
	private String imei;

	/** */
	private String mac;

	/** */
	private String ifa;

	private Date createTime;

	private Date updateTime;

	public DeviceInfoDto() {
	}

	public DeviceInfoDto(boolean success, String message, Long deviceId) {
		super(success, message);
		this.deviceId = deviceId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid == null ? null : uuid.trim();
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion == null ? null : appVersion.trim();
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel == null ? null : channel.trim();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model == null ? null : model.trim();
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os == null ? null : os.trim();
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion == null ? null : osVersion.trim();
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network == null ? null : network.trim();
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display == null ? null : display.trim();
	}

	public Integer getRoot() {
		return root;
	}

	public void setRoot(Integer root) {
		this.root = root;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi == null ? null : imsi.trim();
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei == null ? null : imei.trim();
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac == null ? null : mac.trim();
	}

	public String getIfa() {
		return ifa;
	}

	public void setIfa(String ifa) {
		this.ifa = ifa == null ? null : ifa.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
