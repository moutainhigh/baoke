package com.baoke.job.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JobLoginFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 3153942057964342729L;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String jobLoginCookie = null;
		
		if (req.getCookies() != null) {
			for (Cookie cookie : req.getCookies()) {
				if (cookie.getName().equals("job_login")) {
					jobLoginCookie = cookie.getValue();
					break;
				}
			}
		}
		
		if(null == jobLoginCookie) {
			jobLoginCookie = UUID.randomUUID().toString().replace("-", "");
			Cookie cookieLogin = new Cookie("job_login", jobLoginCookie);
			cookieLogin.setMaxAge(60 * 60 * 2);
			resp.addCookie(cookieLogin);
		}
		
		req.getSession().setAttribute("job_login", jobLoginCookie);
		
		try{
			chain.doFilter(request, response);
		}catch(Exception e){
		}finally{
	    }
	}

}
