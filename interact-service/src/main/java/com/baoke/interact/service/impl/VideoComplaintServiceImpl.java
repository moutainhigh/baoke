package com.baoke.interact.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.constant.config.CommonConfig;
import com.baoke.common.domain.message.SiteMessage;
import com.baoke.common.dto.api.VideoComplaintDictListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.base.PageInfo;
import com.baoke.common.dto.info.VideoComplaintDictDto;
import com.baoke.common.dto.info.VideoComplaintInfoDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.common.dto.seller.VideoComplaintListDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.UserEmptyException;
import com.baoke.common.exception.base.MainException;
import com.baoke.common.util.CollectionUtil;
import com.baoke.common.util.StringUtil;
import com.baoke.interact.constant.VideoComplaintStatusEnum;
import com.baoke.interact.domain.VideoComplaint;
import com.baoke.interact.domain.VideoComplaintDict;
import com.baoke.interact.manager.VideoComplaintDictManager;
import com.baoke.interact.manager.VideoComplaintManager;
import com.baoke.interact.service.VideoComplaintService;
import com.baoke.item.domain.VideoInfo;
import com.baoke.item.manager.VideoInfoManager;
import com.baoke.user.domain.UserInfo;
import com.baoke.user.manager.SendSiteManager;
import com.baoke.user.manager.UserInfoManager;

/**
 * 举报service实现类
 * 
 * @author zjm
 * @date: 2018年6月13日 下午4:03:01
 */
@Service("videoComplaintService")
@ServiceDefinition(value = "videoComplaintService")
public class VideoComplaintServiceImpl implements VideoComplaintService {

	private static final Logger logger = Logger.getLogger(VideoComplaintServiceImpl.class);

	@Resource
	private VideoComplaintDictManager videoComplaintDictManager;

	@Resource
	private VideoComplaintManager videoComplaintManager;

	@Resource
	private UserInfoManager userInfoManager;

	@Resource
	private VideoInfoManager videoInfoManager;

	@Resource
	private SendSiteManager sendSiteManager;

	@Override
	@MethodDefinition(value = "queryVideoComplaint", needLogin = true)
	public VideoComplaintDictListDto queryVideoComplaint(VideoComplaintInfoDto videoComplaintInfoDto)
			throws MainException {
		if (null == videoComplaintInfoDto.getUserId()) {
			throw new UserEmptyException();
		}
		if (null == videoComplaintInfoDto.getVideoId()) {
			throw new ParamInvalidException("视频id不能为空");
		}

		List<VideoComplaintDict> complaintVideoDictList = videoComplaintDictManager
				.queryVideoComplaintDictList(new VideoComplaintDict(BooleanEnum.TRUE));
		List<VideoComplaintDictDto> videoComplaintDictDtoList = new ArrayList<VideoComplaintDictDto>();
		for (VideoComplaintDict videoComplaintDict : complaintVideoDictList) {
			videoComplaintDictDtoList.add(convertComplaintDto(videoComplaintDict));
		}

		List<VideoComplaint> videoComplaintList = videoComplaintManager.queryVideoComplaintList(
				new VideoComplaint(videoComplaintInfoDto.getVideoId(), videoComplaintInfoDto.getUserId()));
		boolean isComplaint = false;
		if (!CollectionUtils.isEmpty(videoComplaintList)) {
			isComplaint = true;
		}

		return new VideoComplaintDictListDto(true, "", videoComplaintDictDtoList, isComplaint);
	}

	@Override
	public VideoComplaintListDto queryVideoComplaintListByPage(CommonQueryDto commonQueryDto)
			throws ParamInvalidException {

		List<Long> userIds = new ArrayList<>();
		if (StringUtil.hasLength(commonQueryDto.getNickName())) {
			List<UserInfo> userInfoList = userInfoManager.queryUserInfoByNickName(commonQueryDto.getNickName());
			if (CollectionUtil.isEmpty(userInfoList)) {
				logger.error("query comment list error, user is empty. nickName=" + commonQueryDto.getNickName());
				return new VideoComplaintListDto(false, null, null, new PageInfo());
			}
			for (UserInfo userInfo : userInfoList) {
				userIds.add(userInfo.getId());
			}
		}

		List<Long> videoIds = new ArrayList<>();
		if (StringUtil.hasLength(commonQueryDto.getVideoTitle())) {
			List<VideoInfo> videoInfoList = videoInfoManager.queryVideoInfoListByTitle(commonQueryDto.getVideoTitle());

			if (CollectionUtil.isEmpty(videoInfoList)) {
				logger.error("query comment list error, video is empty. sellerId=" + commonQueryDto.getVideoTitle());
				return new VideoComplaintListDto(false, null, null, new PageInfo());
			}
			for (VideoInfo videoInfo : videoInfoList) {
				videoIds.add(videoInfo.getId());
			}
		}

		int total = videoComplaintManager.countVideoComplaintList(videoIds, userIds, commonQueryDto.getContent());
		PageInfo pageInfo = commonQueryDto.getPageInfo();
		pageInfo.setTotal(total);

		List<VideoComplaint> videoComplaintList = videoComplaintManager.queryVideoComplaintListByPage(videoIds, userIds,
				commonQueryDto.getContent(), pageInfo);
		List<VideoComplaintInfoDto> videoComplaintInfoDtoList = convertVideoComplaintList2VideoComplaintInfoDtoList(
				videoComplaintList);

		return new VideoComplaintListDto(true, "", videoComplaintInfoDtoList, pageInfo);
	}

