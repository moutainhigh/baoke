package com.baoke.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.baoke.common.cloud.upload.TencentCloudUploadClient;
import com.baoke.common.cloud.upload.TencentCloudUploadConfig;
import com.baoke.common.cloud.upload.TencentCloudUploadResult;
import com.baoke.common.constant.UploadFileEnum;
import com.baoke.common.exception.ParamInvalidException;

/**
 * 文件上传工具类
 *
 */
public class UploadFileUtil {

	/**
	 * 腾讯云图片等比缩放功能（只能压缩）
	 * 
	 * @author wyh
	 * @date 2018年7月12日 下午5:47:00
	 * 
	 * @param imgUrl
	 *            腾讯云图像URL
	 * @param width
	 *            缩放后宽
	 * @param height
	 *            缩放后高
	 * @return
	 */
	public static String getImageView(String imgUrl, int width, int height) {
		if (null == imgUrl) {
			return "";
		}
		// 将图像等比缩放至宽高都小于设定最大值
		return imgUrl + "?imageView2/2/w/" + width + "/h/" + height;
	}

	/**
	 * 上传文件到腾讯云对象存储
	 * 
	 * 支持任意图片/视频格式，上传后统一转化为jpg/mp4
	 * 
	 * @author wyh
	 * @date 2018年7月11日 下午5:28:08
	 * 
	 * @param multipartFile
	 * @param uploadFileEnum
	 * @return 如果是图片上传，返回图片上传的路径； 如果是视频上传，返回封面图路径和视频路径
	 * @throws IOException
	 * @throws ParamInvalidException
	 */
	public static String[] uploadTencentCloud(MultipartFile multipartFile, long userId, UploadFileEnum uploadFileEnum)
			throws IOException, ParamInvalidException {

		if (null == multipartFile) {
			throw new ParamInvalidException("上传文件无效");
		}
		if (null == uploadFileEnum) {
			throw new ParamInvalidException("上传文件参数无效");
		}

		String contentType = null;
		if (multipartFile.getContentType() != null) {
			contentType = multipartFile.getContentType();
		} else if (multipartFile.getOriginalFilename() != null) {
			contentType = TencentCloudUploadClient.getContentType(multipartFile.getOriginalFilename());
		} else {
			contentType = TencentCloudUploadClient.getContentType(multipartFile.getName());
		}

		return uploadTencentCloud(multipartFile.getInputStream(), multipartFile.getSize(), contentType, userId,
				uploadFileEnum);
	}

	/**
	 * 上传文件到腾讯云对象存储
	 * 
	 * 支持任意图片/视频格式，上传后统一转化为jpg/mp4
	 * 
	 * @author wyh
	 * @date 2018年7月11日 下午5:28:08
	 * 
	 * @param data
	 *            文件
	 * @param contentType
	 *            文件类型
	 * @param userId
	 *            用户ID
	 * @param uploadFileEnum
	 * @return 如果是图片上传，返回图片上传的路径； 如果是视频上传，返回封面图路径和视频路径
	 * @throws IOException
	 * @throws ParamInvalidException
	 */
	public static String[] uploadTencentCloud(byte[] data, String contentType, long userId,
			UploadFileEnum uploadFileEnum) throws IOException, ParamInvalidException {

		if (null == data || 0 == data.length) {
			throw new ParamInvalidException("上传文件无效");
		}
		if (StringUtil.isBlank(contentType)) {
			throw new ParamInvalidException("上传文件类型无效");
		}

		return uploadTencentCloud(new ByteArrayInputStream(data), data.length, contentType.toLowerCase(), userId,
				uploadFileEnum);
	}

	private static String[] uploadTencentCloud(InputStream inputStream, long size, String contentType, long userId,
			UploadFileEnum uploadFileEnum) throws IOException {

		String bucketName = uploadFileEnum.getBucket() + "-" + TencentCloudUploadConfig.APPID;

		String filePath = "/" + uploadFileEnum.getSubBucket() + "/" + DateUtil.format(new Date(), "yyMMdd")
				+ UserSignUtil.getSign(userId, 4) + (int) (Math.random() * 10) + UUIDUtil.getUUID();

		boolean isVideo = "VIDEO".equalsIgnoreCase(uploadFileEnum.getFileType());

		if (isVideo) {
			filePath = filePath + ".mp4";
		} else {
			filePath = filePath + ".jpg";
		}

		// upload
		TencentCloudUploadResult tencentCloudUploadResult = new TencentCloudUploadClient().uploadFile(inputStream,
				bucketName, filePath, size, contentType, isVideo);

		if (isVideo) {
			return new String[] { tencentCloudUploadResult.getImgUrl(), tencentCloudUploadResult.getVideoUrl() };
		} else {
			return new String[] { tencentCloudUploadResult.getImgUrl() };
		}
	}

	public static void main(String[] args) {
		System.out.println(UploadFileUtil.getImageView(
				"http://user-1254128350.picsh.myqcloud.com/idcard/18071298e8684f054242e8b39d4f68f1d542b4.jpg", 100,
				100));
	}
}
