package com.baoke.extra.sms.domain.request;

import java.io.Serializable;

/**
 * 短信状态报告请求体
 * 
 * @author: wyj
 * @date: 2018年6月15日 上午9:55:34
 */
public class YunTongXunRequest implements Serializable {
	private static final long serialVersionUID = 7342267973907484121L;

	// 功能操作标识
	private String action;

	// 短信类型，0：上行短信，1：手机接收状态报告
	private Integer smsType;

	// 收到上行短信/短信送达手机时间，格式：YYYYMMDDHHMMSS
	private String recvTime;

	// 版本号，格式：YYYY-MM-DD
	private String apiVersion;

	// 发送/接收短信的手机号码，以13等开头的11位号码
	private String fromNum;

	// 短信内容，当smsType=1时，该字段的值为短信ID
	private String content;

	// 短信扩展码
	private String appendCode;

	// 自定义短信扩展码
	private String subAppend;

	// 短信到达状态, 0为接收成功，其它值为接收失败
	private String status;

	// 短信发送时间，格式：YYYYMMDDHHMMSS
	private String dateSent;

	// 到达状态描述，即运营商网关状态码
	private String deliverCode;

	// 第三方自定义消息id
	private String reqId;

	// 下行短信计费条数
	private String smsCount;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getSmsType() {
		return smsType;
	}

	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}

	public String getRecvTime() {
		return recvTime;
	}

	public void setRecvTime(String recvTime) {
		this.recvTime = recvTime;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getFromNum() {
		return fromNum;
	}

	public void setFromNum(String fromNum) {
		this.fromNum = fromNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAppendCode() {
		return appendCode;
	}

	public void setAppendCode(String appendCode) {
		this.appendCode = appendCode;
	}

	public String getSubAppend() {
		return subAppend;
	}

	public void setSubAppend(String subAppend) {
		this.subAppend = subAppend;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateSent() {
		return dateSent;
	}

	public void setDateSent(String dateSent) {
		this.dateSent = dateSent;
	}

	public String getDeliverCode() {
		return deliverCode;
	}

	public void setDeliverCode(String deliverCode) {
		this.deliverCode = deliverCode;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getSmsCount() {
		return smsCount;
	}

	public void setSmsCount(String smsCount) {
		this.smsCount = smsCount;
	}

	@Override
	public String toString() {
		return "YunTongXunRequest [action=" + action + ", smsType=" + smsType
				+ ", recvTime=" + recvTime + ", apiVersion=" + apiVersion
				+ ", fromNum=" + fromNum + ", content=" + content
				+ ", appendCode=" + appendCode + ", subAppend=" + subAppend
				+ ", status=" + status + ", dateSent=" + dateSent
				+ ", deliverCode=" + deliverCode + ", reqId=" + reqId
				+ ", smsCount=" + smsCount + "]";
	}

}
