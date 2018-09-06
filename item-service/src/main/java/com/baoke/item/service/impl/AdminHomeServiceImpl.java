package com.baoke.item.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baoke.common.dto.seller.AdminHomeDto;
import com.baoke.interact.constant.VideoComplaintStatusEnum;
import com.baoke.interact.manager.VideoComplaintManager;
import com.baoke.item.constant.ItemStatusEnum;
import com.baoke.item.constant.VideoStatusEnum;
import com.baoke.item.domain.ItemInfo;
import com.baoke.item.domain.VideoInfo;
import com.baoke.item.manager.ItemInfoManager;
import com.baoke.item.manager.VideoInfoManager;
import com.baoke.item.service.AdminHomeService;
import com.baoke.user.constant.SellerStatusEnum;
import com.baoke.user.manager.SellerInfoManager;

@Service("adminHomeServiceImpl")
public class AdminHomeServiceImpl implements AdminHomeService {

	@Resource
	private ItemInfoManager itemInfoManager;

	@Resource
	private SellerInfoManager sellerInfoManager;

	@Resource
	private VideoInfoManager videoInfoManager;
	@Resource
	private VideoComplaintManager videoComplaintManager;

	@Override
	public AdminHomeDto queryAdminHome() {
		int auditingItemNUm = itemInfoManager.countItemInfo(new ItemInfo(ItemStatusEnum.WAIT_AUDIT));
		int auditIngSellerNum = sellerInfoManager.queryAuditIngSellerNum(SellerStatusEnum.SELLER_CENTER);
		int auditIngVideoNUm = videoInfoManager.countVideoInfo(new VideoInfo(VideoStatusEnum.WAIT_AUDIT));
		int auditingComplaintNum = videoComplaintManager.queryAuditingComplaintNum(VideoComplaintStatusEnum.PROCESS);

		AdminHomeDto adminHomeDto = new AdminHomeDto();
		adminHomeDto.setAuditIngSellerNum(auditIngSellerNum);
		adminHomeDto.setAuditIngVideoNum(auditIngVideoNUm);
		adminHomeDto.setAuditingItemNum(auditingItemNUm);
		adminHomeDto.setAuditingComplaintNum(auditingComplaintNum);
		adminHomeDto.setAllProcessIngNum(auditingItemNUm + auditIngSellerNum + auditIngVideoNUm + auditingComplaintNum);
		return adminHomeDto;
	}

}
