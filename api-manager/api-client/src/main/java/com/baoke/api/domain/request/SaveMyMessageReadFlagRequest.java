package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.MessageInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 保存已读请求
 * 
 * @author: wyj
 * @date: 2018年6月14日 上午10:37:12
 */
public class SaveMyMessageReadFlagRequest extends BaseRequestParam {

	private static final long serialVersionUID = 7791228502205104408L;

	private Long messageId;// 消息ID

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	@Override
	public BaseDto convert() throws MainException {
		MessageInfoDto messageInfoDto = new MessageInfoDto();
		messageInfoDto.setMessageId(this.messageId);
		return messageInfoDto;
	}

}
