package com.baoke.service;

import com.baoke.api.domain.request.QueryVideoCommentRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 查询视频评论
 * 
 * @author lcl
 * @date 2018年7月19日 上午11:36:28
 *
 */
public class QueryVideoCommentService extends BaseServiceTest{

	public static void main(String[] args) {

		String method = "queryVideoComment";
		
		QueryVideoCommentRequest queryVideoCommentRequest = new QueryVideoCommentRequest();
		queryVideoCommentRequest.setVideoId(1L);
		queryVideoCommentRequest.setMethod(method);
		
		exec(queryVideoCommentRequest);
	}

}
