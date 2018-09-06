package com.baoke.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;
import com.baoke.common.dto.api.AddressDictListDto;
import com.baoke.common.dto.info.AreaDictInfoDto;
import com.baoke.user.domain.AreaDictInfo;
import com.baoke.user.manager.AreaDictManager;
import com.baoke.user.service.AreaDictService;

/**
 * 地区字典service实现
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:51:17
 */
@ServiceDefinition(value = "areaDictService")
@Service("areaDictService")
public class AreaDictServiceImpl implements AreaDictService {

	@Resource
	private AreaDictManager areaDictManager;

	@Override
	@MethodDefinition(value = "queryAddressDict")
	public AddressDictListDto queryAddressDictByParentCode(AreaDictInfoDto areaDictInfoDto) {

		List<AreaDictInfo> areaDictList = areaDictManager.queryAddressDictByParentCode(areaDictInfoDto.getParentCode());
		List<AreaDictInfoDto> areaDictDtoList = new ArrayList<>();
		for (AreaDictInfo areaDictInfo : areaDictList) {
			areaDictDtoList.add(areaDictInfo.convert());
		}

		AddressDictListDto addressDictListDto = new AddressDictListDto(true, "");
		addressDictListDto.setAddressList(areaDictDtoList);
		return addressDictListDto;
	}

}
