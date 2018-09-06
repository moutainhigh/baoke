package com.baoke.trade.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.dto.PostageCompanyListDto;
import com.baoke.common.dto.info.PostageCompanyInfoDto;
import com.baoke.trade.domain.PostageCompanyInfo;
import com.baoke.trade.manager.PostageCompanyInfoManager;
import com.baoke.trade.service.PostageCompanyInfoService;

import net.sf.cglib.beans.BeanCopier;

/**
 * 快递公司service实现
 * 
 * @author zjm
 * @date: 2018年7月3日 下午7:33:38
 */
@Service("postageCompanyInfoService")
@ServiceDefinition(value = "postageCompanyInfoService")
public class PostageCompanyInfoServiceImpl implements PostageCompanyInfoService {

	@Resource
	private PostageCompanyInfoManager postageCompanyInfoManager;

	@MethodDefinition
	@Override
	public PostageCompanyListDto queryPostageCompanyInfoList() {

		List<PostageCompanyInfo> postageCompanyInfoList = postageCompanyInfoManager.queryPostageCompanyInfoList();

		List<PostageCompanyInfoDto> postageCompanyInfoDtoList = new ArrayList<>();

		for (PostageCompanyInfo postageCompanyInfo : postageCompanyInfoList) {
			postageCompanyInfoDtoList.add(converPostageCompanyInfoToDto(postageCompanyInfo));
		}

		return new PostageCompanyListDto(true, "", postageCompanyInfoDtoList);
	}

	private PostageCompanyInfoDto converPostageCompanyInfoToDto(PostageCompanyInfo postageCompanyInfo) {

		PostageCompanyInfoDto postageCompanyInfoDto = new PostageCompanyInfoDto();
		final BeanCopier bc = BeanCopier.create(postageCompanyInfo.getClass(), postageCompanyInfoDto.getClass(), false);
		bc.copy(postageCompanyInfo, postageCompanyInfoDto, null);
		postageCompanyInfoDto.setPostageCompanyId(postageCompanyInfo.getId());

		return postageCompanyInfoDto;
	}

}
