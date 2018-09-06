package com.baoke.interact.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baoke.common.constant.MessageTypeEnum;
import com.baoke.common.dto.MessageListDto;
import com.baoke.common.dto.api.MessageDetailDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.MessageInfoDto;
import com.baoke.common.util.api.json.JsonApiUtil;

/**
 * 站内信
 *
 * @author zjj
 * @date 2018年6月21日 下午8:35:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:*.xml")
public class MessageServiceTest {

	@Resource
	MessageService messageService;

	/**
	 * 我的信息列表(success)
	 * 
	 * @author zjj
	 * @date 2018年6月21日 下午8:59:56
	 */
	@Test
	public void testQueryMyMessage() {
		try {
			MessageListDto messageListDto = new MessageListDto();
			messageListDto.setUserId(2L);
			messageListDto.setPageInfo(new PageInfo(1, 20));
			MessageListDto queryMyMessage = messageService.queryMyMessage(messageListDto);
			System.out.println(JsonApiUtil.convertToJson(queryMyMessage));

			assertNotNull("查询数据不为空", queryMyMessage);
			assertNotNull("查询数据不为空", queryMyMessage.isSuccess());
			assertNotNull("查询数据不为空", queryMyMessage);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 分类查询(success)
	 * 
	 * @author zjj
	 * @date 2018年6月21日 下午8:59:56
	 */
	@Test
	public void testQueryMyMessageByType() {
		try {
			MessageInfoDto messageInfoDto = new MessageInfoDto();
			messageInfoDto.setUserId(2L);
			messageInfoDto.setMessageType(MessageTypeEnum.COMMENT_INTERACT.getCode());
			// messageInfoDto.setVideoId(0L);
			messageInfoDto.setCommentId(7L);
			messageInfoDto.setPageInfo(new PageInfo(1, 20));
			MessageDetailDto messageDetailDto = messageService.queryMyMessageByType(messageInfoDto);

			System.out.println(JsonApiUtil.convertToJson(messageDetailDto));

			assertNotNull("查询数据不为空", messageDetailDto);
			assertNotNull("查询数据不为空", messageDetailDto.isSuccess());
			assertNotNull("查询数据不为空", messageDetailDto);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
