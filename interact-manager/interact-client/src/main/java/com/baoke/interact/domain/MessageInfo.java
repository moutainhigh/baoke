package com.baoke.interact.domain;

import java.util.Date;

import com.baoke.common.constant.MessageTypeEnum;
import com.baoke.common.domain.base.BaseDomain;

/**
 * 消息
 * 
 * @author zjj
 * @date 2018年7月12日 下午5:19:29
 */
public class MessageInfo extends BaseDomain {

	private static final long serialVersionUID = -4708674822231995473L;

	private Long id;

	// 短信类型
	private Integer messageType;

	// 接收人用户ID，全网消息用户ID为0
	private Long userId;

	// 视频ID
	private Long videoId;

	// 当前评论ID 指的是一级评论ID
	private Long commentId;

	// 消息条数
	private Integer num;

	// 排序， 系统消息为1，其他类型为0
	private Integer sort;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date updateTime;

	/**
	 * 构建消息列表Title
	 * 
	 * @author zjj
	 * @date 2018年7月21日 上午11:26:38
	 * @return
	 */
	public String getMessageTitle() {
		String title = (num > 1 ? (" 等" + num + "人") : " ");
		switch (MessageTypeEnum.getByCode(messageType)) {
		case SYSTEM:
			title = "系统消息";
			break;
		case SELLER_BE_FOCUS:
			title = title + "关注了你";
			break;
		case SELLER_BE_GREAT:
			title = title + "喜欢了你的视频";
			break;
		case SELLER_BE_COMMENT:
			title = title + "评论了你的视频";
			break;
		case COMMENT_INTERACT:
			title = title + "回复了你的评论";
			break;
		case COMMENT_GREAT:
			title = title + "赞了你的评论";
			break;
		default:
			title = "";
			break;
		}
		return title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
