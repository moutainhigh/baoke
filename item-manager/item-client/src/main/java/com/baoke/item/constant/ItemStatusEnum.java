package com.baoke.item.constant;

/**
 * 商品状态
 */
public enum ItemStatusEnum {

	WAIT_AUDIT(10, "审核中"),

	AUDIT_FAIL(20, "审核失败"),

	ONLINE(30, "已上线"),

	DOWNLINE(60, "已下线"),

	FORCE_DOWNLINE(80, "封禁中");

	// UNAVAILABLE_STATUS(0, "不可用"),
	//
	// USABLE_STATUS(1, "可用"),
	//
	// TO_BE_ON_THE_SHELF_STATUS(2, "待上架"),
	//
	// SOLD_OUT_STATUS(3, "已售罄"),
	//
	// RESERVATIONS_STATUS(4, "可预订"),
	//
	// WAITE_CHECK(5, "审核中"),
	//
	// CHECK_FAIL(6, "审核失败"),
	//
	// CHECK_SUCCESS(7, "审核通过"),
	//
	// ON_SHELF(8, "上架"),
	//
	// OFF_SHELF(8, "下架");

	private int code;
	private String name;

	ItemStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static ItemStatusEnum getByCode(int code) {
		ItemStatusEnum[] itemStatusEnums = ItemStatusEnum.values();
		for (ItemStatusEnum itemStatusEnum : itemStatusEnums) {
			if (itemStatusEnum.getCode() == code) {
				return itemStatusEnum;
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
