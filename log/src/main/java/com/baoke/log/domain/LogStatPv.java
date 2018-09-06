package com.baoke.log.domain;

import java.io.Serializable;
import java.util.Date;

public class LogStatPv implements Serializable {

	private static final long serialVersionUID = 1635646843842219829L;

	private Long id;

	private String scen;

	private String unionId;

	private Date date;

	private Integer value;

	private Date createTime;

	private Date updateTime;

	/** 构造函数 */
	public LogStatPv(String scen, String unionId, Integer value) {
		this.scen = scen;
		this.unionId = unionId;
		this.value = value;
	}

	/** 获取key值 */
	public String getKey() {
		return (new StringBuffer(this.scen).append("-").append(this.unionId)).toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getScen() {
		return scen;
	}

	public void setScen(String scen) {
		this.scen = scen == null ? null : scen.trim();
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId == null ? null : unionId.trim();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
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
		return "StatLogPv [id=" + id + ", scen=" + scen + ", unionId=" + unionId + ", date=" + date + ", value=" + value
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}