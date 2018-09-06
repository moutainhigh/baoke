package com.baoke.interact.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.domain.BlackKeyWord;

/**
 * 评论敏感词
 *
 * @author lcl
 * @date: 2018年7月25日 上午11:07:32
 */
public interface BlackKeyWordDao {

	int countBlackKeyWord(@Param("blackKeyWord") BlackKeyWord blackKeyWord);

	List<BlackKeyWord> queryBlackKeyWordListByPage(@Param("blackKeyWord") BlackKeyWord blackKeyWord,
			@Param("pageInfo") PageInfo pageInfo);

	List<BlackKeyWord> queryBlackKeyWordListByStatus(@Param("status") int status);

	int addBlackKeyWord(BlackKeyWord blackKeyWord);

	int modifyBlackKeyWordDeleteStatusByIds(@Param("ids") List<Long> ids, @Param("bkUserId") Long bkUserId,
			@Param("status") int status);

}
