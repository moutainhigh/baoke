package com.baoke.wechat.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baoke.common.dto.info.VideoInfoDto;
import com.baoke.common.dto.wechat.VideoInfoListDto;
import com.baoke.item.service.VideoInfoService;
import com.baoke.wechat.constant.CodeEnum;
import com.baoke.wechat.result.ResponsePageResult;
import com.baoke.wechat.util.PageUtil;

/**
 * 小程序相关控制器
 * 
 * @author: wyj
 * @date: 2018年7月5日 上午10:31:39
 */
@RestController
public class WechatHomeController {

	@Resource
	private VideoInfoService videoInfoService;

	/**
	 * 查询首页
	 * 
	 * @author: wyj
	 * @date: 2018年7月5日 上午10:33:35
	 */
	@RequestMapping(value = "/queryWechatHome", method = RequestMethod.POST)
	public ResponsePageResult querySellerInfo(HttpServletRequest request) {

		VideoInfoDto videoInfoDto = new VideoInfoDto();
		videoInfoDto.setPageInfo(PageUtil.getPageInfo(request));
		VideoInfoListDto videoInfoListDto = videoInfoService.queryWechatHome(videoInfoDto);
		if (videoInfoListDto != null) {
			return new ResponsePageResult(CodeEnum.SUCCESS, "", videoInfoListDto.getVideoList(),
					videoInfoListDto.getPageInfo());
		} else {
			return new ResponsePageResult(CodeEnum.SUCCESS, "没有更多数据");
		}
	}
}
