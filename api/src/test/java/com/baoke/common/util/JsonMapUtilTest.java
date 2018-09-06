package com.baoke.common.util;

import java.util.ArrayList;
import java.util.List;

import com.baoke.api.constant.ApiProcessStatus;
import com.baoke.api.domain.response.QueryMyOrderResponse;
import com.baoke.common.dto.info.OrderInfoDto;
import com.baoke.common.dto.info.OrderItemInfoDto;
import com.baoke.common.util.api.json.JsonApiUtil;

public class JsonMapUtilTest {

	public static void main(String[] args) {

		OrderInfoDto orderDto1 = new OrderInfoDto();
		orderDto1.setOrderNo("55");
		orderDto1.setOrderStatus(1);
		orderDto1.setParentOrderNo("15");
		OrderInfoDto orderDto2 = new OrderInfoDto();
		orderDto2.setOrderNo("55");
		orderDto2.setOrderStatus(1);
		orderDto2.setParentOrderNo("15");

		List<OrderItemInfoDto> orderSubList1 = new ArrayList<OrderItemInfoDto>();
		OrderItemInfoDto OrderSubDto1 = new OrderItemInfoDto();
		OrderSubDto1.setItemAttr1Name("15");
		OrderSubDto1.setItemAttr2Name("56");

		OrderItemInfoDto OrderSubDto2 = new OrderItemInfoDto();
		OrderSubDto2.setItemAttr1Name("15");
		OrderSubDto2.setItemAttr2Name("56");

		orderSubList1.add(OrderSubDto1);
		orderSubList1.add(OrderSubDto2);

		List<OrderItemInfoDto> orderSubList2 = new ArrayList<OrderItemInfoDto>();
		OrderItemInfoDto OrderSubDto3 = new OrderItemInfoDto();
		OrderSubDto3.setItemAttr1Name("15");
		OrderSubDto3.setItemAttr2Name("56");

		OrderItemInfoDto OrderSubDto4 = new OrderItemInfoDto();
		OrderSubDto4.setItemAttr1Name("15");
		OrderSubDto4.setItemAttr2Name("56");

		orderSubList2.add(OrderSubDto3);
		orderSubList2.add(OrderSubDto4);

		orderDto1.setOrderItemList(orderSubList1);
		orderDto2.setOrderItemList(orderSubList2);

		List<OrderInfoDto> orderList = new ArrayList<OrderInfoDto>();
		orderList.add(orderDto1);
		orderList.add(orderDto2);

		QueryMyOrderResponse queryMyOrderResponse = new QueryMyOrderResponse();
		queryMyOrderResponse.setApiStatus(ApiProcessStatus.NEED_LOGIN);
		queryMyOrderResponse.setResultMessage("445");
		queryMyOrderResponse.setOrderList(orderList);

		System.out.println(JsonApiUtil.convertToJson(queryMyOrderResponse));
	}
}
