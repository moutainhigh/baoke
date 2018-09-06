package com.baoke.common.constant;

/**
 * 卖家资金收入状态
 */
public enum SellerIncomeStatus {

	FREEZEN(0, "冻结"),

	NORMAL(1, "可提现"),

	SETTLED(2, "已提现");

	private int code;

	private String name;

	private SellerIncomeStatus(int code, String name) {
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

}
