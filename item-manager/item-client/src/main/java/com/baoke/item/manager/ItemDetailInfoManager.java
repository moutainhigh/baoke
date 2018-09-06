package com.baoke.item.manager;

import java.util.List;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.item.domain.ItemDetailInfo;

/**
 * 商品Manager
 * 
 * @author: zdy
 * @date: 2018年6月13日 上午11:33:25
 */
public interface ItemDetailInfoManager {
	/**
	 * 根据ID查询商品详情对象
	 * 
	 * @author: zdy
	 * @date: 2018年6月13日 上午11:33:25
	 * @param id
	 * @return
	 */
	ItemDetailInfo queryItemDetailInfoById(long id);

	/**
	 * 根据商品的id查询商品属性
	 * 
	 * @author: ljj
	 * @date: 2018年7月3日 下午2:21:59
	 */
	List<ItemDetailInfo> queryItemDetailInfoByItemId(long itemId);

	/**
	 * 列表查询商品详情集合
	 * 
	 * @author: zdy
	 * @date: 2018年6月13日 上午11:33:25
	 * @param itemDetailInfo
	 * @return
	 */
	List<ItemDetailInfo> queryItemDetailInfoList(ItemDetailInfo itemDetailInfo);

	/**
	 * 少于10件的商品
	 * 
	 * @author: ljj
	 * @date: 2018年7月12日 下午7:02:25
	 * @param itemDetailInfo
	 * @return
	 */
	List<ItemDetailInfo> queryItemDetailInfoLessTenBySellerId(ItemDetailInfo itemDetailInfo, PageInfo pageInfo);

	/**
	 * 查询少于10件的商品数量
	 * 
	 * @author zjm
	 * @date: 2018年7月16日 下午3:03:32
	 * @param itemDetailInfo
	 * @param pageInfo
	 * @return
	 */
	int countItemDetailInfoLessTenBySellerId(ItemDetailInfo itemDetailInfo);

	/**
	 * 修改商品库存
	 * 
	 * @author ljj
	 * @date: 2018年7月5日 下午5:39:15
	 * @param itemDetailInfo
	 * @return
	 */
	int modifyItemDetailNum(ItemDetailInfo itemDetailInfo);

	/**
	 * 修改商品库存和销售量
	 * 
	 * @author zjj
	 * @date 2018年7月6日 下午3:42:41
	 * @param itemDetailInfo
	 */
	int modifyItemTotalAndSaleNum(ItemDetailInfo itemDetailInfo);

	/**
	 * 新增商品明细
	 * 
	 * @author: wyj
	 * @date: 2018年7月9日 上午10:53:17
	 */
	Long addItemDetailInfo(ItemDetailInfo itemDetailInfo);
}
