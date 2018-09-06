package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.constant.MessageTypeEnum;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.MessageInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 消息列表请求
 * 
 * @author: wyj
 * @date: 2018年6月14日 上午10:27:53
 */
public class QueryMyMessageByTypeRequest extends BaseRequestParam {

	private static final long serialVersionUID = 3790236885012779209L;

	private Integer curPageNo = 1;// 当前页数

	private Integer pageSize = 20;// 每页显示的记录数

	private Integer messageType;

	private Long videoId;

	private Long commentId;

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

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	@Override
	public BaseDto convert() throws MainException {
		MessageInfoDto messageInfoDto = new MessageInfoDto();
		messageInfoDto.setMessageType(messageType);
		messageInfoDto.setVideoId(videoId);
		messageInfoDto.setCommentId(commentId);
		if (messageType == MessageTypeEnum.SELLER_BE_FOCUS.getCode()
				|| messageType == MessageTypeEnum.SELLER_BE_GREAT.getCode()
				|| messageType == MessageTypeEnum.COMMENT_GREAT.getCode()) {
			pageSize = 80;
		}
		messageInfoDto.setPageInfo(new PageInfo(curPageNo, pageSize));

		return messageInfoDto;
	}
}
