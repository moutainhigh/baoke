package com.baoke.common.dto.seller;

import com.baoke.common.dto.base.BaseResultDto;

/**
 *
 * @author ljj
 * @date: 2018年7月13日 下午6:41:57
 */
public class AdminHomeDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	/** 待处理总数 */
	private Integer allProcessIngNum;

	/** 待审核主播数 */
	private Integer auditIngSellerNum;
	/** 待审核视频数 */
	private Integer auditIngVideoNum;
	/** 待审核商品数 */
	private Integer auditingItemNum;
	/** 待处理举报数 */
	private Integer auditingComplaintNum;

	public Integer getAllProcessIngNum() {
		return allProcessIngNum;
	}

	public void setAllProcessIngNum(Integer allProcessIngNum) {
		this.allProcessIngNum = allProcessIngNum;
	}

	public Integer getAuditIngSellerNum() {
		return auditIngSellerNum;
	}

	public void setAuditIngSellerNum(Integer auditIngSellerNum) {
		this.auditIngSellerNum = auditIngSellerNum;
	}

	public Integer getAuditingComplaintNum() {
		return auditingComplaintNum;
	}

	public Integer getAuditIngVideoNum() {
		return auditIngVideoNum;
	}

	public void setAuditIngVideoNum(Integer auditIngVideoNum) {
		this.auditIngVideoNum = auditIngVideoNum;
	}

	public Integer getAuditingItemNum() {
		return auditingItemNum;
	}

	public void setAuditingItemNum(Integer auditingItemNum) {
		this.auditingItemNum = auditingItemNum;
	}

	public void setAuditingComplaintNum(Integer auditingComplaintNum) {
		this.auditingComplaintNum = auditingComplaintNum;
	}

}
