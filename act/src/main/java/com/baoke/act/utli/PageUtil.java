package com.baoke.act.utli;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.util.StringUtil;

/**
 * 获取分页数据工具
 * 
 * @author zjm
 * @date: 2018年6月26日 下午5:35:44
 */
public class PageUtil {

	private static final String CURRENT = "current";// 当前页数

	private static final String PAGE_SIZE = "pageSize";// 每页条数

	private static final Logger logger = LoggerFactory.getLogger(PageUtil.class);

	public static PageInfo getPageInfo(HttpServletRequest request) {

		String current = request.getParameter(CURRENT);
		String pageSize = request.getParameter(PAGE_SIZE);

		PageInfo pageInfo = new PageInfo();

		try {
			if (StringUtil.hasLength(current))
				pageInfo.setCurrent(Integer.parseInt(current));

			if (StringUtil.hasLength(pageSize))
				pageInfo.setPageSize(Integer.parseInt(pageSize));

		} catch (Exception e) {
			logger.error(MessageFormat.format("PageUtil error:current={0};pageSize={1}",
					new Object[] { current, pageSize }));
		}

		return pageInfo;
	}

}
