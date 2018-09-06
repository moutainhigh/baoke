package com.baoke.interact.service;

import java.util.List;

import com.baoke.common.dto.info.BlackKeyWordDto;
import com.baoke.common.dto.seller.BlackKeyWordListDto;
import com.baoke.common.exception.base.MainException;

/**
 * 敏感词管理
 *
 * @author lcl
 * @date: 2018年7月25日 下午2:53:00
 */
public interface BlackKeyWordService {

	/**
	 * 敏感词列表
	 * 
	 * @author lcl
	 * @date: 2018年7月25日 下午3:05:16
	 * @param messageListDto
	 * @return
	 * @throws MainException
	 */
	BlackKeyWordListDto queryBlackKeyWordListByPage(BlackKeyWordDto blackKeyWordDto) throws MainException;

	/**
	 * 
	 * @author lcl
	 * @date: 2018年7月26日 下午4:33:15
	 * @param blackKeyWordDto
	 */
	long addBlackKeyWord(BlackKeyWordDto blackKeyWordDto) throws MainException;

	/**
	 * 
	 * @author lcl
	 * @date: 2018年7月26日 下午4:33:30
	 * @param ids
	 * @param attribute
	 */
	int deleteBlackKeyWordByIds(List<Long> ids, Long attribute) throws MainException;

}
