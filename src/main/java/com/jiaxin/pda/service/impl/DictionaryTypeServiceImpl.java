package com.jiaxin.pda.service.impl;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.dto.DictionaryDto;
import com.jiaxin.pda.entity.dto.DictionaryTypeDto;
import com.jiaxin.pda.entity.vo.DictionaryTypeVo;
import com.jiaxin.pda.entity.vo.DictionaryVo;
import com.jiaxin.pda.mapper.DictionaryMapper;
import com.jiaxin.pda.mapper.DictionaryTypeMapper;
import com.jiaxin.pda.service.DictionaryTypeService;
import com.jiaxin.pda.util.GenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典业务实现类
 * @author milo
 */
@Service
public class DictionaryTypeServiceImpl implements DictionaryTypeService {

    @Autowired
    private DictionaryTypeMapper dictionaryTypeMapper;
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    public int insertDictionaryType(DictionaryTypeVo dictionaryTypeVo) {
        dictionaryTypeVo.setId(GenerateUtil.generateRandomString());
        dictionaryTypeVo.setUuid(dictionaryTypeMapper.queryMaxId() + Constant.INCREASE_PACE);
        return dictionaryTypeMapper.insertDictionaryType(dictionaryTypeVo);
    }

    @Override
    public int updateDictionaryType(DictionaryTypeVo dictionaryTypeVo) {
        return dictionaryTypeMapper.updateDictionaryType(dictionaryTypeVo);
    }

    @Override
    public int deleteDictionaryType(DictionaryTypeVo dictionaryTypeVo) {
        dictionaryTypeVo.setDeleteFlag(true);
        return dictionaryTypeMapper.deleteDictionaryType(dictionaryTypeVo);
    }

    @Override
    public List<DictionaryTypeVo> queryDictionaryTypeByPage(DictionaryTypeDto dictionaryTypeDto) {
        return dictionaryTypeMapper.queryDictionaryTypeByPage(dictionaryTypeDto);
    }

    @Override
    public List<DictionaryVo> queryDictionaryByPage(DictionaryDto dictionaryDto) {
        return dictionaryMapper.queryDictionaryByPage(dictionaryDto);
    }


    @Override
    public int insertDictionaryItem(DictionaryVo dictionaryVo) {
        dictionaryVo.setId(GenerateUtil.generateRandomString());
        dictionaryVo.setUuid(dictionaryMapper.selectMaxUuid() + Constant.INCREASE_PACE);
        return dictionaryMapper.insertDictionaryItem(dictionaryVo);
    }

    @Override
    public int updateDictionaryItem(DictionaryVo dictionaryVo) {
        return dictionaryMapper.updateDictionaryItem(dictionaryVo);
    }

    @Override
    public int deleteDictionaryItem(DictionaryVo dictionaryVo) {
        dictionaryVo.setDeleteFlag(true);
        return dictionaryMapper.deleteDictionaryItem(dictionaryVo);
    }

    @Override
    public List<DictionaryVo> queryDictionaryByTypeId(Integer typeUuid) {
        return dictionaryMapper.queryDictionaryByTypeId(typeUuid);
    }

    @Override
    public DictionaryTypeVo queryDictionaryTypeInfoByName(String dictionaryTypeName) {
        return dictionaryTypeMapper.queryDictionaryTypeInfoByName(dictionaryTypeName);
    }

    @Override
    public DictionaryVo queryDictionaryItemInfoByName(String itemName) {
        return dictionaryMapper.queryDictionaryItemInfoByName(itemName);
    }
}
