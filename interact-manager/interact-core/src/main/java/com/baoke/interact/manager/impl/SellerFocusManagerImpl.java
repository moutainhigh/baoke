package com.baoke.interact.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.constant.SellerFocusStatusEnum;
import com.baoke.interact.dao.SellerFocusDao;
import com.baoke.interact.domain.SellerFocus;
import com.baoke.interact.manager.SellerFocusManager;

/**
 * 播主关注ManagerImpl
 * 
 * @author zdy
 * @date: 2018年6月13日 下午3:27:48
 */
@Component
@DataSource("interactDataSource")
public class SellerFocusManagerImpl implements SellerFocusManager {

	@Resource
	private SellerFocusDao sellerFocusDao;

	@Override
	public List<SellerFocus> querySellerFocusPage(SellerFocus sellerFocus, PageInfo pageInfo) {
		return sellerFocusDao.querySellerFocusPage(sellerFocus, pageInfo);
	}

	@Override
	public int countSellerFocus(SellerFocus sellerFocus) {
		return sellerFocusDao.countSellerFocus(sellerFocus);
	}

	@Override
	public List<SellerFocus> querySellerFocusList(SellerFocus sellerFocus) {
		return sellerFocusDao.querySellerFocusList(sellerFocus);
	}

	@Override
	public List<Long> queryAllFocusID(long sellerId, SellerFocusStatusEnum sellerFocusStatusEnum) {
		return sellerFocusDao.queryAllFocusID(sellerId, sellerFocusStatusEnum.getCode());
	}

	@Override
	// 当前用户是否对改播主已关注seller_focus
	public int querySellerFocusStatus(long userId, long sellerId) {
		int status = SellerFocusStatusEnum.UNFOCUS.getCode();
		SellerFocus sellerFocus = new SellerFocus();
		sellerFocus.setUserId(userId);
		sellerFocus.setSellerId(sellerId);
		sellerFocus.setStatus(SellerFocusStatusEnum.FOCUS.getCode());
		List<SellerFocus> focusList = sellerFocusDao.querySellerFocusList(sellerFocus);
		if (focusList != null && focusList.size() > 0) {
			status = SellerFocusStatusEnum.FOCUS.getCode();
		}
		return status;
	}

	@Override
	public int queryLastdayAddFansNum(long sellerId, SellerFocusStatusEnum sellerFocusEnum) {
		return sellerFocusDao.queryLastdayAddFansNum(sellerId, sellerFocusEnum.getCode());
	}

	@Override
	public long addSellerFocus(SellerFocus sellerFocus) {
		sellerFocusDao.addSellerFocus(sellerFocus);
		return sellerFocus.getId();
	}

	@Override
	public int modifySellerFocus(SellerFocus sellerFocus) {
		return sellerFocusDao.modifySellerFocus(sellerFocus);
	}

}
