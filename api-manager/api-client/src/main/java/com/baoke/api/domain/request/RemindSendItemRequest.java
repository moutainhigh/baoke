package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.api.RemindSendItemDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.exception.base.MainException;

/**
 * 提醒发货请求
 * 
 * @author: wyj
 * @date: 2018年6月14日 上午10:22:05
 */
public class RemindSendItemRequest extends BaseRequestParam {

	private static final long serialVersionUID = 6175976515053610527L;

	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public BaseDto convert() throws MainException {
		return new RemindSendItemDto(orderNo);
	}

}
