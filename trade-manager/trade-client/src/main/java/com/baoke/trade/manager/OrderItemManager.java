package com.baoke.trade.manager;

import java.util.List;

import com.baoke.trade.domain.OrderItem;

/**
 * orderItemManager
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:07:44
 */
public interface OrderItemManager {

	/**
	 * 根据parentOrderNo查询orderItem列表
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:08:03
	 */
	List<OrderItem> queryOrderItemByParentOrderNo(String parentOrderNo);

	/**
	 * 根据子订单orderNoiList查询orderItem列表
	 * 
	 * @author zjj
	 * @date 2018年7月3日 下午5:40:00
	 * @param orderNoList
	 * @return
	 */
	List<OrderItem> queryOrderItemByOrderNos(List<String> orderNoList);

	/**
	 * 根据OrderNo查询orderItem列表
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午3:08:03
	 */
	List<OrderItem> queryOrderItemByOrderNo(String orderNo);

	/**
	 * 根据parentOrderNo， itemId， itemDetailId查询OrderItem
	 * 
	 * @author zjj
	 * @date 2018年7月11日 下午3:08:51
	 * @param parentOrderNo
	 * @param itemId
	 * @param itemDetailId
	 * @return
	 */
	OrderItem queryOrderItemByParentOrderNoAndItemId(String parentOrderNo, Long itemId, Long itemDetailId);

	/**
	 * 批量保存orderItem
	 * 
	 * @author zjj
	 * @date 2018年7月1日 下午5:30:04
	 * @param orderSubList
	 * @return
	 */
	int addBatchOrderItem(List<OrderItem> orderSubList);

	/**
	 * 修改商品数量和总价格
	 * 
	 * @author zjj
	 * @date 2018年7月11日 下午2:30:47
	 * @param orderItem
	 * @return
	 */
	int modifyOrderItemPriceAndNum(OrderItem orderItem);

	/**
	 * 批量修改运费和地址信息
	 * 
	 * @author zjj
	 * @date 2018年7月11日 下午5:11:59
	 * @param orderItemList
	 * @return
	 */
	int modifyBatchOrderItemPostage(List<OrderItem> orderItemList);

}