	private List<VideoComplaintInfoDto> convertVideoComplaintList2VideoComplaintInfoDtoList(
			List<VideoComplaint> videoComplaintList) throws ParamInvalidException {
		List<VideoComplaintInfoDto> videoComplaintInfoDtoList = new ArrayList<>();
		for (VideoComplaint videoComplaint : videoComplaintList) {

			VideoComplaintInfoDto videoComplaintInfoDto = videoComplaint.convert();
			VideoInfo videoInfo = videoInfoManager.queryVideoInfoById(videoComplaint.getVideoId());
			String title = videoInfo == null || videoInfo.getTitle() == null ? "示例视频" : videoInfo.getTitle();
			videoComplaintInfoDto.setTitle(title);
			UserInfo userInfo = userInfoManager.queryUserInfoById(videoComplaint.getUserId());
			String nickName = userInfo == null || userInfo.getNickName() == null ? "匿名用户" : userInfo.getNickName();
			videoComplaintInfoDto.setNickName(nickName);
			videoComplaintInfoDtoList.add(videoComplaintInfoDto);
		}
		return videoComplaintInfoDtoList;
	}

	@Override
	@MethodDefinition(value = "saveComplaint", needLogin = true)
	public BaseResultDto saveComplaint(VideoComplaintInfoDto videoComplaintDto) throws MainException {
		if (null == videoComplaintDto.getUserId() || videoComplaintDto.getUserId() == 0) {
			throw new UserEmptyException();
		}
		if (null == videoComplaintDto.getVideoId() || videoComplaintDto.getVideoId() == 0) {
			throw new ParamInvalidException("视频id不能为空");
		}
		if (StringUtil.isEmpty(videoComplaintDto.getContent())) {
			throw new ParamInvalidException("内容不能为空");
		}

		VideoInfo videoInfo = videoInfoManager.queryVideoInfoById(videoComplaintDto.getVideoId());
		if (null == videoInfo) {
			return new BaseResultDto(false, "举报失败！您要举报的视频已下线，感谢支持！");
		}

		VideoComplaint videoComplaint = convertVideoComplaint(videoComplaintDto);
		videoComplaint.setSellerId(videoInfo.getSellerId());
		videoComplaint.setStatus(VideoComplaintStatusEnum.PROCESS.getCode());

		videoComplaintManager.addVideoComplaint(videoComplaint);
		if (logger.isDebugEnabled()) {
			logger.debug("save video complaint success, " + videoComplaint);
		}

		return new BaseResultDto(true, "您的举报已收到，感谢支持！");
	}

	@Override
	public BaseResultDto modifyVideoComplaintStatus(long id, VideoComplaintStatusEnum videoComplaintStatusEnum) {

		VideoComplaint videoComplaint = videoComplaintManager.queryVideoComplaintById(id);
		if (null == videoComplaint) {
			return new BaseResultDto(false, "该举报记录不存在！");
		}
		if (videoComplaint.getStatus() != VideoComplaintStatusEnum.PROCESS.getCode()) {
			return new BaseResultDto(false, "该举报已被处理，不可重复处理！");
		}

		videoComplaintManager.modifyVideoComplaintStatus(id, videoComplaintStatusEnum);
		if (logger.isDebugEnabled()) {
			logger.debug("modify video complaint status success, id=" + id + ", oldStatus=" + videoComplaint.getStatus()
					+ ", newStatus=" + videoComplaintStatusEnum.getCode());
		}

		// 站内信-给举报者
		String content = "您的举报：" + videoComplaint.getContent() + "已处理，处理结果：" + videoComplaintStatusEnum.getName();
		SiteMessage siteMessage = SiteMessage.createSystemSiteMessage(CommonConfig.SYSTEM_USER_ID,
				videoComplaint.getUserId(), videoComplaint.getVideoId(), "视频举报", content);
		sendSiteManager.sendSite(siteMessage);
		if (videoComplaintStatusEnum.getCode() == VideoComplaintStatusEnum.SUCCESS.getCode()) {
			// 如果举报成功，则通知主播
			content = "您的视频已被举报，举报内容：" + videoComplaint.getContent();
			SiteMessage message = SiteMessage.createSystemSiteMessage(CommonConfig.SYSTEM_USER_ID,
					videoComplaint.getSellerId(), videoComplaint.getVideoId(), "视频举报", content);
			sendSiteManager.sendSite(message);
		}

		return new BaseResultDto(true, "已处理！");
	}

	// 举报dto转化为domain
	private VideoComplaint convertVideoComplaint(VideoComplaintInfoDto complaintDto) {
		VideoComplaint videoComplaint = new VideoComplaint();
		BeanUtils.copyProperties(complaintDto, videoComplaint);
		return videoComplaint;
	}

	// 字典domain转化为dto
	private VideoComplaintDictDto convertComplaintDto(VideoComplaintDict videoComplaintDict) {
		VideoComplaintDictDto videoComplaintDictDto = new VideoComplaintDictDto();
		BeanUtils.copyProperties(videoComplaintDict, videoComplaintDictDto);
		return videoComplaintDictDto;
	}

}
