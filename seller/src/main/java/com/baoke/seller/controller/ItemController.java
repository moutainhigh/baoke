package com.baoke.seller.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baoke.common.constant.ResponseResultCodeEnum;
import com.baoke.common.constant.UploadFileEnum;
import com.baoke.common.domain.result.ResponsePageResult;
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.ItemDto;
import com.baoke.common.dto.ItemListDto;
import com.baoke.common.dto.info.ItemDetailInfoDto;
import com.baoke.common.dto.info.ItemInfoDto;
import com.baoke.common.dto.seller.ItemPostageDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.util.UploadFileUtil;
import com.baoke.item.constant.ItemStatusEnum;
import com.baoke.item.service.ItemInfoManagerService;
import com.baoke.item.service.ItemInfoService;
import com.baoke.seller.util.PageUtil;

/**
 * 商品相关控制器
 * 
 * @author zjm
 * @date: 2018年6月22日 上午11:25:39
 */
@RestController
@RequestMapping("/item")
public class ItemController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	@Resource
	private ItemInfoService itemInfoService;

	@Resource
	private ItemInfoManagerService itemInfoManagerService;

	/**
	 * 查询用户商品列表
	 * 
	 * @author ljj
	 * @date: 2018年7月3日 下午 4:18:44
	 * @return
	 */
	@RequestMapping(value = "/queryMyItemList", method = RequestMethod.POST)
	public ResponsePageResult queryMyItemList(ItemInfoDto itemInfoDto, HttpServletRequest request,
			HttpSession session) {

		itemInfoDto.setUserId((Long) session.getAttribute("userId"));
		try {
			List<Integer> statusList = new ArrayList<>();
			// 审核通过包含已上线，已下线，封禁中三种状态
			if (null != itemInfoDto.getStatus()) {
				if (itemInfoDto.getStatus() == ItemStatusEnum.ONLINE.getCode()) {
					statusList.add(ItemStatusEnum.ONLINE.getCode());
					statusList.add(ItemStatusEnum.DOWNLINE.getCode());
					statusList.add(ItemStatusEnum.FORCE_DOWNLINE.getCode());
				} else {
					statusList.add(itemInfoDto.getStatus());
				}
			}

			ItemListDto itemListDto = itemInfoService
					.queryItemInfoListByPage(new ItemDto(itemInfoDto, PageUtil.getPageInfo(request), statusList));
			return new ResponsePageResult(ResponseResultCodeEnum.SUCCESS, "", itemListDto.getItemList(), itemListDto.getPageInfo());
		} catch (ParamInvalidException e) {
			logger.error("query item list, query by page error. " + itemInfoDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("query item list, query by page error. " + itemInfoDto, e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 首页的库存预警少于10件的商品
	 *
	 * @author ljj
	 * @date: 2018年7月12日 下午 8:18:44
	 * @return
	 */
	@RequestMapping(value = "/queryItemInfoAlarmList", method = RequestMethod.POST)
	public ResponsePageResult queryItemInfoAlarmList(HttpServletRequest request) {

		try {
			ItemListDto itemInfoListResDto = itemInfoManagerService.queryItemDetailInfoLessTenBySellerId(
					(long) request.getSession().getAttribute("userId"), PageUtil.getPageInfo(request));
			return new ResponsePageResult(ResponseResultCodeEnum.SUCCESS, "", itemInfoListResDto.getItemList(),
					itemInfoListResDto.getPageInfo());
		} catch (ParamInvalidException e) {
			logger.error("query itemInfolessTen error. userId=" + request.getSession().getAttribute("userId"), e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("query itemInfolessTen error. userId=" + request.getSession().getAttribute("userId"), e);
			return new ResponsePageResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 查询单条商品
	 * 
	 * @author ljj
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/queryItemInfo", method = RequestMethod.POST)
	public ResponseResult queryItemInfo(ItemInfoDto itemInfoDto) {

		try {
			ItemInfoDto itemInfoDtoResult = itemInfoService.queryItemInfoByItemId(itemInfoDto.getItemId());
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", itemInfoDtoResult);
		} catch (Exception e) {
			logger.error("query item info error." + itemInfoDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 修改邮费
	 * 
	 * @author ljj
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/saveItemPostage", method = RequestMethod.POST)
	public ResponseResult saveItemPostage(ItemPostageDto itemInfoPostDto) {

		try {
			itemInfoManagerService.modifyItemPostage(itemInfoPostDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
		} catch (ParamInvalidException e) {
			logger.error("save item postage error." + itemInfoPostDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("save item postage error." + itemInfoPostDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 修改库存
	 * 
	 * @author ljj
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/saveItemTotalNum", method = RequestMethod.POST)
	public ResponseResult saveItemTotalNum(ItemDetailInfoDto itemDetailInfoDto) {

		try {
			itemInfoManagerService.modifyItemDetailNum(itemDetailInfoDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
		} catch (ParamInvalidException e) {
			logger.error("save item totalNum error." + itemDetailInfoDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("save item totalNum error." + itemDetailInfoDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 上传商品图片
	 * 
	 * @author zjm
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/uploadItemImg", method = RequestMethod.POST)
	public ResponseResult uploadItemImg(@RequestBody MultipartFile img, HttpSession session) {
		if (img == null) {
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
		try {
			String url = UploadFileUtil.uploadTencentCloud(img, (Long) session.getAttribute("userId"),
					UploadFileEnum.ITEM)[0];
			Map<String, String> imgUrl = new HashMap<>();
			imgUrl.put("url", url);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", imgUrl);
		} catch (Exception e) {
			logger.error("upload item image error:", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());

		}
	}

}
