package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.base.BaseResultDto;

/**
 * 查询播主信息manager返回结果集
 * 
 * @author zjm
 * @Date: 2018年6月5日 上午9:44:04
 */
public class QuerySellerInfoResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	/** 播主ID */
	private String sellerCode;

	/** 播主ID */
	private String sellerId;

	/** 播主头像 */
	private String sellerImgUrl;

	/** 播主昵称 */
	private String sellerNickName;

	/** 作品数 */
	private Integer videoNum;

	public QuerySellerInfoResponse(String sellerId, String sellerImgUrl, String sellerNickName, Integer videoNum) {
		this.sellerId = sellerId;
		this.sellerImgUrl = sellerImgUrl;
		this.sellerNickName = sellerNickName;
		this.videoNum = videoNum;
	}

	public QuerySellerInfoResponse() {
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}

	public String getSellerImgUrl() {
		return sellerImgUrl;
	}

	public void setSellerImgUrl(String sellerImgUrl) {
		this.sellerImgUrl = sellerImgUrl;
	}

	public String getSellerNickName() {
		return sellerNickName;
	}

	public void setSellerNickName(String sellerNickName) {
		this.sellerNickName = sellerNickName;
	}

	public Integer getVideoNum() {
		return videoNum;
	}

	public void setVideoNum(Integer videoNum) {
		this.videoNum = videoNum;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		return this;
	}

}
