package com.baoke.sms.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 短信明细实体类
 * 
 * @author: wyj
 * @date: 2018年6月15日 上午11:02:55
 */
public class SendSmsDetail implements Serializable {

	private static final long serialVersionUID = -3086214924445981596L;

	// id
	private int id;

	// 手机号码
	private String phone;

	// 内容
	private String message;

	// 发送时间
	private Date sendTime;

	// 实际发送时间
	private Date validSendTime;

	// 发送请求状态
	private int sendStatus; // 发送状态 -2已失效, -1发送失败, 0数据错误, 1发送中,2发送成功

	// 有效期限
	private int validMillisecond;

	// 发送结果
	private String sendDesc;

	// 渠道
	private String channel;

	// 短信标识id
	private String smsUnionId;

	// 短信接收时间
	private Date receiveTime;

	// 接收状态
	private int receiveStatus; // 接收状态 -1接收失败,1接收成功

	// 接收结果
	private String receiveDesc;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getValidSendTime() {
		return validSendTime;
	}

	public void setValidSendTime(Date validSendTime) {
		this.validSendTime = validSendTime;
	}

	public int getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(int sendStatus) {
		this.sendStatus = sendStatus;
	}

	public int getValidMillisecond() {
		return validMillisecond;
	}

	public void setValidMillisecond(int validMillisecond) {
		this.validMillisecond = validMillisecond;
	}

	public String getSendDesc() {
		return sendDesc;
	}

	public void setSendDesc(String sendDesc) {
		this.sendDesc = sendDesc;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSmsUnionId() {
		return smsUnionId;
	}

	public void setSmsUnionId(String smsUnionId) {
		this.smsUnionId = smsUnionId;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public int getReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(int receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public String getReceiveDesc() {
		return receiveDesc;
	}

	public void setReceiveDesc(String receiveDesc) {
		this.receiveDesc = receiveDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
