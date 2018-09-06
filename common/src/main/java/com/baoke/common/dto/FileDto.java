package com.baoke.common.dto;

import com.baoke.common.dto.base.BaseResultDto;

public class FileDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private byte[] file;// 文件byte数组

	private String fileType;// 文件类型

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
