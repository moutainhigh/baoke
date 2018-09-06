package com.baoke.service;

import com.baoke.api.domain.request.QueryReplyVideoCommentRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 查询评论回复
 * 
 * @author lcl
 * @date 2018年7月19日 上午11:44:08
 *
 */
public class QueryReplyVideoCommentService extends BaseServiceTest{

	public static void main(String[] args) {
		
		Long deviceId = 1L;
		String method = "queryReplyVideoComment";

		QueryReplyVideoCommentRequest queryReplyVideoCommentRequest = new QueryReplyVideoCommentRequest();
		queryReplyVideoCommentRequest.setDeviceCode(getDeviceCodeByDeviceId(deviceId));
		queryReplyVideoCommentRequest.setCommentId(1L);
		queryReplyVideoCommentRequest.setVideoId(1L);
		queryReplyVideoCommentRequest.setMethod(method);

		exec(queryReplyVideoCommentRequest);
	}

}
