package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.SellerItemListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.ItemInfoDto;

/**
 * 查询播主（卖家）商品页回参
 * 
 * @author zdy
 * @date: 2018年6月13日 下午5:32:20
 */
public class QuerySellerItemResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;
	/** 商品集合 */
	private List<ItemInfoDto> itemList;

	private Integer curPageNo;// 当前页数

	private Integer pageSize;// 每页显示的记录数

	public List<ItemInfoDto> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemInfoDto> itemList) {
		this.itemList = itemList;
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

		SellerItemListDto sellerItemListDto = (SellerItemListDto) baseResultDto;
		this.itemList = sellerItemListDto.getItemList();
		if (sellerItemListDto.getPageInfo() != null) {
			this.curPageNo = sellerItemListDto.getPageInfo().getCurrent();
			this.pageSize = sellerItemListDto.getPageInfo().getPageSize();
		}
		return this;
	}

}
