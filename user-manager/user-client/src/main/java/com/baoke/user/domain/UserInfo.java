package com.baoke.user.domain;

import java.util.Date;

import com.baoke.common.constant.config.CommonConfig;
import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.MessageInfoDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.dto.info.VideoCommentInfoDto;
import com.baoke.user.constant.UserBannedEnum;
import com.baoke.user.constant.UserStatusEnum;
import com.baoke.user.constant.UserTypeEnum;

public class UserInfo extends BaseDomain {
	private static final long serialVersionUID = -3509558498369416342L;

	private Long id;

	/** 手机号 */
	private String phone;

	/** 密码 */
	private String password;

	/** 微信标识 */
	private String unionId;

	/** 头像地址 */
	private String headImgUrl;

	/** 昵称 */
	private String nickName;

	/** 性别：1男性，2女性，0未知 */
	private Integer sex;

	/** 省 */
	private String province;

	/** 市 */
	private String city;

	/** 国家 */
	private String country;

	/** 0：普通用户；1：播主 */
	private Integer type;

	/** 状态 0：不可用；1：可用 */
	private Integer status;

	/** 禁言状态 0：未禁言 1：已禁言 */
	private Integer bannedStatus;

	/** 禁言截止时间 */
	private Date bannedEndTime;

	/** 禁言原因 */
	private String bannedReason;

	/** */
	private Date createTime;

	/** */
	private Date updateTime;

	/** 昵称修改时间 */
	private Date nickNameUpdateTime;

	public UserInfo() {

	}

	public UserInfo(Long id, String nickName) {
		super();
		this.id = id;
		this.nickName = nickName;
	}

	public UserInfo(Long id, Integer sex) {
		super();
		this.id = id;
		this.sex = sex;
	}

	public UserInfo(Long id, UserStatusEnum status) {
		super();
		this.id = id;
		this.status = status.getCode();
	}

	public UserInfo(String unionId, UserStatusEnum status) {
		super();
		this.unionId = unionId;
		if (null != status)
			this.status = status.getCode();
	}

	public UserInfo(Long id, String phone, String unionId) {
		super();
		this.id = id;
		this.phone = phone;
		this.unionId = unionId;
	}

	public UserInfo(Long id, String phone, String unionId, UserTypeEnum type, UserStatusEnum status,
			UserBannedEnum bannedStatus) {
		super();
		this.id = id;
		this.phone = phone;
		this.unionId = unionId;
		if (null != type) {
			this.type = type.getCode();
		}
		if (null != status) {
			this.status = status.getCode();
		}
		if (null != bannedStatus) {
			this.bannedStatus = bannedStatus.getCode();
		}
	}

	public UserInfoDto convert() {
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setUserId(this.id);
		userInfoDto.setPhone(this.phone);
		userInfoDto.setHeadImgUrl(this.headImgUrl);
		userInfoDto.setUnionId(this.unionId);
		userInfoDto.setWechatNickName("");
		userInfoDto.setHeadImgUrl(this.headImgUrl);
		userInfoDto.setNickName(this.nickName);
		userInfoDto.setSex(this.sex);
		userInfoDto.setUserType(this.type);
		userInfoDto.setBannedStatus(this.bannedStatus);
		if (null != this.bannedEndTime) {
			userInfoDto.setBannedEndTime(this.bannedEndTime.getTime());
		}
		userInfoDto.setBannedReason(this.bannedReason);

		return userInfoDto;
	}

	public SellerInfoDto convertSellerInfoDto() {
		SellerInfoDto sellerInfoDto = new SellerInfoDto();
		sellerInfoDto.setSellerId(this.id);
		sellerInfoDto.setSellerNickName(this.nickName);
		sellerInfoDto.setSellerImgUrl(this.headImgUrl);
		return sellerInfoDto;
	}

	public MessageInfoDto convertMessageInfoDto(MessageInfoDto messageInfoDto, boolean isSystemUser) {
		if (null == messageInfoDto) {
			messageInfoDto = new MessageInfoDto();
		}
		if (isSystemUser) {
			messageInfoDto.setUserId(CommonConfig.SYSTEM_USER_ID);
			messageInfoDto.setHeadImgUrl(CommonConfig.SYSTEM_USER_HEAD_URL);
			messageInfoDto.setNickName(CommonConfig.SYSTEM_USER_NICK);
		} else {
			messageInfoDto.setUserId(this.id);
			messageInfoDto.setHeadImgUrl(this.headImgUrl);
			messageInfoDto.setNickName(this.nickName);
			messageInfoDto.setUserType(this.type);
		}
		return messageInfoDto;
	}

	public VideoCommentInfoDto convertVideoCommentInfoDto(VideoCommentInfoDto videoCommentInfoDto) {
		if (null == videoCommentInfoDto) {
			videoCommentInfoDto = new VideoCommentInfoDto();
		}
		videoCommentInfoDto.setUserId(this.id);
		videoCommentInfoDto.setUserNick(this.nickName);
		videoCommentInfoDto.setHeadImgUrl(this.headImgUrl);
		videoCommentInfoDto.setUserType(this.type);
		return videoCommentInfoDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public Date getNickNameUpdateTime() {
		return nickNameUpdateTime;
	}

	public void setNickNameUpdateTime(Date nickNameUpdateTime) {
		this.nickNameUpdateTime = nickNameUpdateTime;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country == null ? null : country.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getBannedReason() {
		return bannedReason;
	}

	public void setBannedReason(String bannedReason) {
		this.bannedReason = bannedReason;
	}

	public Integer getBannedStatus() {
		return bannedStatus;
	}

	public void setBannedStatus(Integer bannedStatus) {
		this.bannedStatus = bannedStatus;
	}

	public Date getBannedEndTime() {
		return bannedEndTime;
	}

	public void setBannedEndTime(Date bannedEndTime) {
		this.bannedEndTime = bannedEndTime;
	}

}