package com.baoke.common.dto.seller;

import com.baoke.common.dto.base.BaseDto;

/**
 * 主播概述
 * 
 * @author zjm
 * @date: 2018年6月25日 下午3:19:19
 */
public class SellerHomeDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	/** 视频总播放量 */
	private Integer totalVideoPlayNum;

	/** 昨日视频播放量 */
	private Integer lastdayVideoPlayNum;

	/** 昨日新增粉丝 */
	private Integer lastdayAddFansNum;

	/** 昨日新增喜欢数 */
	private Integer lastdayAddLikeNum;

	/** 成交总额 */
	private Integer totalTradeAmount;

	/** 昨日订单量 */
	private Integer lastdayOrderNum;

	/** 昨日成交额 */
	private Integer lastdayTradeAmount;

	/** 账户金额 */
	private Integer totalAccountAmount;

	/** 可提现金额 */
	private Integer withdrawAmount;

	public Integer getTotalVideoPlayNum() {
		return totalVideoPlayNum;
	}

	public void setTotalVideoPlayNum(Integer totalVideoPlayNum) {
		this.totalVideoPlayNum = totalVideoPlayNum;
	}

	public Integer getLastdayVideoPlayNum() {
		return lastdayVideoPlayNum;
	}

	public void setLastdayVideoPlayNum(Integer lastdayVideoPlayNum) {
		this.lastdayVideoPlayNum = lastdayVideoPlayNum;
	}

	public Integer getLastdayAddFansNum() {
		return lastdayAddFansNum;
	}

	public void setLastdayAddFansNum(Integer lastdayAddFansNum) {
		this.lastdayAddFansNum = lastdayAddFansNum;
	}

	public Integer getLastdayAddLikeNum() {
		return lastdayAddLikeNum;
	}

	public void setLastdayAddLikeNum(Integer lastdayAddLikeNum) {
		this.lastdayAddLikeNum = lastdayAddLikeNum;
	}

	public Integer getTotalTradeAmount() {
		return totalTradeAmount;
	}

	public void setTotalTradeAmount(Integer totalTradeAmount) {
		this.totalTradeAmount = totalTradeAmount;
	}

	public Integer getLastdayOrderNum() {
		return lastdayOrderNum;
	}

	public void setLastdayOrderNum(Integer lastdayOrderNum) {
		this.lastdayOrderNum = lastdayOrderNum;
	}

	public Integer getLastdayTradeAmount() {
		return lastdayTradeAmount;
	}

	public void setLastdayTradeAmount(Integer lastdayTradeAmount) {
		this.lastdayTradeAmount = lastdayTradeAmount;
	}

	public Integer getTotalAccountAmount() {
		return totalAccountAmount;
	}

	public void setTotalAccountAmount(Integer totalAccountAmount) {
		this.totalAccountAmount = totalAccountAmount;
	}

	public Integer getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(Integer withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

}
