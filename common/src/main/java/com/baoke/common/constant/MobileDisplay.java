package com.baoke.common.constant;

/**
 * 无线图片清晰度类型
 */
public enum MobileDisplay {

	LOW("low", 1),

	MIDDLE("middle", 2),

	HIGH("high", 3);

	private String name;
	private int code;

	private MobileDisplay(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 按照名字获取清晰度类型
	 * 
	 * @param display
	 * @return
	 */
	public static MobileDisplay getMobileDisplay(String display) {
		MobileDisplay[] displays = MobileDisplay.class.getEnumConstants();
		for (MobileDisplay md : displays) {
			if (md.getName().equals(display)) {
				return md;
			}
		}
		return null;
	}
}
