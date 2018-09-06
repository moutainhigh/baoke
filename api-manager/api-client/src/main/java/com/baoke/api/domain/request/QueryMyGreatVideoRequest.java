package com.baoke.api.domain.request;

import com.baoke.api.domain.request.base.BaseRequestParam;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 查询我喜欢的视频入参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:28:49
 */
public class QueryMyGreatVideoRequest extends BaseRequestParam {

	private static final long serialVersionUID = -6110536313628955255L;

	private Integer curPageNo = 1;// 当前页数

	private Integer pageSize = 12;// 每页显示的记录数

	public QueryMyGreatVideoRequest() {
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
