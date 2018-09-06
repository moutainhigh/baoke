package com.baoke.service;

import com.baoke.api.domain.request.QueryRecommendHomeRequest;
import com.baoke.service.base.BaseServiceTest;

/**
 * 查询首页推荐列表
 * 
 * @author lcl
 * @date 2018年7月19日 下午1:29:32
 *
 */
public class QueryRecommendHomeService extends BaseServiceTest{

	public static void main(String[] args) {
		
		//Long deviceId = 1L;
		String method = "queryRecommendHome";
		
		QueryRecommendHomeRequest request = new QueryRecommendHomeRequest();
		request.setMethod(method);
		request.setCurPageNo(1);

		exec(request);

	}

}
