package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

public class MallRecommendInfoDto extends BaseResultDto {
	private static final long serialVersionUID = 1L;
	private Long mallItemRecommendId;// 主键id
	private Long parentId;// 区块=0表示区块标题，此时title有值
	private String title;// 区块标题
	private String content;// 区块内容描述
	private String imgUrl;// 图片地址
	private String targetUrl;// 链接地址
	private String dispatchType;// wap/browser/theme
	private Integer sort;// 排序
	private Integer status;// 状态 ： 0：不可用；1：可用
	private Long startTime;// 开始时间
	private Long endTime;// 结束时间

	public Long getMallItemRecommendId() {
		return mallItemRecommendId;
	}

	public void setMallItemRecommendId(Long mallItemRecommendId) {
		this.mallItemRecommendId = mallItemRecommendId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

}
