package com.baoke.common.dto.api;

import com.baoke.common.dto.base.BaseResultDto;

/**
 * 购物车商品明细信息
 * 
 * @author zdy
 * @date: 2018年6月29日 下午8:00:53
 */
public class ShoppingCartItemDetailDto extends BaseResultDto {

	private static final long serialVersionUID = -6706056216907370198L;

	/** 购物车记录主键ID */
	private Long shoppingCartId;

	/** itemId */
	private Long itemId;

	/** itemDetailId */
	private Long itemDetailId;

	/** 商品标题 */
	private String title;

	/** 小图标地址 */
	private String mainImgUrl;

	/** 属性1 name */
	private String attr1Name;

	/** 属性2 name */
	private String attr2Name;

	/** 剩余库存 */
	private Integer totalNum;

	/** 购买数量 */
	private Integer num;

	/** 销售价格 -元 */
	private String priceYuan;

	/** 销售价格 -分 */
	private Integer priceFen;

	/** 商品状态 */
	private Integer itemStatus;

	public Long getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(Long shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getItemDetailId() {
		return itemDetailId;
	}

	public void setItemDetailId(Long itemDetailId) {
		this.itemDetailId = itemDetailId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMainImgUrl() {
		return mainImgUrl;
	}

	public void setMainImgUrl(String mainImgUrl) {
		this.mainImgUrl = mainImgUrl;
	}

	public String getAttr1Name() {
		return attr1Name;
	}

	public void setAttr1Name(String attr1Name) {
		this.attr1Name = attr1Name;
	}

	public String getAttr2Name() {
		return attr2Name;
	}

	public void setAttr2Name(String attr2Name) {
		this.attr2Name = attr2Name;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getPriceYuan() {
		return priceYuan;
	}

	public void setPriceYuan(String priceYuan) {
		this.priceYuan = priceYuan;
	}

	public Integer getPriceFen() {
		return priceFen;
	}

	public void setPriceFen(Integer priceFen) {
		this.priceFen = priceFen;
	}

	public Integer getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(Integer itemStatus) {
		this.itemStatus = itemStatus;
	}

}
