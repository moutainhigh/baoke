package com.baoke.seller.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
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
import com.baoke.common.domain.result.ResponseResult;
import com.baoke.common.dto.info.SellerAuditInfoDto;
import com.baoke.common.dto.seller.SellerAudioStatusDto;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.UploadFileUtil;
import com.baoke.item.service.VideoInfoService;
import com.baoke.user.service.SellerInfoService;
import com.baoke.user.service.UserLoginSmsService;

/**
 * 主播相关控制器
 * 
 * @author zjm
 * @date: 2018年6月22日 上午11:19:41
 */
@RestController
@RequestMapping("/seller")
public class SellerController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private SellerInfoService sellerInforService;
	@Resource
	private UserLoginSmsService userLoginService;

	@Resource
	private VideoInfoService videoInfoService;

	/**
	 * 查询认证结果
	 * 
	 * @author ljj
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/querySellerAuthStatus", method = RequestMethod.POST)
	public ResponseResult querySellerAuthStatus(HttpSession session) {
		try {
			SellerAudioStatusDto sellerInforStatusDto = sellerInforService
					.querySellerAudioStatus((Long) session.getAttribute("userId"));
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", sellerInforStatusDto);
		} catch (MainException e) {
			logger.error("query sellerAuthStatus error.", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("query sellerAuthStatus error.", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 查询主播认证信息
	 * 
	 * @author ljj
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/querySellerInfo", method = RequestMethod.POST)
	public ResponseResult querySellerInfo(HttpSession session) {
		try {
			SellerAuditInfoDto sellerInfoDto = sellerInforService
					.querySellerAuditInfoBySellerId((Long) session.getAttribute("userId"));
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", sellerInfoDto);
		} catch (MainException e) {
			logger.error("query SellerInfo attestation error," + session.getAttribute("userId"), e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("query SellerInfo attestation error," + session.getAttribute("userId"), e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
	}

	/**
	 * 保存主播认证信息
	 * 
	 * @author ljj
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/authentication", method = RequestMethod.POST)
	public ResponseResult authentication(SellerAuditInfoDto sellerInfoCertificationDto, HttpSession session) {
		sellerInfoCertificationDto.setSellerId((Long) session.getAttribute("userId"));

		try {
			sellerInforService.saveSellerInfo(sellerInfoCertificationDto);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "");
		} catch (MainException e) {
			logger.error("authentication error." + sellerInfoCertificationDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error("authentication error." + sellerInfoCertificationDto, e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());

		}
	}

	/**
	 * 上传手持身份证照片
	 * 
	 * @author zjm
	 * @date: 2018年6月22日 上午11:18:44
	 * @return
	 */
	@RequestMapping(value = "/uploadIdCardImg", method = RequestMethod.POST)
	public ResponseResult uploadIdCardImg(@RequestBody MultipartFile img, HttpSession session) {

		if (img == null) {
			return new ResponseResult(ResponseResultCodeEnum.ERROR, ResponseResultCodeEnum.ERROR.getName());
		}
		try {
			String url = UploadFileUtil.uploadTencentCloud(img, (Long) session.getAttribute("userId"),
					UploadFileEnum.USER_IDCARD)[0];
			Map<String, String> vedioUrl = new HashMap<>();
			vedioUrl.put("imgUrl", url);
			return new ResponseResult(ResponseResultCodeEnum.SUCCESS, "", vedioUrl);
		} catch (Exception e) {
			logger.error("upload id card image error:", e);
			return new ResponseResult(ResponseResultCodeEnum.ERROR, e.getMessage());
		}
	}

}
