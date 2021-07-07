package com.jiaxin.pda.service.impl;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.dto.DictionaryDto;
import com.jiaxin.pda.entity.dto.DictionaryTypeDto;
import com.jiaxin.pda.entity.po.DictionaryPO;
import com.jiaxin.pda.entity.vo.DictionaryTypeVo;
import com.jiaxin.pda.entity.vo.DictionaryVo;
import com.jiaxin.pda.mapper.DictionaryMapper;
import com.jiaxin.pda.mapper.DictionaryTypeMapper;
import com.jiaxin.pda.service.IDictionaryTypeService;
import com.jiaxin.pda.util.GenerateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典业务实现类
 * @author milo
 */
@Service
public class DictionaryTypeServiceImpl implements IDictionaryTypeService {

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

    /**
     * 删除字典类型，发生异常的时候回滚
     * @param dictionaryTypeVo
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int deleteDictionaryType(DictionaryTypeVo dictionaryTypeVo) {
        dictionaryTypeVo.setDeleteFlag(true);
        int result = dictionaryTypeMapper.deleteDictionaryType(dictionaryTypeVo);
        //删掉字典项
        if(Constant.OPERATE_SUCCESS == result){
            result = dictionaryMapper.deleteDictionaryByTypeId(dictionaryTypeVo.getUuid());
        }
        return result;
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
        DictionaryPO dictionaryPo = new DictionaryPO();
        //属性复制
        BeanUtils.copyProperties(dictionaryVo,dictionaryPo);
        dictionaryPo.setDeleteFlag(0);
        dictionaryPo.setId(GenerateUtil.generateRandomString());
        dictionaryPo.setUuid(dictionaryMapper.selectMaxUuid() + Constant.INCREASE_PACE);
        return dictionaryMapper.insert(dictionaryPo);
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

    @Override
    public DictionaryVo queryDictionaryItemInfoByUuid(int uuid) {
        return dictionaryMapper.queryDictionaryItemInfoByUuid(uuid);
    }

    @Override
    public List<DictionaryVo> queryDictionaryByUserId(Integer userId) {
        return dictionaryMapper.queryDictionaryByUserId(userId);
    }

}
