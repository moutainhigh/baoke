package com.baoke.api.domain.response;

import java.util.List;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.dto.api.VideoComplaintDictListDto;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoComplaintDictDto;

/**
 * 获取举报字典响应体
 * 
 * @author zjm
 * @date: 2018年6月13日 下午3:31:04
 */
public class QueryVideoComplaintResponse extends BaseResponseParam {

	private static final long serialVersionUID = 1L;

	private List<VideoComplaintDictDto> complaintDictList;// 视频举报字典集合

	private Boolean isComplaint;// 是否举报过

	public List<VideoComplaintDictDto> getComplaintDictList() {
		return complaintDictList;
	}

	public void setComplaintDictList(List<VideoComplaintDictDto> complaintDictList) {
		this.complaintDictList = complaintDictList;
	}

	public Boolean getIsComplaint() {
		return isComplaint;
	}

	public void setIsComplaint(Boolean isComplaint) {
		this.isComplaint = isComplaint;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {
		if (null == baseResultDto)
			return this;

		VideoComplaintDictListDto complaintListDto = (VideoComplaintDictListDto) baseResultDto;
		this.complaintDictList = complaintListDto.getComplaintDictList();
		this.isComplaint = complaintListDto.getIsComplaint();

		return this;
	}

}
