package com.baoke.common.dto;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.ItemInfoDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.dto.info.VideoInfoDto;

/**
 * 商品详情
 * 
 * @author zdy
 * @date: 2018年6月15日 下午6:48:20
 */
public class ItemDetailDto extends BaseResultDto {
	private static final long serialVersionUID = -5853545873831752239L;

	private ItemInfoDto itemInfo;// 商品信息

	private SellerInfoDto sellerInfo;// 播主信息

	private List<VideoInfoDto> videoList;// 视频集合

	public ItemDetailDto() {
	}

	public ItemDetailDto(boolean success, String message) {
		super(success, message);
	}

	public ItemDetailDto(ItemInfoDto itemInfo, SellerInfoDto sellerInfo, List<VideoInfoDto> videoList) {
		this.itemInfo = itemInfo;
		this.sellerInfo = sellerInfo;
		this.videoList = videoList;
	}

	public ItemDetailDto(boolean success, String message, ItemInfoDto itemInfo, SellerInfoDto sellerInfo,
			List<VideoInfoDto> videoList) {
		super(success, message);
		this.itemInfo = itemInfo;
		this.sellerInfo = sellerInfo;
		this.videoList = videoList;
	}

	public ItemInfoDto getItemInfo() {
		return itemInfo;
	}

	public SellerInfoDto getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(SellerInfoDto sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public void setItemInfo(ItemInfoDto itemInfo) {
		this.itemInfo = itemInfo;
	}

	public List<VideoInfoDto> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<VideoInfoDto> videoList) {
		this.videoList = videoList;
	}

}
