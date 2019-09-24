package com.jiaxin.pda.service.impl;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.vo.DictionaryTypeVo;
import com.jiaxin.pda.mapper.DictionaryTypeMapper;
import com.jiaxin.pda.service.DictionaryTypeService;
import com.jiaxin.pda.util.GenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典业务实现类
 * @author milo
 */
@Service
public class DictionaryTypeServiceImpl implements DictionaryTypeService {

    @Autowired
    private DictionaryTypeMapper dictionaryTypeMapper;

    @Override
    public int insertDictionaryType(DictionaryTypeVo dictionaryTypeVo) {
        dictionaryTypeVo.setId(GenerateUtil.generateRandomString());
        dictionaryTypeVo.setUuid(dictionaryTypeMapper.queryMaxId() + Constant.INCREASE_PACE);
        dictionaryTypeVo.setDeleteFlag(false);
        dictionaryTypeVo.setReversion(Constant.INIT_REVERSION);
        dictionaryTypeVo.setCreatedBy(Constant.SUPER_ADMIN);
        dictionaryTypeVo.setCreatedTime(Constant.NOW);
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

    /**
     * 初始化更新参数
     * @param dictionaryTypeVo
     */
    private void initUpdateParam(DictionaryTypeVo dictionaryTypeVo){
        dictionaryTypeVo.setUpdatedBy(Constant.SUPER_ADMIN);
        dictionaryTypeVo.setUpdatedTime(Constant.NOW);
    }


}
