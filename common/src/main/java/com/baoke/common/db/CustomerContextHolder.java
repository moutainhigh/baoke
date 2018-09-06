package com.baoke.common.db;

/**
 * 用ThreadLocal来设置当前线程使用哪个dataSource
 * 
 * @author zjm
 * @Date: 2018年6月1日 下午1:42:35
 */
public class CustomerContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setCustomerType(String customerType) {
		contextHolder.set(customerType);
	}

	public static String getCustomerType() {
		return contextHolder.get();
	}

	public static void clearCustomerType() {
		contextHolder.remove();
	}

}
