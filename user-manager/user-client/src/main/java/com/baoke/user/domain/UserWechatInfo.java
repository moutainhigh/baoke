package com.baoke.user.domain;

import java.util.Date;

import com.baoke.common.constant.WechatUserSourceTypeEnum;
import com.baoke.common.domain.base.BaseDomain;

public class UserWechatInfo extends BaseDomain {

	private static final long serialVersionUID = 1563623399307351204L;

	private Long id;

	/** 微信openId */
	private String openId;

	/** 微信用户统一标识 */
	private String unionId;

	/** app 10; 微信公众号 20; 小程序 30 */
	private Integer sourceType;

	/** 昵称 */
	private String nickName;

	/** 性别 0：男；1：女 */
	private Integer sex;

	/** 省 */
	private String province;

	/** 市 */
	private String city;

	/** 国家 */
	private String country;

	/** 头像地址 */
	private String headImgUrl;

	/** 状态 0：不可用；1：可用 */
	private Integer status;

	/** */
	private Date createTime;

	/** */
	private Date updateTime;

	public UserWechatInfo(String openId, String unionId, WechatUserSourceTypeEnum wechatSourceTypeEnum) {
		super();
		this.openId = openId;
		this.unionId = unionId;
		this.sourceType = wechatSourceTypeEnum.getCode();
	}

	/**
	 * 转换为userInfo
	 * 
	 * @author wyh
	 * @date 2018年7月17日 下午2:10:44
	 * 
	 * @return
	 */
	public UserInfo converUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUnionId(this.unionId);
		userInfo.setNickName(this.nickName);
		userInfo.setSex(this.sex);
		userInfo.setProvince(this.province);
		userInfo.setCity(this.city);
		userInfo.setCountry(this.country);
		userInfo.setHeadImgUrl(this.headImgUrl);
		return userInfo;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public UserWechatInfo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId == null ? null : openId.trim();
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

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
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
}