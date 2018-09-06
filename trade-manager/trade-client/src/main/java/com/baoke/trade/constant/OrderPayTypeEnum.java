package com.baoke.trade.constant;

/**
 * 订单支付方式
 */
public enum OrderPayTypeEnum {

	ALIPAY(1, "支付宝支付"),

	WECHATPAY(2, "微信支付"),

	UNKNOW(0, "未定义");

	private int code;
	private String name;

	OrderPayTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	/**
	 * 判断枚举是否存在
	 * 
	 * @author zjj
	 * @date 2018年7月1日 下午10:39:54
	 * @param code
	 * @return
	 */
	public static boolean isExist(Integer code) {
		if (null == code) {
			return false;
		}
		for (OrderPayTypeEnum payTypeEnum : OrderPayTypeEnum.values()) {
			if (code.equals(payTypeEnum.getCode())) {
				return true;
			}
		}
		return false;
	}

}
