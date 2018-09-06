package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.OrderListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.OrderInfoDto;

/**
 * 查询我的订单列表响应
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午2:54:36
 */
public class QueryMyOrderResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1655795840839022067L;

	// 订单列表
	private List<OrderInfoDto> orderList;

	private Integer curPageNo;// 当前页数

	private Integer pageSize;// 每页显示的记录数

	public List<OrderInfoDto> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderInfoDto> orderList) {
		this.orderList = orderList;
	}

	public Integer getCurPageNo() {
		return curPageNo;
	}

	public void setCurPageNo(Integer curPageNo) {
		this.curPageNo = curPageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		OrderListDto orderListDto = (OrderListDto) baseResultDto;
		this.orderList = orderListDto.getOrderList();
		if (orderListDto.getPageInfo() != null) {
			this.pageSize = orderListDto.getPageInfo().getPageSize();
		}
		return this;
	}

}
