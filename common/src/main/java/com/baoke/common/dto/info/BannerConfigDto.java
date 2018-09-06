
package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * Banner 配置
 * 
 * @author zdy
 * @date: 2018年6月15日 下午6:47:11
 */
public class BannerConfigDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private Long bannerId;

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
	private Long startTime;

	/** 结束时间 */
	private Long endTime;

	/** 状态 */
	private Integer status;

	public BannerConfigDto() {
		super();
	}

	public BannerConfigDto(boolean success, String message) {
		super(success, message);
	}

	public String getScenType() {
		return scenType;
	}

	public void setScenType(String scenType) {
		this.scenType = scenType;
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

	public Long getBannerId() {
		return bannerId;
	}

	public void setBannerId(Long bannerId) {
		this.bannerId = bannerId;
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

	public String getDispatchType() {
		return dispatchType;
	}

	public void setDispatchType(String dispatchType) {
		this.dispatchType = dispatchType;
	}

}
