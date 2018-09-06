package com.baoke.common.dto.seller;

import com.baoke.common.dto.base.BaseDto;

/**
 * 商家设置邮费dto
 * 
 * @author ljj
 * @date: 2018年7月3日 下午7:52:59
 */
public class ItemPostageDto extends BaseDto {
	private static final long serialVersionUID = -4572526805336098306L;

	/** 商品id */
	private Long itemId;

	/** 是否包邮 0（包）1（不包） */
	private Integer type;

	/** 邮费 */
	private Integer postage;

	/** 附加邮费 */
	private String appendPostage;

	/** 地区 */
	private String appendPostageArea;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPostage() {
		return postage;
	}

	public void setPostage(Integer postage) {
		this.postage = postage;
	}

	public String getAppendPostage() {
		return appendPostage;
	}

	public void setAppendPostage(String appendPostage) {
		this.appendPostage = appendPostage;
	}

	public String getAppendPostageArea() {
		return appendPostageArea;
	}

	public void setAppendPostageArea(String appendPostageArea) {
		this.appendPostageArea = appendPostageArea;
	}

}
