package com.baoke.wechat.util;

import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.alibaba.fastjson.JSONObject;
import com.baoke.wechat.domain.WechatUserInfoModel;

/**
 * 微信密文非对称解密
 * 
 * @author: wyj
 * @date: 2018年7月6日 下午2:15:49
 */
public class WxEncryptUtil {
	public static WechatUserInfoModel getUserInfo(String encryptedData, String sessionKey, String iv) {
		final Base64 base64 = new Base64();
		// 被加密的数据
		byte[] encryptedDataByte = base64.decode(encryptedData);
		// 加密秘钥
		byte[] sessionKeyByte = base64.decode(sessionKey);
		// 偏移量
		byte[] ivByte = base64.decode(iv);

		try {
			// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
			int base = 16;
			if (sessionKeyByte.length % base != 0) {
				int groups = sessionKeyByte.length / base + (sessionKeyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(sessionKeyByte, 0, temp, 0, sessionKeyByte.length);
				sessionKeyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			SecretKeySpec secretKeySpec = new SecretKeySpec(sessionKeyByte, "AES");
			AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("AES");
			algorithmParameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, algorithmParameters);// 初始化
			byte[] resultByte = cipher.doFinal(encryptedDataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");
				return JSONObject.parseObject(result, WechatUserInfoModel.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
