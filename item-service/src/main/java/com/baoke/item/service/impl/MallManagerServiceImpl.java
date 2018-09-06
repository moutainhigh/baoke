package com.baoke.item.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baoke.act.domain.MallItemRecommend;
import com.baoke.act.domain.constant.MallItemRecommandStatusEnum;
import com.baoke.act.manager.MallItemRecommendManager;
import com.baoke.common.constant.MallItemRecommandTittleTypeEnum;
import com.baoke.common.dto.info.MallRecommendInfoDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.util.StringUtil;
import com.baoke.item.service.MallManagerService;

@Service("mallManagerService")
public class MallManagerServiceImpl implements MallManagerService {

	private static final Logger logger = Logger.getLogger(MallManagerServiceImpl.class);

	@Resource
	private MallItemRecommendManager mallItemRecommendManager;

	@Override
	public void saveMallRecommendInfo(MallRecommendInfoDto mallRecommendInfoDto) throws ParamInvalidException {
		if (mallRecommendInfoDto.getMallItemRecommendId() == null) {
			if (StringUtil.isBlank(mallRecommendInfoDto.getImgUrl()) || mallRecommendInfoDto.getSort() == null) {
				throw new ParamInvalidException("新增时必要字段不能为空:parenId,imgUrl,sort");
			}

			Long parentId = mallRecommendInfoDto.getParentId();
			if (parentId == null) {
				MallItemRecommend parentMallItemRecommend = new MallItemRecommend(mallRecommendInfoDto.getTitle());
				// 需要新增父推荐标题
				parentMallItemRecommend.setStatus(MallItemRecommandStatusEnum.ENABLE.getCode());
				parentMallItemRecommend.setParentId(MallItemRecommandTittleTypeEnum.PARENT.getCode());
				parentId = mallItemRecommendManager.addMallItemRecommend(parentMallItemRecommend);
			}
			MallItemRecommend subMallItemRecommend = new MallItemRecommend(mallRecommendInfoDto);
			subMallItemRecommend.setTitle(null);// 子推荐无标题
			subMallItemRecommend.setParentId(parentId);
			subMallItemRecommend.setStatus(MallItemRecommandStatusEnum.ENABLE.getCode());
			mallItemRecommendManager.addMallItemRecommend(subMallItemRecommend);
		} else {
			Long parentId = mallRecommendInfoDto.getParentId();
			if (parentId != null || StringUtil.isBlank(mallRecommendInfoDto.getTitle())) {
				MallItemRecommend parentMallItemRecommend = new MallItemRecommend(mallRecommendInfoDto.getTitle());
				parentMallItemRecommend.setId(parentId);
				// 需要修改父推荐标题
				mallItemRecommendManager.modifyMallItemRecommend(parentMallItemRecommend);
			}
			MallItemRecommend subMallItemRecommend = new MallItemRecommend(mallRecommendInfoDto);
			subMallItemRecommend.setTitle(null);// 子推荐无标题
			subMallItemRecommend.setId(mallRecommendInfoDto.getMallItemRecommendId());
			mallItemRecommendManager.modifyMallItemRecommend(subMallItemRecommend);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("save mall recomment info success, " + mallRecommendInfoDto);
		}
	}

}
