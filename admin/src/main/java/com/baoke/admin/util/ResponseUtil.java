package com.baoke.admin.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * 请求响应Util
 */
public class ResponseUtil {

	/**
	 * print json object
	 * @param response
	 * @param obj
	 * @throws IOException
	 */
	public static void writeGson(HttpServletResponse response, Object obj) throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(json);
	}
}
