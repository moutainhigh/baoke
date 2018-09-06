package com.baoke.common.dto.api;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;

/**
 * 视频详情
 * 
 * @author zdy
 * @date: 2018年7月17日 下午2:18:18
 */
public class VideoDetailDto extends BaseResultDto {
	private static final long serialVersionUID = 1L;
	/** 视频id */
	private Long videoId;

	/** 主播ID */
	private Long sellerId;

	/** 查询来源 */
	private Integer queryScen;

	private PageInfo pageInfo;// 分页

	public VideoDetailDto() {
		super();
	}

	public VideoDetailDto(Integer curPageNo, Integer pageSize) {
		PageInfo pageInfo = new PageInfo(curPageNo, pageSize);
		this.pageInfo = pageInfo;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getQueryScen() {
		return queryScen;
	}

	public void setQueryScen(Integer queryScen) {
		this.queryScen = queryScen;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
