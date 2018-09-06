package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 物流明细信息
 * 
 * @author zjm
 * @date: 2018年6月25日 下午2:24:38
 */
public class PostageDetailInfoDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	/** 时间 */
	private Long dateTime;

	/** 内容 */
	private String content;

	public Long getDateTime() {
		return dateTime;
	}

	public void setDateTime(Long dateTime) {
		this.dateTime = dateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
