package com.baoke.interact.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.db.DataSource;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.dao.BlackKeyWordDao;
import com.baoke.interact.domain.BlackKeyWord;
import com.baoke.interact.manager.BlackKeyWordManager;

@Component
@DataSource("miscDataSource")
public class BlackKeyWordManagerImpl implements BlackKeyWordManager {

	@Resource
	private BlackKeyWordDao blackKeyWordDao;

	@Override
	public int countBlackKeyWord(BlackKeyWord blackKeyWord) {

		return blackKeyWordDao.countBlackKeyWord(blackKeyWord);
	}

	@Override
	public List<BlackKeyWord> queryBlackKeyWordListByPage(BlackKeyWord blackKeyWord, PageInfo pageInfo) {

		return blackKeyWordDao.queryBlackKeyWordListByPage(blackKeyWord, pageInfo);
	}

	@Override
	public List<BlackKeyWord> queryBlackKeyWordListByStatus(BooleanEnum booleanEnum) {

		return blackKeyWordDao.queryBlackKeyWordListByStatus(booleanEnum.getCode());
	}

	@Override
	public int addBlackKeyWord(BlackKeyWord blackKeyWord) {

		return blackKeyWordDao.addBlackKeyWord(blackKeyWord);
	}

	@Override
	public int modifyBlackKeyWordDeleteStatusByIds(List<Long> ids, Long bkUserId, BooleanEnum booleanEnum) {

		return blackKeyWordDao.modifyBlackKeyWordDeleteStatusByIds(ids, bkUserId, booleanEnum.getCode());
	}

}
