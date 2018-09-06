package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.VideoDetailDto;
import com.baoke.common.dto.base.BaseDto;

/**
 * 查询视频详情请求参数
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:20:00
 */
public class QueryVideoDetailRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	private Long videoId;// 视频Id

	/** 主播ID */
	private Long sellerId;

	/** 查询来源 */
	private Integer queryScen;

	private Integer curPageNo = 1;// 当前页数

	private Integer pageSize = 12;

	public QueryVideoDetailRequest() {
		super();
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Integer getCurPageNo() {
		return curPageNo;
	}

	public void setCurPageNo(Integer curPageNo) {
		this.curPageNo = curPageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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

	@Override
	public BaseDto convert() {
		VideoDetailDto videoDetailDto = new VideoDetailDto(curPageNo, pageSize);
		videoDetailDto.setVideoId(videoId);
		videoDetailDto.setSellerId(sellerId);
		videoDetailDto.setQueryScen(queryScen);
		return videoDetailDto;
	}
}
