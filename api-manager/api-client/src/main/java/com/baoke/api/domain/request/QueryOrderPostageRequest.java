package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.PostageInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查看物流
 * 
 * @author zdy
 * @date: 2018年7月10日 下午8:31:33
 */
public class QueryOrderPostageRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	// 订单号
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public BaseDto convert() throws MainException {
		PostageInfoDto postageInfoDto = new PostageInfoDto();
		postageInfoDto.setOrderNo(orderNo);
		return postageInfoDto;
	}

}
