package com.baoke.admin.util;

import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpClientHelper {
	public static int TIME_OUT_IN_MILLISECONDS =1000 * 60;
	
//	private final static int BUFFER = 1024; 
	
	private static final Log log = LogFactory.getLog(HttpClientHelper.class);
	
	protected static HttpClient httpClient = null;
	static {
		MultiThreadedHttpConnectionManager mgr = new MultiThreadedHttpConnectionManager();
		mgr.getParams().setDefaultMaxConnectionsPerHost(64);
		mgr.getParams().setMaxTotalConnections(256);
		mgr.getParams().setConnectionTimeout(TIME_OUT_IN_MILLISECONDS);
		mgr.getParams().setSoTimeout(TIME_OUT_IN_MILLISECONDS);
		
		httpClient = new HttpClient(mgr);
		httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.43 Safari/537.31");
	}
	
	private HttpClientHelper(){
	}
	
	public static HttpClient getHttpClient(){
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(TIME_OUT_IN_MILLISECONDS);
		return httpClient;
	}
	
	public static String getStringByUrl(String url, boolean needResponse){
		return processGet(getHttpClient(), null, url, needResponse, "", false);
	}
	
	
	
	public static String processGet(HttpClient client, GetMethod get, String url, boolean needResponse, String cookie, boolean needAppendCookies) {
		try {
			if (client == null || url == null || url.equals("") )
				return "";

			byte[] responseBody = null;
			String html = "";
			if (get == null){
				get = new GetMethod(url);
				get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
				get.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,TIME_OUT_IN_MILLISECONDS);
//				get.getParams().setSoTimeout(TIME_OUT_IN_MILLISECONDS);
			}
			
			if (!cookie.equals("")&&cookie.length()>0)
				get.setRequestHeader("Cookie", cookie);
			
			client.executeMethod(get);

			if (needAppendCookies) {
				client.getState().addCookies(client.getState().getCookies());
			}
			if (needResponse) {
				responseBody = get.getResponseBody();
				html = new String(responseBody, "GBK");
			}
			return html;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			log.error("processGet出错url="+url);
		} finally {
			get.releaseConnection();
		}
		return "";
	}	
	
	/**
	 * 带字符集编码方式无cookie以get方式获取数据
	 * 
	 * @param url
	 *            HTTP请求地址
	 * @param needResponse
	 *            是否需要返回结果
	 * @param responseCharsetName
	 *            返回结果编码集
	 * @return
	 */
	public static String getStringByUrl(String url, boolean needResponse,
			String responseCharsetName) {
		return processGet(getHttpClient(), null, url, needResponse, "", false,
				responseCharsetName);
	}

	

	public static String processGet(HttpClient client, GetMethod get,
			String url, boolean needResponse, String cookie,
			boolean needAppendCookies, String responseCharsetName) {
		byte[] responseBody = null;

		try {
			if (client == null || url == null || url.equals(""))
				return "";

			String html = "";

			if (get == null) {
				get = new GetMethod(url);
				get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
						new DefaultHttpMethodRetryHandler());
				get.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,
						TIME_OUT_IN_MILLISECONDS);
			}

			if (!cookie.equals("") && cookie.length() > 0)
				get.setRequestHeader("Cookie", cookie);

			client.executeMethod(get);

			if (needAppendCookies) {
				client.getState().addCookies(client.getState().getCookies());
			}
			if (needResponse) {
				responseBody = get.getResponseBody();
				html = new String(responseBody, responseCharsetName);
			}
			return html;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			log.error("processGet出错url=" + url);
		} finally {
			get.releaseConnection();
		}
		return "";
	}

	/**
	 * 以POST方式提交HTTP请求
	 * 
	 * @param url
	 *            HTTP地址
	 * @param needResponse
	 *            是否需要返回结果
	 * @param responseCharsetName
	 *            返回结果字符集
	 * @param params
	 *            提交的参数
	 * @return
	 */
	public static String postDataByUrl(String url, boolean needResponse,
			String responseCharsetName, Map<String, String> params) {
		return processPost(getHttpClient(), null, url, needResponse, "", false,
				responseCharsetName, params);
	}


	public static String processPost(HttpClient client, PostMethod post,
			String url, boolean needResponse, String cookie,
			boolean needAppendCookies, String responseCharsetName,
			Map<String, String> params) {

		NameValuePair[] data = null;
		byte[] responseBody = null;

		try {
			if (client == null || url == null || url.equals(""))
				return "";

			String html = "";

			if (post == null) {
				post = new PostMethod(url);
				post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
						new DefaultHttpMethodRetryHandler());
				post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,
						TIME_OUT_IN_MILLISECONDS);
				post.getParams().setContentCharset("UTF-8");
				post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			}

			if (!cookie.equals("") && cookie.length() > 0)
				post.setRequestHeader("Cookie", cookie);

			// 此处开始组装提交数据
			if (null != params && 0 != params.size()) {
				data = new NameValuePair[params.size()];
				int i = 0;
				for (String key : params.keySet()) {
					data[i] = new NameValuePair();
					data[i].setName(key);
					data[i].setValue(params.get(key));
					i++;
				}
			}

			post.setRequestBody(data);

			
			client.executeMethod(post);

			if (needAppendCookies) {
				client.getState().addCookies(client.getState().getCookies());
			}

			if (needResponse) {
				responseBody = post.getResponseBody();
				html = new String(responseBody, responseCharsetName);
			}

			return html;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			log.error("processPost出错url=" + url);
		} finally {
			post.releaseConnection();
		}

		return "";
	}
	
    public static String postDataByRequestBody(String url, String postData,boolean needResponse,
            String responseCharsetName) {
		return postDataByRequestBody(url,postData,needResponse,responseCharsetName,TIME_OUT_IN_MILLISECONDS);
	}
	
	
	/**
	 * 通过RequestBody传递请求参数，不使用名值对
	 * @param url
	 * @param needResponse
	 * @param responseCharsetName
	 * @param postData
	 * @return
	 */
	@SuppressWarnings("all")
    public static String postDataByRequestBody(String url, String postData,boolean needResponse,
            String responseCharsetName, int timeOut) {

        byte[] responseBody = null;
        HttpClient client = getHttpClient();
        PostMethod post = new PostMethod(url);
        try {
            String html = "";
            post.setRequestHeader("Content-type", "application/json; charset=utf-8");
            post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler());
            post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeOut);
            post.setRequestBody(postData);
            client.executeMethod(post);
            if (needResponse) {
                responseBody = post.getResponseBody();
                if(StringUtils.isEmpty(responseCharsetName)){
                    responseCharsetName = "utf-8";
                }
                html = new String(responseBody, responseCharsetName);
            }
            return html;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("postDataByRequestBody出错,url=" + url+",postData"+postData);
        } finally {
            post.releaseConnection();
        }
        return "";
    }
	
}
