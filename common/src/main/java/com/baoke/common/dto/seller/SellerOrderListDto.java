package com.baoke.common.dto.seller;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;

/**
 * 卖家订单列表dto
 * 
 * @author: wyj
 * @date: 2018年7月12日 下午2:07:03
 */
public class SellerOrderListDto extends BaseResultDto {

	private static final long serialVersionUID = 811553157454790997L;

	private List<SellerOrderDto> orderList;

	private String totalMoney;

	private PageInfo pageInfo;

	public SellerOrderListDto() {
	}

	public SellerOrderListDto(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public SellerOrderListDto(List<SellerOrderDto> orderList, String totalMoney) {
		this.orderList = orderList;
		this.totalMoney = totalMoney;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<SellerOrderDto> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<SellerOrderDto> orderList) {
		this.orderList = orderList;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

}
