package com.jiaxin.pda.service.impl;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.dto.DictionaryDto;
import com.jiaxin.pda.entity.dto.DictionaryTypeDto;
import com.jiaxin.pda.entity.vo.DictionaryTypeVo;
import com.jiaxin.pda.entity.vo.DictionaryVo;
import com.jiaxin.pda.entity.vo.OperateVo;
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
        //初始化更新参数
        this.initUpdateParam(dictionaryTypeVo);
        return dictionaryTypeMapper.insertDictionaryType(dictionaryTypeVo);
    }

    @Override
    public int updateDictionaryType(DictionaryTypeVo dictionaryTypeVo) {
        //初始化更新参数
        this.initUpdateParam(dictionaryTypeVo);
        return dictionaryTypeMapper.updateDictionaryType(dictionaryTypeVo);
    }

    @Override
    public int deleteDictionaryType(String id) {
        DictionaryTypeVo dictionaryTypeVo = dictionaryTypeMapper.findById(id);
        dictionaryTypeVo.setDeleteFlag(true);
        //初始化更新参数
        this.initUpdateParam(dictionaryTypeVo);
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
        dictionaryVo.setDeleteFlag(false);
        dictionaryVo.setReversion(Constant.INIT_REVERSION);
        dictionaryVo.setCreatedBy(Constant.SUPER_ADMIN);
        dictionaryVo.setCreatedTime(Constant.NOW);
        //初始化更新参数
        this.initUpdateParam(dictionaryVo);
        return dictionaryMapper.insertDictionaryItem(dictionaryVo);
    }

    @Override
    public int updateDictionaryItem(DictionaryVo dictionaryVo) {
        //初始化更新参数
        this.initUpdateParam(dictionaryVo);
        return dictionaryMapper.updateDictionaryItem(dictionaryVo);
    }

    @Override
    public int deleteDictionaryItem(String id) {
        DictionaryVo dictionaryVo = dictionaryMapper.findById(id);
        dictionaryVo.setDeleteFlag(true);
        //初始化更新参数
        this.initUpdateParam(dictionaryVo);
        return dictionaryMapper.deleteDictionaryItem(dictionaryVo);
    }

    @Override
    public List<DictionaryVo> queryDictionaryByTypeId(Integer typeUuid) {
        return dictionaryMapper.queryDictionaryByTypeId(typeUuid);
    }

    /**
     * 初始化更新参数
     * @param operateVo
     */
    private void initUpdateParam(OperateVo operateVo){
        operateVo.setUpdatedBy(Constant.SUPER_ADMIN);
        operateVo.setUpdatedTime(Constant.NOW);
    }



}
