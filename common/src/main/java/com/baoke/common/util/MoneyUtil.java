package com.baoke.common.util;

import java.text.DecimalFormat;

/**
 * 货币工具类
 *
 * @author zjj
 * @date 2018年7月4日 下午8:29:36
 */
public class MoneyUtil {

	private static int HUNDRED = 100;

	/**
	 * 价格分转元
	 * 
	 * @author zdy
	 * @date: 2018年6月15日 下午4:36:06
	 * @param param
	 * @return
	 */
	public static String changeF2Y(Integer price) {
		if (price == null) {
			return "";
		}
		double prices = price / HUNDRED + (price % HUNDRED * 0.01);
		return roundingDigitsZeroToString(prices);
	}

	/**
	 * 去掉后面无用的零
	 * 
	 * @author zdy
	 * @date: 2018年6月15日 上午10:04:02
	 * @param param
	 * @return
	 */
	public static String roundingDigitsZeroToString(double param) {
		String s = new DecimalFormat("0.00").format(param);
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉后面无用的零
			s = s.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
		}
		return s;
	}

	/**
	 * 元转换为分
	 * 
	 * @author zjj
	 * @date 2018年7月4日 下午8:29:49
	 * @param amount
	 * @return
	 */
	public static int changeY2F(String amount) {
		if (!StringUtil.isPositiveNumber(amount)) {
			return 0;
		}
		String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥ 或者$的金额
		int index = currency.indexOf(".");
		int length = currency.length();
		int fen = 0;
		if (index == -1) {
			fen = Integer.valueOf(currency + "00");
		} else if (length - index >= 3) {
			fen = Integer.valueOf((currency.substring(0, index + 3)).replace(".", ""));
		} else if (length - index == 2) {
			fen = Integer.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
		} else {
			fen = Integer.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
		}
		return fen;
	}

	/**
	 * 钱数（元、分）相加
	 * 
	 * @author zjj
	 * @date 2018年7月7日 下午8:01:50
	 * @param num1
	 *            单位元
	 * @param num2
	 *            单位分
	 * @return
	 */
	public static String add(String num1, Integer num2) {
		if (null == num2) {
			num2 = 0;
		}
		int sum = changeY2F(num1) + num2;
		return changeF2Y(sum);
	}

	public static void main(String[] args) {
		Integer num2 = 2;
		String num1 = "12.0";
		String add = add(num1, num2);
		System.out.println(add);
	}
}
