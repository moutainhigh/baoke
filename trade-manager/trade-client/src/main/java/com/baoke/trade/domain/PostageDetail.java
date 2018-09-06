package com.baoke.trade.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;

/**
 * 快递明细实体
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:05:47
 */
public class PostageDetail extends BaseDomain {

	private static final long serialVersionUID = -9064821899760293052L;

	// 快递明细id
	private Integer id;

	// 快递单号
	private String postageNo;

	// 时间
	private Date dateTime;

	// 内容
	private String content;

	// 签名
	private String sign;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostageNo() {
		return postageNo;
	}

	public void setPostageNo(String postageNo) {
		this.postageNo = postageNo;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
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

}
