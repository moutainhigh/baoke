package com.baoke.item.manager;

import java.util.List;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.item.constant.ItemStatusEnum;
import com.baoke.item.domain.ItemInfo;

/**
 * 商品Manager
 * 
 * @author: zdy
 * @date: 2018年6月13日 上午11:34:07
 */
public interface ItemInfoManager {
	/**
	 * 查询商品信息-分页
	 * 
	 * @author zdy
	 * @date: 2018年6月26日 下午8:31:28
	 * @param itemInfo
	 * @param pageInfo
	 * @return
	 */
	List<ItemInfo> queryItemInfoListByPage(ItemInfo itemInfo, PageInfo pageInfo);

	/**
	 * 根据ID查询商品对象
	 * 
	 * @author: zdy
	 * @date: 2018年6月13日 上午11:33:25
	 * @param id
	 * @return
	 */
	ItemInfo queryItemInfoById(long id);

	/**
	 * 根据ID查询对象，带状态
	 * 
	 * @author zjj
	 * @date 2018年7月26日 下午5:25:50
	 * @param itemId
	 * @param online
	 * @return
	 */
	ItemInfo queryItemInfoByIdWithStatus(long itemId, ItemStatusEnum online);

	/**
	 * 商品统计
	 * 
	 * @author: zdy
	 * @date: 2018年6月13日 上午11:33:25
	 * @param id
	 * @return
	 */
	int countItemInfo(ItemInfo itemInfo);

	/**
	 * 商品列表查询集合
	 * 
	 * @author: zdy
	 * @date: 2018年6月13日 上午11:33:25
	 * @param itemInfo
	 * @return
	 */
	List<ItemInfo> queryItemInfoList(ItemInfo itemInfo);

	/**
	 * 根据状态列表查询商品数量
	 * 
	 * @author zjm
	 * @date: 2018年7月24日 上午11:41:07
	 * @param itemInfo
	 * @param statuses
	 * @return
	 */
	int countItemInfoByStatuses(ItemInfo itemInfo, List<Integer> statuses);

	/**
	 * 根据状态列表分页查询商品信息
	 * 
	 * @author zjm
	 * @date: 2018年7月24日 上午11:50:41
	 * @param itemInfo
	 * @param statues
	 * @param pageInfo
	 * @return
	 */
	List<ItemInfo> queryItemInfoListByPageAndStatuses(ItemInfo itemInfo, List<Integer> statuses, PageInfo pageInfo);

	/**
	 * 新增商品
	 * 
	 * @author: wyj
	 * @date: 2018年7月2日 下午4:45:50
	 */
	long addItemInfo(ItemInfo itemInfo);

	/**
	 * 修改邮费
	 * 
	 * @author: ljj
	 * @date: 2018年7月3日 上午11:33:25
	 * @param itemInfo
	 * @return
	 */
	int modifyItemPostage(ItemInfo itemInfo);

	/**
	 * 修改状态
	 * 
	 * @author zjm
	 * @date: 2018年7月23日 下午4:40:31
	 * @param id
	 * @param itemStatusEnum
	 * @return
	 */
	int modifyItemStatusById(Long id, ItemStatusEnum itemStatusEnum);

	/**
	 * 根据商品id列表修改商品信息
	 * 
	 * @author zjm
	 * @date: 2018年7月6日 下午3:58:49
	 * @param itemInfo
	 * @return
	 */
	int modifyItemByitemIds(List<Long> itemIds, ItemStatusEnum itemStatusEnum, String auditResult);

	/**
	 * 根据商品id修改商品信息
	 * 
	 * @author zjm
	 * @date: 2018年7月23日 下午8:14:42
	 * @param itemId
	 * @param itemStatusEnum
	 * @param auditResult
	 * @return
	 */
	int modifyItemByitemId(Long itemId, ItemStatusEnum itemStatusEnum, String auditResult);

}
