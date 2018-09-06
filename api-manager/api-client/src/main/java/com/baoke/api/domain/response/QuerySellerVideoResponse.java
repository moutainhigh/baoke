package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.SellerVideoListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoInfoDto;

/**
 * 查询播主（卖家）首页（视频页）回参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:27:12
 */
public class QuerySellerVideoResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	// 视频集合
	private List<VideoInfoDto> videoList;

	private Integer curPageNo;// 当前页数

	private Integer pageSize;// 每页显示的记录数

	public List<VideoInfoDto> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<VideoInfoDto> videoList) {
		this.videoList = videoList;
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
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		SellerVideoListDto sellerVideoListDto = (SellerVideoListDto) baseResultDto;
		this.videoList = sellerVideoListDto.getVideoList();
		if (sellerVideoListDto.getPageInfo() != null) {
			this.curPageNo = sellerVideoListDto.getPageInfo().getCurrent();
			this.pageSize = sellerVideoListDto.getPageInfo().getPageSize();
		}

		return this;
	}
}
