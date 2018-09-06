package com.baoke.common.dto;

import org.springframework.web.multipart.MultipartFile;

import com.baoke.common.dto.base.BaseResultDto;

public class MultipartFileDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	// 文件
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
