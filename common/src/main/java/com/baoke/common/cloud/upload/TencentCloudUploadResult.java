package com.baoke.common.cloud.upload;

import java.io.Serializable;

/**
 * 腾讯云上传结果
 * 
 * @author: wyj
 * @date: 2018年7月4日 下午5:12:51
 */
public class TencentCloudUploadResult implements Serializable {

	private static final long serialVersionUID = 7107789633735684205L;

	// 是否成功
	private boolean isSuccess;

	// 错误信息
	private String errorMessage;

	// 图片地址(或视频抽图地址)
	private String imgUrl;

	// 视频地址(转码后)
	private String videoUrl;

	public TencentCloudUploadResult() {
		super();
	}

	public TencentCloudUploadResult(boolean isSuccess, String errorMessage) {
		super();
		this.isSuccess = isSuccess;
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

}
