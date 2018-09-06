package com.baoke.push.controller;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.baoke.common.constant.MessageTypeEnum;
import com.baoke.common.domain.message.SiteMessage;
import com.baoke.common.util.HttpClientHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:*xml" })
public class SiteMessageTest {

	private static final Logger logger = Logger.getLogger(SiteMessageTest.class);

	String url = "http://127.0.0.1/send/site";

	@Test
	public void test() {
		SiteMessage siteMessage = new SiteMessage();
		siteMessage.setMessageTypeEnum(MessageTypeEnum.COMMENT_INTERACT);
		siteMessage.setFromUserId(15L);
		siteMessage.setToUserId(2L);
		siteMessage.setVideoId(12L);
		siteMessage.setCommentId(30L);
		siteMessage.setParentCommentId(7L);
		siteMessage.setSecondParentCommentId(7L);
		siteMessage.setTitle("test");
		siteMessage.setContent("评论回复评论回复评论回复评论回复评论回复评论回复评论回复评论回复评论回复评论回复评论回复评论回复评论回复");

		String result = HttpClientHelper.postDataByRequestBody(url, JSON.toJSONString(siteMessage), true, "utf-8");

		logger.debug(result);
	}

}
