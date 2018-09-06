package com.baoke.act.domain.result;

import com.baoke.act.constant.CodeEnum;
import com.baoke.common.dto.base.PageInfo;

/**
 * code返回码 带分页 结果集
 * 
 * @author zjm
 * @date: 2018年6月25日 下午4:13:51
 */
public class ResponsePageResult extends ResponseResult {

	private static final long serialVersionUID = 1L;

	/** 分页体 */
	private PageInfo pagination;

	public ResponsePageResult(CodeEnum code, String message) {
		super();
		super.setCode(code);
		super.setMessage(message);
	}

	public ResponsePageResult(CodeEnum code, String message, Object data, PageInfo pagination) {
		super();
		super.setData(data);
		super.setMessage(message);
		super.setCode(code);

		if (null != pagination) {
			this.pagination = pagination;
		} else {
			pagination = new PageInfo();
		}

	}

	public ResponsePageResult() {
		this.pagination = new PageInfo();
	}

	public PageInfo getPagination() {
		return pagination;
	}

	public void setPagination(PageInfo pagination) {
		this.pagination = pagination;
	}

}
