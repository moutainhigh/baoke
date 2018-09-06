package com.baoke.user.domain;

import java.util.Date;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.AreaDictInfoDto;

/**
 * 地区字典信息
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:31:13
 */
public class AreaDictInfo extends BaseDomain {

	private static final long serialVersionUID = -8659236373933557605L;

	// 行政机构编号
	private String code;

	// 上级机构
	private String parentCode;

	// 行政区划名称
	private String name;

	// 名称首字母
	private String firstEnName;

	// 地区简称
	private String simpleName;

	// 地区级别
	private Integer level;

	// 电话区号
	private String phoneAreaCode;

	// 邮政编码
	private String postageCode;

	// 状态
	private Integer status;

	// 是否热门
	private Integer isHot;

	//
	private Date createTime;

	//
	private Date updateTime;

	public AreaDictInfo() {
		super();
	}

	public AreaDictInfo(String parentCode, BooleanEnum booleanEnum) {
		super();
		this.parentCode = parentCode;
		this.status = booleanEnum.getCode();
	}

	public AreaDictInfoDto convert() {
		AreaDictInfoDto areaDictInfoDto = new AreaDictInfoDto();
		areaDictInfoDto.setCode(this.code);
		areaDictInfoDto.setParentCode(this.parentCode);
		areaDictInfoDto.setFirstEnName(this.firstEnName);
		areaDictInfoDto.setName(this.name);
		return areaDictInfoDto;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstEnName() {
		return firstEnName;
	}

	public void setFirstEnName(String firstEnName) {
		this.firstEnName = firstEnName;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPhoneAreaCode() {
		return phoneAreaCode;
	}

	public void setPhoneAreaCode(String phoneAreaCode) {
		this.phoneAreaCode = phoneAreaCode;
	}

	public String getPostageCode() {
		return postageCode;
	}

	public void setPostageCode(String postageCode) {
		this.postageCode = postageCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
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
