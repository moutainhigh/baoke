package com.baoke.common.dto.api;

import java.util.List;

import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.dto.info.VideoComplaintDictDto;

/**
 * 举报字典
 * 
 * @author zjm
 * @date: 2018年6月13日 下午3:38:54
 */
public class VideoComplaintDictListDto extends BaseResultDto {

	private static final long serialVersionUID = 1L;

	private List<VideoComplaintDictDto> complaintDictList;

	private Boolean isComplaint;

	public VideoComplaintDictListDto() {
	}

	public VideoComplaintDictListDto(List<VideoComplaintDictDto> complaintDtoList, boolean isComplaint) {
		this.complaintDictList = complaintDtoList;
		this.isComplaint = isComplaint;
	}

	public VideoComplaintDictListDto(boolean success, String message, List<VideoComplaintDictDto> complaintDictList,
			Boolean isComplaint) {
		super(success, message);
		this.complaintDictList = complaintDictList;
		this.isComplaint = isComplaint;
	}

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

}
