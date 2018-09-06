package com.baoke.common.constant;

/**
 * BANNER类型
 */
public enum BannerScenTypeEnum {

	APP_INDEX_TOP_BANNER("APP_INDEX_TOP", "APP首页banner"),

	APP_STARTPAGE_ADVERT("APP_STARTPAGE_ADVERT", "APP启动页");

	private String name;

	private String desc;

	BannerScenTypeEnum(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
