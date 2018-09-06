package com.baoke.wechat.result;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.wechat.constant.CodeEnum;

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
		super.setMessage(message);
		super.setCode(code);
	}

	public ResponsePageResult(CodeEnum code, String message, Object data, PageInfo pagination) {
		super();
		super.setData(data);
		super.setMessage(message);
		super.setCode(code);
		this.pagination = pagination;
	}

	public ResponsePageResult() {

	}

	public PageInfo getPagination() {
		return pagination;
	}

	public void setPagination(PageInfo pagination) {
		this.pagination = pagination;
	}

}
