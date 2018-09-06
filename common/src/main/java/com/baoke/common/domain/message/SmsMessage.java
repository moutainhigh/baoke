package com.baoke.common.domain.message;

import com.baoke.common.domain.base.BaseDomain;

/**
 * 发送短信
 * 
 * @author wyh
 * @date 2018年7月14日 下午4:14:41
 *
 */
public class SmsMessage extends BaseDomain {

	private static final long serialVersionUID = 4855961905345796492L;
	// 手机号码
	private String phone;
	// 短信内容
	private String message;
	// 要求发送时间
	private long sendTime;
	// 有效期限,单位毫秒
	private int validMillisecond;

	public SmsMessage() {
		super();
	}

	public SmsMessage(String phone, String message, long sendTime, int validMillisecond) {
		super();
		this.phone = phone;
		this.message = message;
		this.sendTime = sendTime;
		this.validMillisecond = validMillisecond;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getValidMillisecond() {
		return validMillisecond;
	}

	public void setValidMillisecond(int validMillisecond) {
		this.validMillisecond = validMillisecond;
	}

}
