package com.baoke.user.domain.model;

/**
 * 微信token请求返回
 * 
 * @author zjm
 * @Date: 2018年6月12日 下午3:13:31
 */
public class WechatTokenResponse {

	/** 接口调用凭证 */
	private String access_token;

	/** access_token接口调用凭证超时时间，单位（秒） */
	private Long expires_in;

	/** 用户刷新access_token */
	private String refresh_token;

	/** 授权用户唯一标识 */
	private String openid;

	/** 用户授权的作用域，使用逗号（,）分隔 */
	private String scope;

	/** 异常时返回 */
	private Long errcode;

	/** 异常时返回 */
	private String errmsg;

	public Long getErrcode() {
		return errcode;
	}

	public void setErrcode(Long errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
