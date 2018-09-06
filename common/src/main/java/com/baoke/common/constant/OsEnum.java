package com.baoke.common.constant;

import com.baoke.common.util.StringUtil;

/**
 * 设备系统 ios/android枚举
 */
public enum OsEnum {

	ANDROID("android", "安卓系统"),

	IOS("ios", "苹果系统");

	private String code;

	private String name;

	private OsEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 根据code获取枚举
	 * 
	 * @author wjm
	 * @date: 2018年6月15日 下午6:40:10
	 * @param code
	 * @return
	 */
	public static OsEnum getOsEnumByCode(String code) {
		for (OsEnum param : OsEnum.values()) {
			if (StringUtil.equals(param.getCode(), code)) {
				return param;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
