package com.baoke.common.dto.seller;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.BlackKeyWordDto;

/**
 * 敏感词集合
 *
 * @author lcl
 * @date: 2018年7月25日 下午3:00:51
 */
public class BlackKeyWordListDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private List<BlackKeyWordDto> blackKeyWordDtoList;// 敏感词集合

	private PageInfo pageInfo;// 分页

	public BlackKeyWordListDto() {
		this.pageInfo = new PageInfo();
	}

	public BlackKeyWordListDto(List<BlackKeyWordDto> blackKeyWordDtoList, PageInfo pageInfo) {
		this.blackKeyWordDtoList = blackKeyWordDtoList;
		this.pageInfo = pageInfo;
	}

	public BlackKeyWordListDto(List<BlackKeyWordDto> blackKeyWordDtoList) {
		this.blackKeyWordDtoList = blackKeyWordDtoList;
	}

	public BlackKeyWordListDto(PageInfo pageInfo, List<BlackKeyWordDto> blackKeyWordDtoList) {
		this.pageInfo = pageInfo;
		this.blackKeyWordDtoList = blackKeyWordDtoList;
	}

	public BlackKeyWordListDto(boolean success, String message, List<BlackKeyWordDto> blackKeyWordDtoList,
			PageInfo pageInfo) {
		super(success, message);
		this.blackKeyWordDtoList = blackKeyWordDtoList;
		this.pageInfo = pageInfo;
	}

	public List<BlackKeyWordDto> getBlackKeyWordDtoList() {
		return blackKeyWordDtoList;
	}

	public void setBlackKeyWordDtoList(List<BlackKeyWordDto> blackKeyWordDtoList) {
		this.blackKeyWordDtoList = blackKeyWordDtoList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
