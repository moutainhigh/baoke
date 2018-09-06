package com.baoke.wechat.domain.encrypt;

import java.io.Serializable;

/**
 * 微信用户信息的密文数据
 * 
 * @author: wyj
 * @date: 2018年7月6日 上午10:02:34
 */
public class WechatUserInfoEncrypt implements Serializable {

	private static final long serialVersionUID = 9178749624343429636L;

	private String code;

	private String encryptedData;

	private String iv;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

}
