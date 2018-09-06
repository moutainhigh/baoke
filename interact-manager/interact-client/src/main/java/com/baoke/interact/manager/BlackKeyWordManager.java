package com.baoke.interact.manager;

import java.util.List;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.domain.BlackKeyWord;

/**
 * 敏感词
 *
 * @author lcl
 * @date: 2018年7月25日 上午11:00:13
 */
public interface BlackKeyWordManager {

	/**
	 * 查询总条数
	 * 
	 * @author lcl
	 * @date: 2018年7月25日 下午4:13:48
	 * @param status
	 * @return
	 */
	int countBlackKeyWord(BlackKeyWord blackKeyWord);

	/**
	 * 敏感词列表
	 * 
	 * @author lcl
	 * @date: 2018年7月25日 下午3:09:26
	 * @param blackKeyWordDto
	 * @return
	 */
	List<BlackKeyWord> queryBlackKeyWordListByPage(BlackKeyWord blackKeyWord, PageInfo pageInfo);

	/**
	 * 根据状态查询列表
	 * 
	 * @author lcl
	 * @date: 2018年7月26日 下午4:51:48
	 * @param booleanEnum
	 * @return
	 */
	List<BlackKeyWord> queryBlackKeyWordListByStatus(BooleanEnum booleanEnum);

	/**
	 * 新增敏感词
	 * 
	 * @author lcl
	 * @date: 2018年7月25日 下午7:31:00
	 * @param blackKeyWord
	 */
	int addBlackKeyWord(BlackKeyWord blackKeyWord);

	/**
	 * 批量删除敏感词
	 * 
	 * @author lcl
	 * @date: 2018年7月26日 下午4:10:52
	 * @param ids
	 * @param bkUserId
	 * @param status
	 * @return
	 */
	int modifyBlackKeyWordDeleteStatusByIds(List<Long> ids, Long bkUserId, BooleanEnum booleanEnum);

}
