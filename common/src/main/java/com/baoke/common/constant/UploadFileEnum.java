package com.baoke.common.constant;

/**
 * 上传文件类型定义
 * 
 */
public enum UploadFileEnum {

	USER_NICK("USER_NICK", "IMAGE", "user", "head", "用户私有存储空间"),

	USER_IDCARD("USER_IDCARD", "IMAGE", "user", "idcard", "用户私有存储空间"),

	ITEM("ITEM", "IMAGE", "item", "item", "商品私有存储空间"),

	VIDEO("VIDEO", "VIDEO", "video", "video", "视频私有存储空间"),

	VIDEO_COVER("VIDEO_COVER", "IMAGE", "video", "cover", "视频私有存储空间"),

	ACTIVITY("ACTIVITY", "IMAGE", "baoke", "activity", "公有存储空间"),

	BANNER("BANNER", "IMAGE", "baoke", "banner", "公有存储空间");

	private String name;

	private String fileType;

	private String bucket;

	private String subBucket;

	private String desc;

	private UploadFileEnum(String name, String fileType, String bucket, String subBucket, String desc) {
		this.name = name;
		this.fileType = fileType;
		this.bucket = bucket;
		this.subBucket = subBucket;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getSubBucket() {
		return subBucket;
	}

	public void setSubBucket(String subBucket) {
		this.subBucket = subBucket;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
