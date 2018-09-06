package com.baoke.common.constant;

/**
 * 消息类型
 */
public enum MessageTypeEnum {

	SYSTEM(10, "SYSTEM", "系统消息"),

	SELLER_BE_FOCUS(20, "SELLER_BE_FOCUS", "关注消息－主播被关注"),

	SELLER_BE_GREAT(22, "SELLER_BE_GREAT", "关注消息－主播视频被点赞（喜欢）"),

	SELLER_BE_COMMENT(40, "SELLER_BE_COMMENT", "评论消息－主播收到的评论（一级）"),

	COMMENT_INTERACT(50, "COMMENT_INTERACT", "评论消息－评论的回复（二级）"),

	COMMENT_GREAT(80, "COMMENT_GREAT", "评论点赞消息－评论点赞");

	private int code;

	private String name;

	private String desc;

	private MessageTypeEnum(int code, String name, String desc) {
		this.code = code;
		this.name = name;
		this.desc = desc;
	}
	
	/**
	 * 根据code返回枚举
	 * 
	 * @author zjj
	 * @date 2018年7月12日 下午7:46:41
	 * @param code
	 * @return
	 */
	public static MessageTypeEnum getByCode(Integer code) {
		if (null == code) {
			return null;
		}
		MessageTypeEnum[] values = MessageTypeEnum.values();
		for (MessageTypeEnum messageTypeEnum : values) {
			if (code.equals(messageTypeEnum.getCode())) {
				return messageTypeEnum;
			}
		}
		return null;
	}

	/**
	 * 判断是否存在
	 * 
	 * @author zjj
	 * @date 2018年7月12日 下午7:47:54
	 * @param code
	 * @return
	 */
	public static boolean isExist(Integer code) {
		if (null == code) {
			return false;
		}
		MessageTypeEnum[] values = MessageTypeEnum.values();
		for (MessageTypeEnum messageTypeEnum : values) {
			if (code.equals(messageTypeEnum.getCode())) {
				return true;
			}
		}
		return false;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}



}
