package com.baoke.common.dto.base;

/**
 * 添加操作成功与失败状态
 * 
 * @author zdy
 * @date: 2018年6月26日 上午10:37:15
 */
public class BaseResultDto extends BaseDto {

	private static final long serialVersionUID = 7993234800345435431L;

	/** 是否成功 */
	private boolean success = false;

	/** 附加信息 */
	private String message;

	public BaseResultDto() {
	}

	public BaseResultDto(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public BaseResultDto(Long userId, Long deviceId) {
		super(userId, deviceId);
	}

	public BaseResultDto(Long userId, Long deviceId, boolean success, String message) {
		super(userId, deviceId);
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
