package com.baoke.act.domain;

import java.util.Date;

import com.baoke.common.constant.BannerScenTypeEnum;
import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.BannerConfigDto;

/**
 * banner配置 date: 2018年6月5日
 * 
 * @author zdy
 * @version
 */
public class BannerConfig extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private Long id;
	/** 名称 */
	private String name;

	/** 内容 */
	private String text;

	/** 图片地址 */
	private String imgUrl;

	/** 链接地址 */
	private String targetUrl;

	/** 场景类型 */
	private String scenType;

	/** wap/browser/theme */
	private String dispatchType;

	/** 排序值，小的在前面 */
	private Integer sort;

	/** 开始时间 */
	private Date startTime;

	/** 结束时间 */
	private Date endTime;

	/** 创建人 */
	private Long createUserId;

	/** 修改人 */
	private Long modifyUserId;

	/** */
	private Date createTime;

	/** */
	private Date updateTime;

	/** 状态 ：0：不可用；1：可用 */
	private Integer status;

	public BannerConfigDto convert() {
		BannerConfigDto bannerConfigDto = new BannerConfigDto(true, "");
		bannerConfigDto.setBannerId(this.id);
		bannerConfigDto.setImgUrl(this.imgUrl);
		bannerConfigDto.setName(this.name);
		bannerConfigDto.setScenType(this.scenType);
		bannerConfigDto.setSort(this.sort);
		bannerConfigDto.setStatus(this.status);
		bannerConfigDto.setTargetUrl(this.targetUrl);
		bannerConfigDto.setText(this.text);
		bannerConfigDto.setDispatchType(this.dispatchType);
		if (this.startTime != null) {
			bannerConfigDto.setStartTime(this.startTime.getTime());
		}
		return bannerConfigDto;
	}

	public BannerConfig() {
		super();
	}

	public BannerConfig(BannerScenTypeEnum scenTypeEnum) {
		super();
		this.scenType = scenTypeEnum.getName();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getScenType() {
		return scenType;
	}

	public void setScenType(String scenType) {
		this.scenType = scenType;
	}

	public String getDispatchType() {
		return dispatchType;
	}

	public void setDispatchType(String dispatchType) {
		this.dispatchType = dispatchType;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
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
