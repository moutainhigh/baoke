package com.baoke.trade.util;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import com.baoke.common.util.MD5Util;
import com.baoke.common.util.StringUtil;

public class WechatPayUtil {

	// 微信支付签名算法sign
	public static String createSign(String characterEncoding, SortedMap<String, String> parameters, String priKey) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> parametersSet = parameters.entrySet(); // 所有非空参数值的参数按照参数名按照ASCII码从小到大排序
		Iterator<Entry<String, String>> it = parametersSet.iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String key = entry.getKey();
			String value = entry.getValue();
			if (!StringUtil.isBlank(value) && !"sign".equals(key) && !"key".equals(key)) {
				sb.append(key + "=" + value + "&");
			}
		}
		sb.append("key=").append(priKey);
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}
}
