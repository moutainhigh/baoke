package com.baoke.common.util;

/**
 * orderNo生成工具类
 *
 * @author zjj
 * @date 2018年6月27日 上午9:18:06
 */
public class OrderNoUtil {
	
	/** 父订单前缀*/
	public static final String PARENT_ORDER_PREFIX = "PO"; 
	
	/** 子订单前缀*/
	public static final String SUB_ORDER_PREFIX = "SO"; 
	
	/**
	 * 订单NO生成方法    生成策略（待定）：前缀+System.nanoTime
	 * 
	 * 后期可考虑 redis incr
	 * 
	 * @author zjj
	 * @date 2018年6月27日 上午9:26:03
	 * @param prefix
	 * @return
	 */
	public static String getOrderNo(String prefix) {
		return prefix + System.nanoTime();
	}
	
	public static void main(String[] args) {
		String orderNo = getOrderNo(SUB_ORDER_PREFIX);
		System.out.println(orderNo);
	}
	
}
