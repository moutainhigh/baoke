package com.baoke.service;

import java.util.Date;

import com.baoke.api.domain.request.QueryMyMessageByTypeRequest;
import com.baoke.common.constant.MessageTypeEnum;
import com.baoke.service.base.BaseServiceTest;

/**
 * 分类查询消息
 * 
 * @author lcl
 * @date 2018年7月19日 上午11:22:19
 */
public class QueryMyMessageByTypeService extends BaseServiceTest {

	public static void main(String[] args) {

		Long userId = 2L;
		Long deviceId = 1L;
		String method = "queryMyMessageByType";

		QueryMyMessageByTypeRequest request = new QueryMyMessageByTypeRequest();
		request.setUserCode(getUserCodeByUserId(userId));
		request.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		request.setTimeStamp(new Date().getTime());
		request.setMethod(method);
		request.setMessageType(MessageTypeEnum.COMMENT_INTERACT.getCode());
		request.setCommentId(7L);

		exec(request);
	}

}
