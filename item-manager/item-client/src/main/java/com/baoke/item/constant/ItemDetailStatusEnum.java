package com.baoke.item.constant;

/**
 * 商品详情状态
 */
public enum ItemDetailStatusEnum {

	DISABLE(0, "不可用"),

	ENABLE(1, "可用");

	private int code;
	private String name;

	ItemDetailStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
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

	public static String getValueByCode(int code) {
		ItemDetailStatusEnum[] itemStatusEnums = ItemDetailStatusEnum.values();
		for (ItemDetailStatusEnum itemStatusEnum : itemStatusEnums) {
			if (itemStatusEnum.getCode() == code) {
				return itemStatusEnum.getName();
			}
		}
		return "";
	}

}
