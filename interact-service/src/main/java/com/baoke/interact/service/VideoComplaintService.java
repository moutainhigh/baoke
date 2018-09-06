package com.baoke.interact.service;

import com.baoke.common.dto.api.VideoComplaintDictListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoComplaintInfoDto;
import com.baoke.common.dto.seller.CommonQueryDto;
import com.baoke.common.dto.seller.VideoComplaintListDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.exception.base.MainException;
import com.baoke.interact.constant.VideoComplaintStatusEnum;

/**
 * 举报service
 * 
 * @author zjm
 * @date: 2018年6月13日 下午3:59:31
 */
public interface VideoComplaintService {

	/**
	 * 获取举报字典
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午4:01:06
	 * @param baseDto
	 * @return
	 * @throws MainException
	 */
	VideoComplaintDictListDto queryVideoComplaint(VideoComplaintInfoDto videoComplaintInfoDto) throws MainException;

	/**
	 * 分页查询举报列表
	 * 
	 * @author: wyj
	 * @date: 2018年7月9日 下午3:14:10
	 */
	VideoComplaintListDto queryVideoComplaintListByPage(CommonQueryDto commonQueryDto) throws ParamInvalidException;

	/**
	 * 保存举报（吐槽）
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午4:02:36
	 * @param complaintDto
	 * @return
	 * @throws MainException
	 */
	BaseResultDto saveComplaint(VideoComplaintInfoDto videoComplaintDto) throws MainException;

	/**
	 * 保存举报记录处理结果接口
	 * 
	 * @author: wyj
	 * @date: 2018年7月10日 上午10:32:31
	 */
	BaseResultDto modifyVideoComplaintStatus(long id, VideoComplaintStatusEnum videoComplaintResultEnum);

}
