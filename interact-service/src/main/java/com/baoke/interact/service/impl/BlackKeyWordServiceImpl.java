package com.baoke.interact.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.BlackKeyWordDto;
import com.baoke.common.dto.seller.BlackKeyWordListDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.interact.domain.BlackKeyWord;
import com.baoke.interact.manager.BlackKeyWordManager;
import com.baoke.interact.service.BlackKeyWordService;
import com.baoke.user.manager.UserInfoManager;

@Service("blackKeyWordService")
public class BlackKeyWordServiceImpl implements BlackKeyWordService {

	private static final Logger logger = Logger.getLogger(BlackKeyWordServiceImpl.class);

	@Resource
	private BlackKeyWordManager blackKeyWordManager;

	@Resource
	private UserInfoManager userInfoManager;

	@Override
	public BlackKeyWordListDto queryBlackKeyWordListByPage(BlackKeyWordDto blackKeyWordDto) throws MainException {
		if (null == blackKeyWordDto) {
			throw new ParamInvalidException("参数不能为空");
		}
		BlackKeyWord blackKeyWord = new BlackKeyWord(null, blackKeyWordDto.getName(), BooleanEnum.TRUE);
		PageInfo pageInfo = blackKeyWordDto.getPageInfo();
		pageInfo.setTotal(blackKeyWordManager.countBlackKeyWord(blackKeyWord));

		List<BlackKeyWord> blackKeyWordList = blackKeyWordManager.queryBlackKeyWordListByPage(blackKeyWord, pageInfo);
		List<BlackKeyWordDto> blackKeyWordDtoList = new ArrayList<>();
		if (CollectionUtil.isNotEmpty(blackKeyWordList)) {
			for (BlackKeyWord blackKeyWordTemp : blackKeyWordList) {
				BlackKeyWordDto blackKeyWordDtoTemp = blackKeyWordTemp.convert();
				blackKeyWordDtoTemp.setBkUserName(blackKeyWordDto.getBkUserName());
				blackKeyWordDtoList.add(blackKeyWordDtoTemp);
			}
		}

		return new BlackKeyWordListDto(blackKeyWordDtoList, pageInfo);
	}

	@Override
	public long addBlackKeyWord(BlackKeyWordDto blackKeyWordDto) throws MainException {
		if (null == blackKeyWordDto || null == blackKeyWordDto.getBkUserId()) {
			throw new ParamInvalidException("对象或操作者不能为空！");
		}

		BlackKeyWord blackKeyWord = new BlackKeyWord();
		blackKeyWord.setId(blackKeyWordDto.getBlackKeyWordId());
		blackKeyWord.setBkUserId(blackKeyWordDto.getBkUserId());

		if (StringUtil.isBlank(blackKeyWordDto.getName())) {
			throw new ParamInvalidException("敏感词不能为空！");
		}
		blackKeyWord.setName(blackKeyWordDto.getName());
		blackKeyWord.setStatus(BooleanEnum.TRUE.getCode());
		long id = blackKeyWordManager.addBlackKeyWord(blackKeyWord);

		if (logger.isDebugEnabled()) {
			logger.debug("add black key word success," + blackKeyWord);
		}
		return id;
	}

	@Override
	public int deleteBlackKeyWordByIds(List<Long> ids, Long bkUserId) throws MainException {
		if (CollectionUtil.isEmpty(ids)) {
			throw new ParamInvalidException("ids不能为空！");
		}
		if (null == bkUserId || 0 >= bkUserId) {
			throw new ParamInvalidException("ids不能为空！");
		}

		return blackKeyWordManager.modifyBlackKeyWordDeleteStatusByIds(ids, bkUserId, BooleanEnum.FALSE);

	}

}
