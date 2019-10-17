package com.jiaxin.pda.service;

import com.jiaxin.pda.entity.dto.DictionaryDto;
import com.jiaxin.pda.entity.dto.DictionaryTypeDto;
import com.jiaxin.pda.entity.vo.DictionaryTypeVo;
import com.jiaxin.pda.entity.vo.DictionaryVo;

import java.util.List;

/**
 * 字典业务接口
 * @author milo
 *
 */
public interface DictionaryTypeService {
    /**
     * 插入字典类型
     * @param dictionaryTypeVo
     * @return
     */
    int insertDictionaryType(DictionaryTypeVo dictionaryTypeVo);

    /**
     * 修改字典类型
     * @param dictionaryTypeVo
     * @return
     */
    int updateDictionaryType(DictionaryTypeVo dictionaryTypeVo);

    /**
     * 删除字典类型(逻辑删除)
     * @param id
     * @return
     */
    int deleteDictionaryType(String id);

    /**
     * 分页查询字典类型
     * @param dictionaryTypeDto
     * @return
     */
    List<DictionaryTypeVo> queryDictionaryTypeByPage(DictionaryTypeDto dictionaryTypeDto);

    /**
     * 分页查询字典
     * @param dictionaryDto
     * @return
     */
    List<DictionaryVo> queryDictionaryByPage(DictionaryDto dictionaryDto);

    /**
     * 插入字典项
     * @param dictionaryVo 字典对象
     * @return 返回结果
     */
    int insertDictionaryItem(DictionaryVo dictionaryVo);


    /**
     * 修改字典项
     * @param dictionaryVo
     * @return
     */
    int updateDictionaryItem(DictionaryVo dictionaryVo);

    /**
     * 删除字典项(逻辑删除)
     * @param id
     * @return
     */
    int deleteDictionaryItem(String id);

    /**
     * 根据类型ID查询字典
     * @param typeUuid
     * @return
     */
    List<DictionaryVo> queryDictionaryByTypeId(Integer typeUuid);

    /**
     * 根据名称查询数据字典类型信息
     * @param dictionaryTypeName
     * @return
     */
    DictionaryTypeVo queryDictionaryTypeInfoByName(String dictionaryTypeName);
}
