package com.baoke.common.dto.info;

import com.baoke.common.dto.base.BaseResultDto;

public class SellerAuditInfoDto extends BaseResultDto {

	private static final long serialVersionUID = -6744384308388651070L;

	/** 卖家id */
	private Long sellerId;

	/** 真实姓名 */
	private String realName;

	/** 主播名称 */
	private String sellerNickName;

	/** 播主头像 */
	private String sellerImgUrl;

	/** 身份证号码 */
	private String idCard;

	/** 手持身份证照片 */
	private String idCardImgUrl;

	/** 店铺类目id */
	private String categoryIds;

	private String categoryNames;

	/** 银行卡号 */
	private String bankCardNo;

	/** 开户行 */
	private String depositBank;

	/** 联系手机号 */
	private String contactPhone;

	/** 标签 用;分隔 */
	private String tags;

	private Integer status;

	/** 审核结果描述 */
	private String auditResult;

	/** 验证码 */
	private String smsCode;

	public SellerAuditInfoDto() {
	}

	public SellerAuditInfoDto(boolean success, String message) {
		super(success, message);
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSellerNickName() {
		return sellerNickName;
	}

	public void setSellerNickName(String sellerNickName) {
		this.sellerNickName = sellerNickName;
	}

	public String getSellerImgUrl() {
		return sellerImgUrl;
	}

	public void setSellerImgUrl(String sellerImgUrl) {
		this.sellerImgUrl = sellerImgUrl;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIdCardImgUrl() {
		return idCardImgUrl;
	}

	public void setIdCardImgUrl(String idCardImgUrl) {
		this.idCardImgUrl = idCardImgUrl;
	}

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public String getCategoryNames() {
		return categoryNames;
	}

	public void setCategoryNames(String categoryNames) {
		this.categoryNames = categoryNames;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

}
