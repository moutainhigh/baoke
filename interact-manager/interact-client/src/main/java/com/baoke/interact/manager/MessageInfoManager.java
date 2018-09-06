package com.baoke.interact.manager;

import java.util.List;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.domain.MessageInfo;

/**
 * 消息manager
 * 
 * @author zjj
 * @date 2018年7月12日 下午5:27:03
 */
public interface MessageInfoManager {

	/**
	 * 分页获取我的消息
	 * 
	 * @author zjj
	 * @date 2018年7月12日 下午5:28:48
	 * @param userId
	 * @param pageInfo
	 * @return
	 */
	List<MessageInfo> queryMyMessageInfo(Long userId, PageInfo pageInfo);

	/**
	 * 查询messageInfo信息
	 * 
	 * @author zjj
	 * @date 2018年7月17日 下午3:34:37
	 * @param messageInfo
	 * @return
	 */
	MessageInfo queryMessageInfo(MessageInfo messageInfo);

	/**
	 * 添加messageInfo
	 * 
	 * @author zjj
	 * @date 2018年7月17日 下午4:05:59
	 * @param messageInfo
	 * @return
	 */
	long addMessageInfo(MessageInfo messageInfo);

	/**
	 * 修改num
	 * 
	 * @author zjj
	 * @date 2018年7月17日 下午4:11:46
	 * @param id
	 * @param num
	 * @return
	 */
	int modifyMessageNum(Long id, int num);

}
