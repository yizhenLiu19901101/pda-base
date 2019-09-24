package com.jiaxin.pda.service;

import com.jiaxin.pda.entity.vo.DictionaryTypeVo;

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
}
