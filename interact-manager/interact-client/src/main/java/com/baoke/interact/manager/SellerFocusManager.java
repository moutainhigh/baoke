package com.baoke.interact.manager;

import java.util.List;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.constant.SellerFocusStatusEnum;
import com.baoke.interact.domain.SellerFocus;

/**
 * 关注manager
 * 
 * @author zdy
 * @date: 2018年6月13日 下午3:06:48
 */
public interface SellerFocusManager {
	/**
	 * 查询关注主播列表-分页
	 * 
	 * @author zdy
	 * @date: 2018年6月26日 下午7:51:20
	 * @param sellerFocus
	 * @param pageInfo
	 * @return
	 */
	List<SellerFocus> querySellerFocusPage(SellerFocus sellerFocus, PageInfo pageInfo);

	/**
	 * 查询关注主播列表
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午3:06:59
	 * @param sellerFocus
	 * @return
	 */
	List<SellerFocus> querySellerFocusList(SellerFocus sellerFocus);

	/**
	 * 昨日新增粉丝数
	 * 
	 * @author ljj
	 * @date: 2018年7月13日 下午3:53:34
	 * @param sellerId
	 * @param status
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int queryLastdayAddFansNum(long sellerId, SellerFocusStatusEnum sellerFocusEnum);

	/**
	 * 查询统计关注数
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午3:07:12
	 * @param sellerFocus
	 * @return
	 */

	int countSellerFocus(SellerFocus sellerFocus);

	/**
	 * 根据主播ID获取所有关注者的ID
	 * 
	 * @author zjj
	 * @date 2018年7月14日 下午8:45:13
	 * @param sellerId
	 * @return
	 */
	List<Long> queryAllFocusID(long sellerId, SellerFocusStatusEnum sellerFocusStatusEnum);

	/**
	 * 当前用户是否对改播主已关注seller_focus
	 * 
	 * @param userId
	 * @param sellerId
	 * @return
	 */
	int querySellerFocusStatus(long userId, long sellerId);

	/**
	 * 保存关注播主（卖家）
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午3:08:41
	 * @param sellerFocus
	 */
	long addSellerFocus(SellerFocus sellerFocus);

	/**
	 * 修改关注播主（卖家）
	 * 
	 * @author zdy
	 * @date: 2018年6月13日 下午3:08:41
	 * @param sellerFocus
	 */
	int modifySellerFocus(SellerFocus sellerFocus);

}
