package com.baoke.service;

import com.baoke.api.domain.request.QueryVideoCommentDetailRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 查询评论详情
 * 
 * @author lcl
 * @date 2018年7月19日 下午2:38:57
 *
 */
public class QueryVideoCommentDetailService extends BaseServiceTest{

	public static void main(String[] args) {
		String method = "queryVideoCommentDetail";
		
		QueryVideoCommentDetailRequest queryVideoCommentDetailRequest = new QueryVideoCommentDetailRequest();
		queryVideoCommentDetailRequest.setVideoCommentId(1L);
		queryVideoCommentDetailRequest.setMethod(method);

		exec(queryVideoCommentDetailRequest);
	}

}
