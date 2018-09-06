package com.baoke.interact.constant;

/**
 * 关注卖家
 */
public enum SellerFocusStatusEnum {

	UNFOCUS(0, "取消关注"),

	FOCUS(1, "关注");

	private int code;

	private String name;

	private SellerFocusStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 
	 * 根据code获取枚举
	 * 
	 * date: 2018年6月5日 上午10:13:49
	 * 
	 * @author zjm
	 * @param code
	 * @return
	 */
	public static SellerFocusStatusEnum getSellerFocusEnumByCode(Integer code) {
		if (null == code) {
			return null;
		}
		for (SellerFocusStatusEnum param : SellerFocusStatusEnum.values()) {
			if (code.equals(param.getCode())) {
				return param;
			}
		}
		return null;
	}

	/**
	 * 判断枚举是否存在
	 * 
	 * @author zjj
	 * @date 2018年6月25日 下午5:10:39
	 * @param code
	 * @return
	 */
	public static boolean isExist(Integer code) {
		if (null == code) {
			return false;
		}
		for (SellerFocusStatusEnum param : SellerFocusStatusEnum.values()) {
			if (code.equals(param.getCode())) {
				return true;
			}
		}
		return false;
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
