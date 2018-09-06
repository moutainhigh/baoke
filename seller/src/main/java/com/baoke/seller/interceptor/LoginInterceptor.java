package com.baoke.seller.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.info.UserInfoDto;
import com.baoke.common.util.StringUtil;

/**
 * 拦截器
 * 
 * @author zjm
 * @date: 2018年7月3日 下午3:36:05
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	private List<String> exceptUrls;

	public List<String> getExceptUrls() {
		return exceptUrls;
	}

	public void setExceptUrls(List<String> exceptUrls) {
		this.exceptUrls = exceptUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String requestUri = request.getRequestURI();
		if (requestUri.startsWith(request.getContextPath())) {
			requestUri = requestUri.substring(request.getContextPath().length(), requestUri.length());
		}
		// 系统根目录
		if (StringUtil.equals("/", requestUri)) {
			return true;
		}
		// 放行exceptUrls中配置的url
		for (String url : exceptUrls) {
			if (url.endsWith("/**")) {
				if (requestUri.startsWith(url.substring(0, url.length() - 3))) {
					return true;
				}
			} else if (requestUri.startsWith(url)) {
				return true;
			}
		}

		UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
		if (null == userInfoDto) {
			logger.warn("user is empty, need login!");
			returnErrorMessage(response);
			return false;
		}
		return true;
	}

	private void returnErrorMessage(HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(JSONObject.toJSONString(new ResponseResult(ResponseResultCodeEnum.FORBIDDEN, ResponseResultCodeEnum.FORBIDDEN.getName())));
		out.flush();
	}
}
