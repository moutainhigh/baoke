package com.baoke.api.util;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.baoke.common.exception.ParamInvalidException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BkBase64 {

	private static final Log log = LogFactory.getLog(BkBase64.class);
	public static final BASE64Encoder SUN_BASE64_ENCODE = new BASE64Encoder();
	public static final BASE64Decoder SUN_BASE64_DECODE = new BASE64Decoder();

	/**
	 * 64进制和10进制的转换类
	 * 
	 * @author Administrator
	 * 
	 */

	public static String long2Base64(long l) throws ParamInvalidException {
		try {
			byte[] b = new byte[8];
			for (int i = 0; i < 8; i++) {
				b[7 - i] = (byte) (l >>> (i * 8));
			}
			String result = SUN_BASE64_ENCODE.encode(Base64.encodeBase64(b));
			// 反向转换不成功的时候 打印一下日志
			long lr = BkBase64.base642long(result);
			if (l != lr) {
				log.error("debuglong2Base64 error,userid=" + l + "result=" + result);
			}
			return result;
		} catch (Exception e) {
			throw new ParamInvalidException(e);
		}
	}

	public static long base642long(String s) throws ParamInvalidException {
		try {
			byte[] b = SUN_BASE64_DECODE.decodeBuffer(s);
			b = Base64.decodeBase64(b);
			long res = 0;
			for (int i = 0; i < 8; i++) {
				res <<= 8;
				res ^= (long) b[i] & 0xFF;
			}
			return res;
		} catch (Exception e) {
			throw new ParamInvalidException(e);
		}
	}

	public static String base64encode(String s) {
		return SUN_BASE64_ENCODE.encode(s.getBytes());
	}

	public static String base64decode(String s) {
		try {
			return new String(SUN_BASE64_DECODE.decodeBuffer(s));
		} catch (IOException e) {
			return null;
		}
	}

}
