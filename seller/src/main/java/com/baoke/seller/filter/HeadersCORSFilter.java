package com.baoke.seller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 拦截器
 * 
 * @author zjm
 * @date: 2018年7月9日 下午4:56:15
 */
public class HeadersCORSFilter implements Filter {

	private static Logger logger = Logger.getLogger(HeadersCORSFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;

			// 跨域
			String origin = httpRequest.getHeader("Origin");
			if (origin == null) {
				httpResponse.addHeader("Access-Control-Allow-Origin", "*");
			} else {
				httpResponse.addHeader("Access-Control-Allow-Origin", origin);
			}
			httpResponse.addHeader("Access-Control-Allow-Headers",
					"Origin, x-requested-with, Content-Type, Accept,X-Cookie");
			httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
			httpResponse.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS,DELETE");
			if (httpRequest.getMethod().equals("OPTIONS")) {
				httpResponse.setStatus(HttpServletResponse.SC_OK);
				return;
			}
			chain.doFilter(request, response);
		} catch (Exception e) {
			logger.error("Exception in crossDomainFilter.doFilter", e);
			throw e;
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
