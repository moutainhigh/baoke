package com.baoke.item.constant;

/**
 * 查询视频详情页场景定义（需要返回不同的视频列表）
 * 
 * @date 2018年7月17日 上午11:07:00
 *
 */
public enum VideoDetailQueryScenEnum {

	INDEX_RECOMMEND(10, "首页推荐"),

	INDEX_FOLLOW(11, "首页关注"),

	SELLER_HOME(20, "主播首页"),

	MY_LIKE(30, "我喜欢的视频"),

	SINGLE_VIDEO(66, "单个视频详情");

	private int code;

	private String name;

	VideoDetailQueryScenEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 根据code获取枚举
	 */
	public static VideoStatusEnum getVideoStatusEnumByCode(Integer code) {
		for (VideoStatusEnum param : VideoStatusEnum.values()) {
			if (param.getCode() == code) {
				return param;
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
