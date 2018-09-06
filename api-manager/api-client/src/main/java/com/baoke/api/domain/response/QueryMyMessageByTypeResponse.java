package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.api.MessageDetailDto;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 消息列表响应
 * 
 * @author: wyj
 * @date: 2018年6月14日 上午10:30:55
 */
public class QueryMyMessageByTypeResponse extends BaseResponseParam {

	private static final long serialVersionUID = -6710829914614449439L;

	private MessageDetailDto messageDetail;

	private Integer curPageNo;// 当前页数

	private Integer pageSize;// 每页显示的记录数

	public MessageDetailDto getMessageDetail() {
		return messageDetail;
	}

	public void setMessageDetail(MessageDetailDto messageDetail) {
		this.messageDetail = messageDetail;
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
		MessageDetailDto messageDetail = (MessageDetailDto) baseResultDto;
		this.messageDetail = messageDetail;
		if (messageDetail.getPageInfo() != null) {
			this.curPageNo = messageDetail.getPageInfo().getCurrent();
			this.pageSize = messageDetail.getPageInfo().getPageSize();
		}
		return this;
	}

}
