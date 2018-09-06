package com.baoke.interact.manager;

import java.util.List;

import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.constant.IsReadEnum;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.domain.MessageNotify;

/**
 * 消息通知manager
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午4:44:16
 */
public interface MessageNotifyManager {

	/**
	 * 查询消息
	 * 
	 * @author zjj
	 * @date 2018年7月12日 下午7:08:46
	 * @param messageNotify
	 * @return
	 */
	List<MessageNotify> queryMessageNotifyList(MessageNotify messageNotify, PageInfo pageInfo, BooleanEnum booleanEnum);

	/**
	 * 查询当前类型最新一条消息
	 * 
	 * @author zjj
	 * @date 2018年7月18日 下午8:54:36
	 * @param messageNotify
	 * @param booleanEnum
	 * @return
	 */
	MessageNotify queryLastMessageNotify(MessageNotify messageNotify, BooleanEnum booleanEnum);

	/**
	 * 查询未读信息条数
	 * 
	 * @author zjj
	 * @date 2018年7月12日 下午7:08:23
	 * @param messageNotifyTemp
	 * @return
	 */
	int countUnReadMessageNotify(MessageNotify messageNotify, BooleanEnum booleanEnum);

	/**
	 * 根据userId和已读标示查询用户站内信数量
	 * 
	 * @author zjm
	 * @date: 2018年6月20日 下午5:54:23
	 * @param userId
	 * @param isRead
	 * @return
	 */
	int countMessageNotifyByUserIdAndIsRead(long userId, int isRead);

	/**
	 * 通过消息id查询消息实体
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午5:52:21
	 */
	MessageNotify queryMessageNotifyById(long messageId);

	/**
	 * 新增站内信
	 * 
	 * @author: wyj
	 * @date: 2018年6月13日 下午5:44:11
	 */
	long addMessageNotify(MessageNotify messageNotify);

	/**
	 * 修改消息已读状态
	 * 
	 * @author zjj
	 * @date 2018年7月12日 上午11:14:13
	 * @param idList
	 * @param statusEnum
	 * @return
	 */
	int modifyMessageReadStatus(List<Long> idList, IsReadEnum ReadStatus);

}
