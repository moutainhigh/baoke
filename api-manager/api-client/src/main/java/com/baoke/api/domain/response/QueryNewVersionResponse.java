package com.baoke.api.domain.response;

import com.baoke.api.domain.response.base.BaseResponseParam;
import com.baoke.common.constant.BooleanEnum;
import com.baoke.common.dto.api.AppVersionDto;
import com.baoke.common.dto.base.BaseResultDto;

public class QueryNewVersionResponse extends BaseResponseParam {

	private static final long serialVersionUID = 8890917356938156303L;

	private String versionNo;

	private Integer hasNew;

	private Integer isForce;

	private String updateUrl;

	private String content;

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public Integer getHasNew() {
		return hasNew;
	}

	public void setHasNew(Integer hasNew) {
		this.hasNew = hasNew;
	}

	public Integer getIsForce() {
		return isForce;
	}

	public void setIsForce(Integer isForce) {
		this.isForce = isForce;
	}

	public String getUpdateUrl() {
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public BaseResponseParam convert(BaseResultDto baseResultDto) {

		if (baseResultDto == null) {
			this.hasNew = BooleanEnum.FALSE.getCode();
			return this;
		}
		AppVersionDto appVersionDto = (AppVersionDto) baseResultDto;
		this.content = appVersionDto.getContent();
		this.hasNew = appVersionDto.getHasNew();
		this.isForce = appVersionDto.getIsForce();
		this.updateUrl = appVersionDto.getUpdateUrl();
		this.versionNo = appVersionDto.getVersionNo();

		return this;
	}

}
