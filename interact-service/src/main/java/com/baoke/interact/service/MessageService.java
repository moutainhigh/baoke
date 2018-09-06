package com.baoke.interact.service;

import com.baoke.common.dto.MessageListDto;
import com.baoke.common.dto.api.MessageDetailDto;
import com.baoke.common.dto.info.MessageInfoDto;
import com.baoke.common.exception.base.MainException;

/**
 * 站内信service
 * 
 */
public interface MessageService {

	/**
	 * 我的信息列表
	 * 
	 * @author zjj
	 * @date 2018年6月15日 下午1:35:28
	 * @return
	 * @throws MainException
	 */
	MessageListDto queryMyMessage(MessageListDto messageListDto) throws MainException;

	/**
	 * 查询我的单个类型的消息
	 * 
	 * @author zjj
	 * @date 2018年7月12日 下午2:02:12
	 * @param messageInfoDto
	 * @return
	 * @throws MainException
	 */
	MessageDetailDto queryMyMessageByType(MessageInfoDto messageInfoDto) throws MainException;

}
