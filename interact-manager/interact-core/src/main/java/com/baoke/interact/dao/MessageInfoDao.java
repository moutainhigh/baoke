package com.baoke.interact.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.domain.MessageInfo;

/**
 * 消息DAO
 * 
 * @author zjj
 * @date 2018年7月12日 下午5:31:59
 */
public interface MessageInfoDao {

	List<MessageInfo> queryMyMessageInfo(@Param("userId") Long userId, @Param("pageInfo") PageInfo pageInfo);

	MessageInfo queryMessageInfo(MessageInfo messageInfo);

	int addMessageInfo(MessageInfo messageInfo);

	int modifyMessageNum(@Param("id") Long id, @Param("num") int num);

}
