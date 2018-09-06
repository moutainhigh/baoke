package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.api.SellerShareDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.ParamInvalidException;

public class QuerySellerShareResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	private String title;// 分享标题
	private String content;// 分享内容
	private String url;// 分享URL
	private String iconImgUrl;// 分享小图
	private String imgUrl;// 分享大图
	private String qrCodeUrl;// 二维码中URL

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconImgUrl() {
		return iconImgUrl;
	}

	public void setIconImgUrl(String iconImgUrl) {
		this.iconImgUrl = iconImgUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) throws ParamInvalidException {
		SellerShareDto sellerShareDto = (SellerShareDto) baseResultDto;
		this.title = sellerShareDto.getTitle();
		this.content = sellerShareDto.getContent();
		this.iconImgUrl = sellerShareDto.getIconImgUrl();
		this.imgUrl = sellerShareDto.getImgUrl();
		this.url = sellerShareDto.getUrl();
		this.qrCodeUrl = sellerShareDto.getQrCodeUrl();
		return this;
	}

}
