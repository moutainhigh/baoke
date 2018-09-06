package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.MessageListDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.exception.base.MainException;

/**
 * 消息列表请求
 * 
 * @author: wyj
 * @date: 2018年6月14日 上午10:27:53
 */
public class QueryMyMessageRequest extends BaseRequestParam {

	private static final long serialVersionUID = 3790236885012779209L;

	private Integer curPageNo = 1;// 当前页数

	private Integer pageSize = 20;// 每页显示的记录数

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
	public BaseDto convert() throws MainException {
		MessageListDto messageListDto = new MessageListDto();
		messageListDto.setPageInfo(new PageInfo(curPageNo, pageSize));
		return messageListDto;
	}
}
