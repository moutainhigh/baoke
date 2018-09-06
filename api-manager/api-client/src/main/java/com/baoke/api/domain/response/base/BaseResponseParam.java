package com.baoke.api.domain.response.base;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.baoke.api.constant.ApiProcessStatus;
import com.baoke.api.util.BkBase64;
import com.baoke.common.dto.base.BaseResultDto;
import com.baoke.common.exception.ParamInvalidException;
import com.baoke.common.util.StringUtil;

/**
 * 基础响应参数
 * 
 * @author zjm
 * @Date: 2018年6月4日 下午3:32:35
 */
public abstract class BaseResponseParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 接口返回状态码 */
	private Integer resultStatus;

	/**
	 * 接口返回操作结果
	 * 
	 * true:表示操作成功 false:表示操作失败
	 * 
	 */
	private Boolean resultSuccess;

	/** 响应描述，用于页面输出 */
	private String resultMessage;

	public BaseResponseParam() {
		super();
	}

	public abstract BaseResponseParam convert(BaseResultDto baseResultDto) throws ParamInvalidException;

	/**
	 * Id加密下发客户端
	 * 
	 * @author wyh
	 * @date 2018年6月29日 下午1:40:49
	 * 
	 * @param id
	 * @return
	 * @throws ParamInvalidException
	 */
	public String getCodeFromId(Long id) throws ParamInvalidException {
		if (null != id) {
			return BkBase64.long2Base64(id);
		}
		return "";
	}

	public void setApiStatus(ApiProcessStatus apiProcessStatus) {
		this.resultStatus = apiProcessStatus.getCode();
		if (StringUtil.isBlank(this.resultMessage)) {
			this.resultMessage = apiProcessStatus.getName();
		}
	}

	public Integer getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(Integer resultStatus) {
		this.resultStatus = resultStatus;
	}

	public Boolean getResultSuccess() {
		return resultSuccess;
	}

	public void setResultSuccess(Boolean resultSuccess) {
		this.resultSuccess = resultSuccess;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
