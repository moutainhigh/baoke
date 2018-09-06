package com.baoke.user.service;

import com.baoke.common.dto.ChannelShareDto;
import com.baoke.common.dto.api.SellerShareDto;
import com.baoke.common.dto.base.BaseDto;
import com.baoke.common.dto.info.SellerInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 分享service
 * 
 * @author zdy
 * @date: 2018年7月9日 下午9:16:53
 */
public interface ShareService {
	/**
	 * 查询分享渠道
	 * 
	 * @author zdy
	 * @date: 2018年7月10日 上午10:42:09
	 * @param baseDto
	 * @return
	 */
	ChannelShareDto queryShareType(BaseDto baseDto);

	/**
	 * 查询播主首页分享数据
	 * 
	 * @author zdy
	 * @date: 2018年7月26日 下午8:54:13
	 * @param sellerInfoDto
	 * @return
	 * @throws MainException
	 */
	SellerShareDto querySellerShare(SellerInfoDto sellerInfoDto) throws MainException;
}
