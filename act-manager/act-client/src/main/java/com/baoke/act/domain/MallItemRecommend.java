package com.baoke.act.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.MallRecommendInfoDto;

public class MallItemRecommend extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private Long id;//
	private Long parentId;// 区块=0表示区块标题，此时title有值
	private String title;// 区块标题
	private String content;// 区块内容描述
	private String imgUrl;// 图片地址
	private String targetUrl;// 链接地址
	private String dispatchType;// wap/browser/theme
	private Integer sort;// 排序
	private Integer status;// 状态 ： 0：不可用；1：可用
	private Date startTime;// 开始时间
	private Date endTime;// 结束时间
	private Date createTime;//
	private Date updateTime;//

	public MallItemRecommend() {
		super();
	}

	public MallItemRecommend(String title) {
		this.title = title;
	}

	public MallItemRecommend(Long parentId) {
		super();
		this.parentId = parentId;
	}

	public MallItemRecommend(Long parentId, Integer status) {
		super();
		this.parentId = parentId;
		this.status = status;
	}

	public MallItemRecommend(MallRecommendInfoDto mallRecommendInfoDto) {
		this.parentId = mallRecommendInfoDto.getParentId();
		this.title = mallRecommendInfoDto.getTitle();
		this.content = mallRecommendInfoDto.getContent();
		this.imgUrl = mallRecommendInfoDto.getImgUrl();
		this.targetUrl = mallRecommendInfoDto.getTargetUrl();
		this.dispatchType = mallRecommendInfoDto.getDispatchType();
		this.sort = mallRecommendInfoDto.getSort();
		this.status = mallRecommendInfoDto.getStatus();
		if (null != mallRecommendInfoDto.getStartTime()) {
			this.startTime = new Date(mallRecommendInfoDto.getStartTime());
		}
		if (null != mallRecommendInfoDto.getEndTime()) {
			this.endTime = new Date(mallRecommendInfoDto.getEndTime());
		}

	}

	public MallRecommendInfoDto convert() {
		MallRecommendInfoDto mallRecommendInfoDto = new MallRecommendInfoDto();
		mallRecommendInfoDto.setParentId(this.parentId);
		mallRecommendInfoDto.setTitle(this.title);
		mallRecommendInfoDto.setContent(this.content);
		mallRecommendInfoDto.setImgUrl(this.imgUrl);
		mallRecommendInfoDto.setTargetUrl(this.targetUrl);
		mallRecommendInfoDto.setDispatchType(this.dispatchType);
		mallRecommendInfoDto.setSort(this.sort);
		mallRecommendInfoDto.setStatus(this.status);
		if (null != this.startTime) {
			mallRecommendInfoDto.setStartTime(this.startTime.getTime());
		}

		if (null != this.endTime) {
			mallRecommendInfoDto.setEndTime(this.endTime.getTime());
		}

		return mallRecommendInfoDto;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTargetUrl() {
		return this.targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getDispatchType() {
		return this.dispatchType;
	}

	public void setDispatchType(String dispatchType) {
		this.dispatchType = dispatchType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
