package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 视频举报字典表
 * 
 * @author zdy
 * @date: 2018年6月29日 下午5:00:59
 */
public class VideoComplaintDictDto extends BaseResultDto {

	private static final long serialVersionUID = -9105341191566249527L;

	private Long complaintDictId;

	/** 内容 */
	private String content;

	/** 状态 0:禁用 1：启用 */
	private Integer status;

	/** 类型 0：非自填；1：自填 */
	private Integer type;

	public Long getComplaintDictId() {
		return complaintDictId;
	}

	public void setComplaintDictId(Long complaintDictId) {
		this.complaintDictId = complaintDictId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
