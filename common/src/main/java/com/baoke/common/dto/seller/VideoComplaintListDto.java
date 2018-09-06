package com.baoke.common.dto.seller;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.VideoComplaintInfoDto;

/**
 * 查询举报列表dto
 * 
 * @author: wyj
 * @date: 2018年7月7日 下午2:56:30
 */
public class VideoComplaintListDto extends BaseResultDto {

	private static final long serialVersionUID = -9105805025165565146L;

	private List<VideoComplaintInfoDto> list;

	private PageInfo pagination;

	public VideoComplaintListDto() {
	}

	public VideoComplaintListDto(boolean success, String message) {
		super(success, message);
	}

	public VideoComplaintListDto(boolean success, String message, List<VideoComplaintInfoDto> list, PageInfo pageInfo) {
		super(success, message);
		this.list = list;
		this.pagination = pageInfo;
	}

	public List<VideoComplaintInfoDto> getList() {
		return list;
	}

	public void setList(List<VideoComplaintInfoDto> list) {
		this.list = list;
	}

	public PageInfo getPagination() {
		return pagination;
	}

	public void setPagination(PageInfo pagination) {
		this.pagination = pagination;
	}

}
