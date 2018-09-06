package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.OrderInfoDto;

/**
 * 订单列表
 * 
 * @author wyh
 * @date 2018年6月30日 下午3:00:32
 *
 */
public class OrderListDto extends BaseResultDto {

	private static final long serialVersionUID = 4725919692712283479L;

	private List<OrderInfoDto> orderList;

	private PageInfo pageInfo;

	public OrderListDto() {
	}

	public OrderListDto(boolean success, String message) {
		super(success, message);
	}

	public OrderListDto(boolean success, String message, List<OrderInfoDto> orderList, PageInfo pageInfo) {
		super(success, message);
		this.orderList = orderList;
		this.pageInfo = pageInfo;
	}

	public List<OrderInfoDto> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderInfoDto> orderList) {
		this.orderList = orderList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
