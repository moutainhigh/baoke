package com.baoke.common.dto.api;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 主播分享数据
 * 
 * @author zdy
 * @date: 2018年7月26日 下午8:54:27
 */
public class SellerShareDto extends BaseResultDto {
	private static final long serialVersionUID = -7268090063816809050L;

	private String title;// 分享标题
	private String content;// 分享内容
	private String url;// 分享URL
	private String iconImgUrl;// 分享小图
	private String imgUrl;// 分享大图
	private String qrCodeUrl;// 二维码中URL

	public SellerShareDto(boolean success, String message) {
		super(success, message);
	}

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

}
