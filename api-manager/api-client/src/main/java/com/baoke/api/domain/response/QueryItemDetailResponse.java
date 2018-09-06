package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.ItemDetailDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.ItemInfoDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.dto.info.VideoInfoDto;

/**
 * 查询视频详情回参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:30:08
 */
public class QueryItemDetailResponse extends BaseResponseParam {
	private static final long serialVersionUID = -5853545873831752239L;

	private ItemInfoDto itemInfo;

	private SellerInfoDto sellerInfo;

	private List<VideoInfoDto> videoList;

	public ItemInfoDto getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(ItemInfoDto queryItemDetail) {
		this.itemInfo = queryItemDetail;
	}

	public List<VideoInfoDto> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<VideoInfoDto> videoList) {
		this.videoList = videoList;
	}

	public SellerInfoDto getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(SellerInfoDto sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (baseResultDto == null) {
			return this;
		}
		ItemDetailDto itemDetailDto = (ItemDetailDto) baseResultDto;
		this.itemInfo = itemDetailDto.getItemInfo();
		this.sellerInfo = itemDetailDto.getSellerInfo();
		this.videoList = itemDetailDto.getVideoList();
		return this;
	}
}
