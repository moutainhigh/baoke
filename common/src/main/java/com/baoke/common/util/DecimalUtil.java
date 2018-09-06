package com.baoke.common.util;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * BigDecimal计算类
 * 
 * @author: wyj
 * @date: 2018年6月15日 下午6:22:42
 */
public class DecimalUtil {
	private static MathContext mc = MathContext.DECIMAL128;

	private static int DEFAULT_SCALE = 1;

	/**
	 * 提供精确的乘法运
	 * 
	 * @author wyj
	 * @date: 2018年6月14日 下午8:31:17
	 * @param param1
	 *            被乘数
	 * @param param2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static BigDecimal multiply(double param1, double param2) {
		BigDecimal p1 = BigDecimal.valueOf(param1);
		BigDecimal p2 = BigDecimal.valueOf(param2);
		return p1.multiply(p2);
	}

	/**
	 * 提供（相对）精确的除法运算
	 * 
	 * @author wyj
	 * @date: 2018年6月14日 下午8:32:59
	 * @param param1
	 *            被除数
	 * @param param2
	 *            除数
	 * @return 两个参数的商
	 */
	public static BigDecimal divide(double param1, double param2) {
		BigDecimal p1 = BigDecimal.valueOf(param1);
		BigDecimal p2 = BigDecimal.valueOf(param2);
		if (param2 == 0)
			return BigDecimal.valueOf(0);
		return p1.divide(p2, mc);
	}

	/**
	 * 保留指定位小数(四舍五入)
	 * 
	 * @author wyj
	 * @date: 2018年6月14日 下午8:32:59
	 * @param param
	 */
	public static double setScale(BigDecimal param, Integer scale) {
		if (scale == null) {
			return param.setScale(DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return param.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 保留1位小数(四舍五入)
	 * 
	 * @author wyj
	 * @date: 2018年6月14日 下午8:32:59
	 * @param param
	 */
	public static double setScale(BigDecimal param) {
		return setScale(param, DEFAULT_SCALE);
	}

	/**
	 * 保留1位小数(四舍五入)
	 * 
	 * @author wyj
	 * @date: 2018年6月14日 下午8:32:59
	 * @param param
	 */
	public static double setScale(double param) {
		return setScale(BigDecimal.valueOf(param));
	}

	/**
	 * 保留指定位小数(四舍五入)
	 * 
	 * @author wyj
	 * @date: 2018年6月14日 下午8:32:59
	 * @param param
	 */
	public static double setScale(double param, Integer scale) {
		return setScale(BigDecimal.valueOf(param), scale);
	}

	/**
	 * 提供（相对）精确的除法运算 保留4位数。
	 * 
	 * @author wyj
	 * @date: 2018年6月14日 下午8:36:07
	 * @param param1
	 * @param param2
	 * @return 两个参数的商精确到4位数后的double
	 */
	public static double divideToDouble(double param1, double param2) {
		return divide(param1, param2).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
