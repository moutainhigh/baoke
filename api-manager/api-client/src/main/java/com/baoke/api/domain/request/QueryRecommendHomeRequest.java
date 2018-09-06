package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询首页推荐列表请求参数
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:17:22
 */
public class QueryRecommendHomeRequest extends BaseRequestParam {

	private static final long serialVersionUID = 1L;

	private Integer curPageNo = 1;// 当前页数

	private Integer pageSize = 12;// 每页显示的记录数

	public QueryRecommendHomeRequest() {
		super();
	}

	public Integer getCurPageNo() {
		return curPageNo;
	}

	public void setCurPageNo(Integer curPageNo) {
		this.curPageNo = curPageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public BaseDto convert() throws MainException {
		VideoInfoDto videoInfoDto = new VideoInfoDto(this.curPageNo, this.pageSize);
		return videoInfoDto;
	}

}
