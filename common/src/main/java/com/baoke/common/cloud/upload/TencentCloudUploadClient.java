package com.baoke.common.cloud.upload;

import java.io.File;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;

/**
 * 媒体流服务
 * 
 * @author: wyj
 * @date: 2018年6月28日 上午11:08:54
 */
public class TencentCloudUploadClient {

	private static Logger logger = Logger.getLogger(TencentCloudUploadClient.class);

	/**
	 * 将本地文件上传到COS,限制5G以内,一般用于20m大小的文件上传,即图片的上传
	 * 
	 * @author: wyj
	 * @date: 2018年6月28日 下午4:23:36
	 */
	public TencentCloudUploadResult uploadFile(File file, String bucketName, String filePath, boolean isVideo) {

		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filePath, file);

		return upload(bucketName, filePath, isVideo, putObjectRequest);
	}

	/**
	 * 从输入流进行读取并上传到COS,限制5G以内,一般用于20m大小的文件上传,即图片的上传
	 * 
	 * @author: wyj
	 * @date: 2018年6月28日 下午4:30:25
	 */
	public TencentCloudUploadResult uploadFile(InputStream inputStream, String bucketName, String filePath,
			long contentLength, String contentType, boolean isVideo) {

		ObjectMetadata objectMetadata = new ObjectMetadata();
		// 从输入流上传必须制定content length, 否则http客户端可能会缓存所有数据，存在内存OOM的情况
		objectMetadata.setContentLength(contentLength);
		// 默认下载时根据cos路径key的后缀返回响应的contenttype, 上传时设置contenttype会覆盖默认值
		objectMetadata.setContentType(contentType);

		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filePath, inputStream, objectMetadata);

		return upload(bucketName, filePath, isVideo, putObjectRequest);
	}

	private TencentCloudUploadResult upload(String bucketName, String filePath, boolean isVideo,
			PutObjectRequest putObjectRequest) {
		COSClient cosclient = createCodCLient();
		try {
			cosclient.putObject(putObjectRequest);
		} catch (CosServiceException e) {
			logger.error(String.format("上传失败，错误状态码:%s,错误码:%s,错误信息:%s,请求体:%s",
					new Object[] { e.getStatusCode(), e.getErrorCode(), e.getErrorMessage(), putObjectRequest }), e);

			String message = String.format("错误状态码:%s,错误码:%s,错误信息:%s,请求体:%s",
					new Object[] { e.getStatusCode(), e.getErrorCode(), e.getErrorMessage(), putObjectRequest });
			return new TencentCloudUploadResult(false, message);
		} catch (CosClientException e) {
			logger.error(String.format("上传失败， 错误信息:%s,请求体:%s", new Object[] { e.getMessage(), putObjectRequest }), e);

			String message = String.format("错误信息:%s,请求体:%s", new Object[] { e.getMessage(), putObjectRequest });
			return new TencentCloudUploadResult(false, message);
		}

		// 关闭客户端
		cosclient.shutdown();

		TencentCloudUploadResult tencentCloudUploadResult = new TencentCloudUploadResult(true, "");
		if (isVideo) {
			String videoUrl = String.format(TencentCloudUploadConfig.TRANSFER_VIDEO_URL,
					new Object[] { bucketName, filePath });
			String imgUrl = String.format(TencentCloudUploadConfig.TRANSFER_PICTRURE_URL,
					new Object[] { bucketName, filePath });
			tencentCloudUploadResult.setImgUrl(imgUrl);
			tencentCloudUploadResult.setVideoUrl(videoUrl);
		} else {
			String url = String.format(TencentCloudUploadConfig.DEFAULT_REGION_URL,
					new Object[] { bucketName, filePath });
			tencentCloudUploadResult.setImgUrl(url);
		}
		return tencentCloudUploadResult;
	}

	private COSClient createCodCLient() {
		// 1 初始化用户身份信息(secretId, secretKey)
		COSCredentials cosCredentials = new BasicCOSCredentials(TencentCloudUploadConfig.SECRET_ID,
				TencentCloudUploadConfig.SECRET_KEY);
		// 2 设置bucket的区域, COS地域的简称请参照
		// https://www.qcloud.com/document/product/436/6224
		ClientConfig clientConfig = new ClientConfig(new Region(TencentCloudUploadConfig.DEFAULT_REGION));
		// 3 生成cos客户端
		COSClient cosClient = new COSClient(cosCredentials, clientConfig);
		return cosClient;
	}

	public static final String getContentType(String fileName) {
		String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("bmp".equalsIgnoreCase(fileExtension))
			return "image/bmp";
		if ("gif".equalsIgnoreCase(fileExtension))
			return "image/gif";
		if ("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)
				|| "png".equalsIgnoreCase(fileExtension))
			return "image/jpeg";
		if ("html".equalsIgnoreCase(fileExtension))
			return "text/html";
		if ("txt".equalsIgnoreCase(fileExtension))
			return "text/plain";
		if ("vsd".equalsIgnoreCase(fileExtension))
			return "application/vnd.visio";
		if ("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension))
			return "application/vnd.ms-powerpoint";
		if ("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension))
			return "application/msword";
		if ("xml".equalsIgnoreCase(fileExtension))
			return "text/xml";
		return "text/html";
	}

}
