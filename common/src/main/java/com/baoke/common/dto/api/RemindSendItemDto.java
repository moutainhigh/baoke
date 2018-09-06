package com.baoke.common.dto.api;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 提醒发货
 * 
 * @author wyh
 * @date 2018年6月30日 上午10:40:31
 *
 */
public class RemindSendItemDto extends BaseResultDto {
	private static final long serialVersionUID = -7268090063816809050L;

	private String orderNo;

	public RemindSendItemDto() {
		super();
	}

	public RemindSendItemDto(String orderNo) {
		super();
		this.orderNo = orderNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
