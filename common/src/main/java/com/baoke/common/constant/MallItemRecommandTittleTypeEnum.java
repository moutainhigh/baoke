package com.baoke.common.constant;

/**
 * 商城明星推荐表父子标题枚举
 * 
 */
public enum MallItemRecommandTittleTypeEnum {

	PARENT(0, "父标题");

	private long code;

	private String name;

	private MallItemRecommandTittleTypeEnum(long code, String name) {
		this.code = code;
		this.name = name;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
