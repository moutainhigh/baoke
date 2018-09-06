package com.baoke.user.domain;

import java.util.Date;

import com.baoke.common.constant.UserPhoneCodeTypeEnum;
import com.baoke.common.domain.base.BaseDomain;

public class UserPhoneCode extends BaseDomain {

	private static final long serialVersionUID = 6712629884665766885L;

	private Long id;

	/** 手机号 */
	private String phone;

	/** 验证码 */
	private String smsCode;

	/** 类型：0：用户登录；1：店铺认证 */
	private Integer type;

	/** 失效时间 */
	private Date deadlineTime;

	private Date createTime;

	public UserPhoneCode(String phone, UserPhoneCodeTypeEnum type) {
		super();
		this.phone = phone;
		this.type = type.getCode();
	}

	public UserPhoneCode(String phone, String smsCode, Date deadlineTime, UserPhoneCodeTypeEnum type) {
		super();
		this.phone = phone;
		this.smsCode = smsCode;
		this.deadlineTime = deadlineTime;
		this.type = type.getCode();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public UserPhoneCode() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode == null ? null : smsCode.trim();
	}

	public Date getDeadlineTime() {
		return deadlineTime;
	}

	public void setDeadlineTime(Date deadlineTime) {
		this.deadlineTime = deadlineTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}