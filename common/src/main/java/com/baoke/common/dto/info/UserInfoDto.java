package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 用户信息
 * 
 * @author zjm
 * @Date: 2018年6月4日 下午7:40:16
 */
public class UserInfoDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	// /** userId */
	// private int id;

	/** 用户CODE */
	private String userCode;

	/** 手机号 */
	private String phone;

	/** 微信 */
	private String unionId;

	/** 微信昵称 */
	private String wechatNickName;

	/** 用户头像 */
	private String headImgUrl;

	/** 用户昵称 */
	private String nickName;

	/** 性别：1男性，2女性，0未知 */
	private Integer sex;

	/** 我的关注数 */
	private Integer focusNum;

	/** 我的点赞数 */
	private Integer greatNum;

	/** 未读消息数 */
	private Integer unReadMsgNum;

	/** 是否绑定微信 */
	private Integer isBindWechat;

	/** 是否绑定手机号 */
	private Integer isBindPhone;

	/** 是否播主（卖家） */
	private Integer userType;

	/** 认证状态 状态：0：审核中；1：已通过；2：已拒绝 */
	private Integer authStatus;

	/** 禁言状态 0：未禁言 1：已禁言 */
	private Integer bannedStatus;

	/** 禁言截止时间 */
	private Long bannedEndTime;

	/** 禁言原因 */
	private String bannedReason;

	/** 播主（卖家）信息 */
	private SellerInfoDto sellerInfo;

	public UserInfoDto() {

	}

	public UserInfoDto(boolean success, String message) {
		super(success, message);
	}

	public UserInfoDto(Long userId, Long deviceId, String headImgUrl, String nickName, Integer sex, Integer userType) {
		setUserId(userId);
		setDeviceId(deviceId);
		this.headImgUrl = headImgUrl;
		this.nickName = nickName;
		this.sex = sex;
		this.userType = userType;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getBannedStatus() {
		return bannedStatus;
	}

	public void setBannedStatus(Integer bannedStatus) {
		this.bannedStatus = bannedStatus;
	}

	public Long getBannedEndTime() {
		return bannedEndTime;
	}

	public void setBannedEndTime(Long bannedEndTime) {
		this.bannedEndTime = bannedEndTime;
	}

	public String getBannedReason() {
		return bannedReason;
	}

	public void setBannedReason(String bannedReason) {
		this.bannedReason = bannedReason;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public Integer getUnReadMsgNum() {
		return unReadMsgNum;
	}

	public void setUnReadMsgNum(Integer unReadMsgNum) {
		this.unReadMsgNum = unReadMsgNum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getWechatNickName() {
		return wechatNickName;
	}

	public void setWechatNickName(String wechatNickName) {
		this.wechatNickName = wechatNickName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getFocusNum() {
		return focusNum;
	}

	public void setFocusNum(Integer focusNum) {
		this.focusNum = focusNum;
	}

	public Integer getGreatNum() {
		return greatNum;
	}

	public void setGreatNum(Integer greatNum) {
		this.greatNum = greatNum;
	}

	public Integer getIsBindWechat() {
		return isBindWechat;
	}

	public void setIsBindWechat(Integer isBindWechat) {
		this.isBindWechat = isBindWechat;
	}

	public Integer getIsBindPhone() {
		return isBindPhone;
	}

	public void setIsBindPhone(Integer isBindPhone) {
		this.isBindPhone = isBindPhone;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public SellerInfoDto getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(SellerInfoDto sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

}
