package com.baoke.trade.constant;

/**
 * 是否提醒发货
 * 
 * @author zjj
 * @date 2018年7月20日 下午4:04:18
 */
public enum IsRemindSendItemEnum {

	NO(0, "否"),

	YES(1, "是");

	private int code;
	private String name;

	IsRemindSendItemEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static String getDescByStatus(int status) {
		for (IsRemindSendItemEnum isRemindSendItemtEnum : IsRemindSendItemEnum.values()) {
			if (isRemindSendItemtEnum.code == status) {
				return isRemindSendItemtEnum.name;
			}
		}
		return "";
	}

	/**
	 * 判断枚举是否存在
	 * 
	 * @author zjj
	 * @date 2018年6月30日 下午4:49:32
	 * @param code
	 * @return
	 */
	public static boolean isExist(Integer code) {
		if (null == code) {
			return false;
		}
		for (IsRemindSendItemEnum isRemindSendItemtEnum : IsRemindSendItemEnum.values()) {
			if (code.equals(isRemindSendItemtEnum.getCode())) {
				return true;
			}
		}
		return false;
	}

}
