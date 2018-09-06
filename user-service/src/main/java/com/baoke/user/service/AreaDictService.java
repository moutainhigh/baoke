package com.baoke.user.service;

import com.baoke.common.dto.api.AddressDictListDto;
import com.baoke.common.dto.info.AreaDictInfoDto;

/**
 * 地区字典service
 * 
 * @author: wyj
 * @date: 2018年6月13日 下午3:52:27
 */
public interface AreaDictService {

	AddressDictListDto queryAddressDictByParentCode(AreaDictInfoDto areaDictInfoDto);
}
