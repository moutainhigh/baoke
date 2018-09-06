package com.baoke.common.dto.seller;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 商家认证状态
 * 
 * @author ljj
 * @date: 2018年7月2日 下午2:52:24
 */
public class SellerAudioStatusDto extends BaseResultDto {

	private static final long serialVersionUID = -5409673185002708105L;

	private Integer Status;

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

}
