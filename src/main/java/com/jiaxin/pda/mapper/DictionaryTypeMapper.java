package com.jiaxin.pda.mapper;

import com.jiaxin.pda.entity.dto.DictionaryDto;
import com.jiaxin.pda.entity.dto.DictionaryTypeDto;
import com.jiaxin.pda.entity.vo.DictionaryTypeVo;
import com.jiaxin.pda.entity.vo.DictionaryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典类型映射类
 * @author milo
 */
@Mapper
public interface DictionaryTypeMapper {

    /**
     * 插入字典类型
     * @param dictionaryTypeVo
     * @return
     */
    int insertDictionaryType(DictionaryTypeVo dictionaryTypeVo);

    /**
     * 查询最大的ID
     * @return
     */
    int queryMaxId();

    /**
     * 修改字典类型
     * @param dictionaryTypeVo
     * @return
     */
    int updateDictionaryType(DictionaryTypeVo dictionaryTypeVo);

    /**
     * 删除字典类型(逻辑删除)
     * @param dictionaryTypeVo
     * @return
     */
    int deleteDictionaryType(DictionaryTypeVo dictionaryTypeVo);

    /**
     * 根据Id查找字典类型对象
     * @param id
     * @return
     */
    DictionaryTypeVo findById(String id);

    /**
     * 分页查询字典类型
     * @param dictionaryTypeDto
     * @return
     */
    List<DictionaryTypeVo> queryDictionaryTypeByPage(DictionaryTypeDto dictionaryTypeDto);

    /**
     * 根据名称查询数据字典类型信息
     * @param dictionaryTypeName
     * @return
     */
    DictionaryTypeVo queryDictionaryTypeInfoByName(String dictionaryTypeName);

}
