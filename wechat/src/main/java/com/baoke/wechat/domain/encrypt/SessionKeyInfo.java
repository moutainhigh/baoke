package com.baoke.wechat.domain.encrypt;

import java.io.Serializable;

/**
 * 会话密钥实体类
 * 
 * @author: wyj
 * @date: 2018年7月5日 下午4:19:28
 */
public class SessionKeyInfo implements Serializable {

	private static final long serialVersionUID = -2884384706776433243L;

	private Integer errcode;

	private String errmsg;

	private String openid;

	private String session_key;

	public String getOpenid() {
		return openid;
	}

	public boolean valid() {
		return errcode == null || errcode == 0;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSession_key() {
		return session_key;
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
}
