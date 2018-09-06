package com.baoke.api.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.api.service.RequestParamService;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.ParamParseException;
import com.baoke.common.util.BeanUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.common.util.api.GZip;
import com.baoke.common.util.api.Md5Encrypt;
import com.baoke.common.util.api.RC4Utils;
import com.baoke.common.util.api.json.JsonApiUtil;

public class RequestParamServiceImpl implements RequestParamService {

	public static final Log log = LogFactory.getLog(RequestParamServiceImpl.class);

	@Value("${requestParam.decode.key}")
	public String decodeKey;

	@Value("${requestParam.request.data.key}")
	public String requestDataKey;

	public static final String REQUEST_DOMAIN = "com.baoke.api.domain.request.";

	@Override
	public Map<String, String> parseParams(HttpServletRequest request) throws ParamParseException {

		Map<String, String> map = parseParamsFromRequest(request);
		String requestData = map.get("requestData");
		if (StringUtil.isBlank(requestData)) {
			throw new ParamParseException();
		}

		requestData = requestData.trim();

		if (!requestData.startsWith("{") && !requestData.endsWith("}")) {
			try {
				requestData = URLDecoder.decode(requestData, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new ParamParseException(e);
			}
		}

		map.put("requestData", requestData);
		return map;
	}

	private Map<String, String> parseParamsFromRequest(HttpServletRequest request) throws ParamParseException {

		String requestData = null;
		String sign = null;

		// 获取 加密标识
		String encrypt = request.getHeader("Encrypt");

		if (StringUtil.isNotBlank(encrypt) && "yes".equalsIgnoreCase(encrypt)) {

			byte[] data;
			try {
				data = readBytes(request.getInputStream());
			} catch (IOException e) {
				throw new ParamParseException(e);
			}
			byte[] data1 = RC4Utils.decry_RC4(data,
					"79927f330445959dd6a0be6a2d2a27668bee90657453f6f4d5ea7f7a0fc34204c48baa1c6e3031f78d27ab61d1e2b414b6ec6f38963ed5d81fb3dc065661bbf5");
			String queryStr = GZip.uncompressToString(data1, "utf-8");
			int requestEnd = queryStr.indexOf("&", queryStr.indexOf("requestData="));
			requestEnd = requestEnd == -1 ? queryStr.length() : requestEnd;
			requestData = queryStr.substring(queryStr.indexOf("requestData=") + 12, requestEnd);
			int signEnd = queryStr.indexOf("&", queryStr.indexOf("sign="));
			signEnd = signEnd == -1 ? queryStr.length() : signEnd;
			sign = queryStr.substring(queryStr.indexOf("sign=") + 8, signEnd);

			if (StringUtil.isBlank(requestData)) {
				return parseEncryptParams(request);
			}
		} else {
			if (StringUtil.isBlank(request.getParameter("requestData"))) {
				return parseGETParams(request);
			}
			requestData = request.getParameter("requestData");
			sign = request.getParameter("sign");
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("requestData", requestData);
		map.put("sign", sign);

		return map;
	}

	// 解析加密参数
	private Map<String, String> parseEncryptParams(HttpServletRequest request) throws ParamParseException {
		byte[] bytes = new byte[10240];
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		InputStream is;
		String paramterStr;
		int readLen;
		try {
			is = request.getInputStream();
			while ((readLen = is.read(bytes)) != -1) {
				os.write(bytes, 0, readLen);
			}

			bytes = os.toByteArray();
			bytes = RC4Utils.decry_RC4(bytes, decodeKey);
			bytes = ungzip(bytes);

			paramterStr = URLDecoder.decode(new String(bytes, "UTF-8"), "UTF-8");
		} catch (Exception e) {
			throw new ParamParseException(e);
		}
		if (paramterStr != null && !paramterStr.isEmpty()) {
			Map<String, String> params = decodeParamterStrToMap(paramterStr);
			params.putAll(parseGETParams(request));
			return params;
		} else {
			return null;
		}
	}

	private static byte[] readBytes(InputStream in) throws IOException {
		BufferedInputStream reader = new BufferedInputStream(in);
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
		byte[] temp = new byte[256];
		int size = 0;
		while ((size = reader.read(temp)) != -1) {
			bos.write(temp, 0, size);
		}
		return bos.toByteArray();
	}

	private Map<String, String> parseGETParams(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Enumeration<String> paramterNames = request.getParameterNames();
		String pName;

		while (paramterNames.hasMoreElements()) {
			pName = paramterNames.nextElement();
			params.put(pName, request.getParameter(pName));
		}
		return params;
	}

	private byte[] ungzip(byte[] params) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		GZIPInputStream zis = null;
		byte[] buff = new byte[params.length];
		int readLen = 0;
		try {
			try {
				zis = new GZIPInputStream(new ByteArrayInputStream(params));
				while ((readLen = zis.read(buff)) != -1) {
					os.write(buff, 0, readLen);
				}
			} finally {
				if (zis != null)
					zis.close();
			}
		} catch (Exception e) {
			log.error("unzip 发生异常:", e);
		}
		return os.toByteArray();
	}

	private Map<String, String> decodeParamterStrToMap(String paramterStr) {
		String[] params = paramterStr.split("&");
		Map<String, String> paramsMap = new HashMap<String, String>(params.length);
		String key;
		String val;
		for (String param : params) {
			if (param.indexOf("=") > 0) {
				key = param.substring(0, param.indexOf("="));
				val = param.substring(param.indexOf("=") + 1);
				paramsMap.put(key, val);
			}
		}
		return paramsMap;
	}

	@Override
	public void checkParams(String requestData, String sign) throws ParamInvalidException {
		if (!(Md5Encrypt.md5(requestData + requestDataKey, "utf-8").equals(sign))) {
			throw new ParamInvalidException();
		}
	}

	@Override
	public BaseRequestParam convertToRequest(String requestData) throws ParamInvalidException {

		try {
			Map<String, String> allParams = JsonApiUtil.convertForFirstChildren(requestData);
			String method = allParams.get("method");
			String className = REQUEST_DOMAIN + method.substring(0, 1).toUpperCase() + method.substring(1) + "Request";

			Class<?> clazz = Class.forName(className);

			return (BaseRequestParam) BeanUtil.convertToBean(allParams, clazz);

		} catch (Exception e) {
			throw new ParamInvalidException(e);
		}
	}

}
