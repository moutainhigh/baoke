package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.MessageInfoDto;

/**
 * 消息列表
 * 
 * @author: wyj
 * @date: 2018年6月14日 上午10:16:13
 */
public class MessageListDto extends BaseResultDto {

	private static final long serialVersionUID = -7022645001741916495L;

	private List<MessageInfoDto> messageList; // 消息集合

	private PageInfo pageInfo; // 分页

	public MessageListDto() {
	}

	public MessageListDto(boolean success, String message) {
		super(success, message);
	}

	public MessageListDto(boolean success, String message, List<MessageInfoDto> messageList, PageInfo pageInfo) {
		super(success, message);
		this.messageList = messageList;
		this.pageInfo = pageInfo;
	}

	public List<MessageInfoDto> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MessageInfoDto> messageList) {
		this.messageList = messageList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
