package com.baoke.user.domain;

import java.util.Date;

import com.baoke.common.domain.base.BaseDomain;
import com.baoke.common.dto.info.SellerAuditInfoDto;
import com.baoke.user.constant.SellerStatusEnum;

public class SellerInfo extends BaseDomain {
	private static final long serialVersionUID = -4289959100379616137L;

	private Long sellerId;

	private String nickName;

	private String realName;

	private String idCard;

	private String idCardImgUrl;

	private String categoryIds;

	private String bankCardNo;

	private String depositBank;

	private String contactPhone;

	private Integer status;

	private String auditResult;

	private Date createTime;

	private Date updateTime;

	public SellerInfo() {
		super();
	}

	public SellerInfo(SellerAuditInfoDto sellerAuditInfoDto) {
		this.sellerId = sellerAuditInfoDto.getSellerId();
		this.realName = sellerAuditInfoDto.getRealName();
		this.idCard = sellerAuditInfoDto.getIdCard();
		this.nickName = sellerAuditInfoDto.getSellerNickName();
		this.bankCardNo = sellerAuditInfoDto.getBankCardNo();
		this.depositBank = sellerAuditInfoDto.getDepositBank();
		this.contactPhone = sellerAuditInfoDto.getContactPhone();
		this.idCardImgUrl = sellerAuditInfoDto.getIdCardImgUrl();
		this.categoryIds = sellerAuditInfoDto.getCategoryIds();
		this.status = SellerStatusEnum.SELLER_CENTER.getCode();
	}

	public SellerAuditInfoDto convert() {
		SellerAuditInfoDto sellerAuditInfoDto = new SellerAuditInfoDto();
		sellerAuditInfoDto.setSellerId(this.sellerId);
		sellerAuditInfoDto.setAuditResult(this.auditResult);
		sellerAuditInfoDto.setRealName(this.realName);
		sellerAuditInfoDto.setIdCard(this.idCard);
		sellerAuditInfoDto.setIdCardImgUrl(this.idCardImgUrl);
		sellerAuditInfoDto.setSellerNickName(this.nickName);
		sellerAuditInfoDto.setCategoryIds(this.categoryIds);
		sellerAuditInfoDto.setBankCardNo(this.bankCardNo);
		sellerAuditInfoDto.setDepositBank(this.depositBank);
		sellerAuditInfoDto.setContactPhone(this.contactPhone);
		sellerAuditInfoDto.setStatus(this.status);
		return sellerAuditInfoDto;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard == null ? null : idCard.trim();
	}

	public String getIdCardImgUrl() {
		return idCardImgUrl;
	}

	public void setIdCardImgUrl(String idCardImgUrl) {
		this.idCardImgUrl = idCardImgUrl == null ? null : idCardImgUrl.trim();
	}

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds == null ? null : categoryIds.trim();
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
	}

	public String getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank == null ? null : depositBank.trim();
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone == null ? null : contactPhone.trim();
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
		this.auditResult = auditResult == null ? null : auditResult.trim();
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