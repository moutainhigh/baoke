package com.baoke.trade.service;

import com.baoke.common.domain.result.Result;
import com.baoke.common.dto.info.PostageInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.trade.domain.PostageInfo;

/**
 * 物流相关
 * 
 * @author: wyj
 * @date: 2018年7月3日 下午6:27:03
 */
public interface PostageService {

	/**
	 * 发货
	 * 
	 * @author: wyj
	 * @date: 2018年7月4日 上午10:51:31
	 */
	Result addPostageInfo(PostageInfo postageInfo) throws ParamInvalidException;

	/**
	 * 查看物流
	 * 
	 * @author zdy
	 * @date: 2018年7月10日 下午8:17:07
	 * @param postageInfoDto
	 * @return
	 * @throws MainException
	 */
	PostageInfoDto queryOrderPostage(PostageInfoDto postageInfoDto) throws MainException;

	/**
	 * 查询物流信息接口
	 * 
	 * @author: wyj
	 * @date: 2018年7月4日 上午10:48:35
	 */
	PostageInfoDto queryPostageDetailList(String orderNo) throws ParamInvalidException;
}
