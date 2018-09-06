package com.baoke.admin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 * @author linlvping
 *
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
	
	/**
	 * 如果字符串为NULL, 则返回""
	 * 如果字符串不为空, 则返回去空格后的结果
	 * 
	 * @param str
	 * @return
	 */
	public static String noNUll(String str) {
		if (isEmpty(str)) {
			return "";
		} else {
			return str.trim();
		}
	}
	
    /**
     * 去掉字符串左右两边的空格
     * 
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }
	
	/**
	 * 如果字符串是null或空字符串""，则返回指定默认字符串，否则返回字符串本身。
	 * 当字符串为empty，将字符串转换成指定的默认字符串。
	 * 注：判断字符串为null时，可用更通用的ObjectUtil.defaultIfNull。
	 * 
	 * @param str 要转换的字符串
	 * @param defaultStr 默认字符串
	 * @return 字符串本身或指定的默认字符串
	 */
	public static String defaultIfEmpty(String str, String defaultStr) {
		return str == null || str.length() == 0 ? defaultStr : str;
	}

	/**
	 * 如果字符串是null或空字符串""，则返回指定默认字符串，否则返回字符串本身。
	 * 当字符串blank时，将字符串转换成指定的默认字符串。
	 * 注：判断字符串为null时，可用更通用的ObjectUtil.defaultIfNull。
	 * 
	 * @param str 要转换的字符串
	 * @param defaultStr 默认字符串
	 * @return 字符串本身或指定的默认字符串
	 */
	public static String defaultIfBlank(String str, String defaultStr) {
		return isBlank(str) ? defaultStr : str;
	}
	
	/**
	 * 通过正则表达式 pattern 在输入的字符串str中 搜索第一个符合正则格式的字符串
	 * 如果pattern为null或者str为null或者没有匹配 返回null
	 * 
	 * @param pattern
	 * @param str
	 * @return
	 */
	public static String getRegexFromStr(Pattern pattern, String str) {
		return getRegexFromStr(pattern, str, -1);
	}
	
	/**
	 * 如果group大于等于0 则返回匹配的组的数据 
	 * 
	 * @param pattern
	 * @param str
	 * @param group
	 * @return
	 */
	public static String getRegexFromStr(Pattern pattern, String str, int group) {
		if(pattern == null || str == null) {
			return null;
		}
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()) {
			return group < 0 ? matcher.group() : matcher.group(group);
		}
		return null;
	}
	
    /**
     * 字符串的细节隐藏处理
     * 
     * StringUtils.hideStr("hello world") = "h**d"
     * StringUtils.hideStr("h") = "h**h"
     * StringUtils.hideStr(" ") = ""
     * StringUtils.hideStr("") = ""
     * 
     * @param str
     * @return
     */
	public static String hideStr(String str){
		if(isEmpty(str)) {
			return "";
		}
		StringBuffer message = new StringBuffer();
		message.append(str.substring(0, 1));
		message.append("**");
		message.append(str.substring((str.length()-1)));
		return message.toString();
	}
	
	/**
	 * 淘粉吧账号的细节隐藏处理
	 * 
	 * StringUtils.hideNickDetail("tracynss@taobao") = "tr**ss"
	 * StringUtils.hideNickDetail("tracynss@tf8") = "tr**ss"
	 * StringUtils.hideNickDetail("tra") = "tra"
	 * StringUtils.hideNickDetail(" ") = ""
	 * StringUtils.hideNickDetail("") = ""
	 * 
	 * @param str
	 * @return
	 */
	public static String hideNickDetail(String str){
	  if(isBlank(str)){
	      return "";
	  }
	  StringBuilder sb = new StringBuilder();
	  if(str.indexOf("@")>-1){
	      if(str.substring(str.indexOf("@")).length()>=4){
	          sb.append(str.substring(0,2));
	          sb.append("**");
	          sb.append(str.substring(str.indexOf("@")-2, str.indexOf("@")));
	      }else{
	          return str;
	      }
	  }else{
	      if(str.length()>=4){
              sb.append(str.substring(0,2));
              sb.append("**");
              sb.append(str.substring(str.length()-2));
          }else{
              return str;
          }
	  }
	  return sb.toString();
	}
	
	/**
	 * 过滤空格、回车、换行、制表符
	 * 
	 * @param str
	 * @return
	 */
	public static String filterBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

	/**
	 * 过滤出字符串中的字母、数字和中文
	 * 
	 * @param str
	 * @return
	 */
	public static String filterStr(String str){
		if(str==null || str.trim().length()==0){
			return str;
		}
		return str.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
	}
	
	/**
	 * 截取字符串
	 * 长度超过7位的部分用...表示
	 * 
	 * @param str
	 * @return
	 */
	public static String subStr(String str){
		if(str==null || str.trim().length()==0){
			return str;
		}
		
		if(str.length()>7){
			return str.substring(0,7)+"...";
		}else {
			return str;
		}
	}

//	/** 获取淘粉吧刷新命令 */
//	public static boolean isRefreshFlag() {
//		return isRefreshFlag(null);
//	}
//	
//	public static final boolean isRefreshFlag(HttpServletRequest request) {
//		if(CacheContext.refreshFlag.get() != null && CacheContext.refreshFlag.get()) {
//			return true;
//		}
//		return request != null && "refresh".equals(request.getParameter("cmd"));
//	}
	
}
