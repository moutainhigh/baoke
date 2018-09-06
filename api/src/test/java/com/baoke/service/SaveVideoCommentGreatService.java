package com.baoke.service;

import com.baoke.api.domain.request.SaveVideoCommentGreatRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 保存视频评论点赞
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:09:35
 *
 */
public class SaveVideoCommentGreatService extends BaseServiceTest{

	public static void main(String[] args) {
		
		String method = "saveVideoCommentGreat";

		SaveVideoCommentGreatRequest request = new SaveVideoCommentGreatRequest();
		//？直接写 不知道有没有问题
		request.setUserCode("QUFBQUFBQUFBQWs9");
		request.setMethod(method);
		request.setCommentId(0L);
		request.setIsGreat(1);
		
		exec(request);
		
	}

}
