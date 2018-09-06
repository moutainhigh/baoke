package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.OrderInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查詢我的訂單請求
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:50:04
 */
public class QueryMyOrderRequest extends BaseRequestParam {
	private static final long serialVersionUID = -2455517648055390074L;

	// 订单状态
	private Integer orderStatus;

	private Integer curPageNo = 1;// 当前页数

	private Integer pageSize = 12;// 每页显示的记录数

	public Integer getCurPageNo() {
		return curPageNo;
	}

	public void setCurPageNo(Integer curPageNo) {
		this.curPageNo = curPageNo;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public BaseDto convert() throws MainException {
		OrderInfoDto orderDto = new OrderInfoDto(curPageNo, pageSize);
		if (!Integer.valueOf(0).equals(orderStatus)) {
			orderDto.setOrderStatus(orderStatus);
		}
		return orderDto;
	}

}
