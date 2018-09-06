package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.MessageListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.MessageInfoDto;

/**
 * 消息列表响应
 * 
 * @author: wyj
 * @date: 2018年6月14日 上午10:30:55
 */
public class QueryMyMessageResponse extends BaseResponseParam {

	private static final long serialVersionUID = -6710829914614449439L;

	private List<MessageInfoDto> messageList;

	private Integer curPageNo;// 当前页数

	private Integer pageSize;// 每页显示的记录数

	public List<MessageInfoDto> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MessageInfoDto> messageList) {
		this.messageList = messageList;
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

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		MessageListDto messageListDto = (MessageListDto) baseResultDto;
		this.messageList = messageListDto.getMessageList();
		if (messageListDto.getPageInfo() != null) {
			this.curPageNo = messageListDto.getPageInfo().getCurrent();
			this.pageSize = messageListDto.getPageInfo().getPageSize();
		}
		return this;
	}

}
