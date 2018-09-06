package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.VideoComplaintInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 保存举报（吐槽）请求体
 * 
 * @author zjm
 * @date: 2018年6月13日 下午3:42:15
 */
public class SaveComplaintRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	/** 视频id */
	private Long videoId;

	/** 类型 0：非自填；1：自填 */
	private Integer type;

	/** 内容 */
	private String content;

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public BaseDto convert() throws MainException {
		VideoComplaintInfoDto videoComplaintDto = new VideoComplaintInfoDto();
		videoComplaintDto.setContent(this.content);
		videoComplaintDto.setType(this.type);
		videoComplaintDto.setVideoId(this.videoId);
		return videoComplaintDto;
	}

}
