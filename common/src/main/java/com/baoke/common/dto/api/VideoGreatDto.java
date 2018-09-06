package com.baoke.common.dto.api;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 视频 点赞(喜欢)、不感兴趣
 * 
 * @author wyh
 * @date 2018年6月30日 上午11:22:48
 *
 */
public class VideoGreatDto extends BaseResultDto {
	private static final long serialVersionUID = 1L;

	/** 视频id */
	private Long videoId;

	/** 状态 0：取消点赞；1：点赞 */
	private Integer isGreat;

	/** 不感兴趣 0：否；1：是 */
	private Integer isDelike;

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Integer getIsGreat() {
		return isGreat;
	}

	public void setIsGreat(Integer isGreat) {
		this.isGreat = isGreat;
	}

	public Integer getIsDelike() {
		return isDelike;
	}

	public void setIsDelike(Integer isDelike) {
		this.isDelike = isDelike;
	}

}
