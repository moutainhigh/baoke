package com.baoke.interact.manager;

import java.util.List;

import com.baoke.common.dto.base.PageInfo;
import com.baoke.interact.constant.VideoComplaintStatusEnum;
import com.baoke.interact.domain.VideoComplaint;

/**
 * 举报Manager
 * 
 * @author zjm
 * @date: 2018年6月13日 下午4:04:12
 */
public interface VideoComplaintManager {

	/**
	 * 根据ID查询举报信息
	 * 
	 * @author zjj
	 * @date 2018年7月16日 下午2:40:59
	 * @param id
	 * @return
	 */
	VideoComplaint queryVideoComplaintById(long id);

	/**
	 * 查询视频举报集合
	 * 
	 * @author zdy
	 * @date: 2018年7月3日 下午7:21:38
	 * @param videoComplaint
	 * @param pageInfo
	 * @return
	 */
	List<VideoComplaint> queryVideoComplaintList(VideoComplaint videoComplaint);

	/**
	 * 分页查询举报集合
	 * 
	 * @author: wyj
	 * @date: 2018年7月9日 下午3:05:10
	 */
	List<VideoComplaint> queryVideoComplaintListByPage(List<Long> videoIds, List<Long> userIds, String content,
			PageInfo pageInfo);

	/**
	 * 待处理的举报数量
	 * 
	 * @author ljj
	 * @date: 2018年7月13日 下午7:26:23
	 * @param videoComplaintResultEnum
	 * @return
	 */
	int queryAuditingComplaintNum(VideoComplaintStatusEnum videoComplaintResultEnum);

	/**
	 * 查询举报列表总条数
	 * 
	 * @author: wyj
	 * @date: 2018年7月12日 下午3:28:31
	 */
	int countVideoComplaintList(List<Long> videoIds, List<Long> userIds, String content);

	/**
	 * 新增举报
	 * 
	 * @author zjm
	 * @date: 2018年6月13日 下午4:06:24
	 * @param videoComplaint
	 * @return
	 */
	long addVideoComplaint(VideoComplaint videoComplaint);

	/**
	 * 保存举报记录处理结果接口
	 * 
	 * @author: wyj
	 * @date: 2018年7月10日 上午10:37:45
	 */
	int modifyVideoComplaintStatus(long id, VideoComplaintStatusEnum videoComplaintResultEnum);

}
