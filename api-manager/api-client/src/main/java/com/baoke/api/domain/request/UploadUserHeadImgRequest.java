package com.baoke.api.domain.request;

import org.apache.commons.codec.binary.Base64;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.FileDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.StringUtil;

public class UploadUserHeadImgRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	// 用户头像文件字符串
	private String headImg;

	private String fileType;// 文件类型

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public BaseDto convert() throws MainException {
		FileDto fileDto = new FileDto();
		if (!StringUtil.isEmpty(this.headImg)) {
			byte[] headImgByte = Base64.decodeBase64(headImg);
			fileDto.setFile(headImgByte);
		}
		fileDto.setFileType(this.fileType);
		return fileDto;
	}

}
