package com.jiaxin.pda.mapper;

import com.jiaxin.pda.entity.vo.DictionaryVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典映射接口
 * @author milo
 */
@Mapper
public interface DictionaryMapper {

    /**
     * 插入字典项
     * @param dictionaryVo 字典对象
     * @return 返回结果
     */
    int insertDictionaryItem(DictionaryVo dictionaryVo);

    /**
     * 查询字典表中最大的ID
     * @return
     */
    Integer selectMaxUuid();

    /**
     * 修改字典项
     * @param dictionaryVo
     * @return
     */
    int updateDictionaryItem(DictionaryVo dictionaryVo);

    /**
     * 删除字典项
     * @param dictionaryVo
     * @return
     */
    int deleteDictionaryItem(DictionaryVo dictionaryVo);

    /**
     * 根据ID查找字典项
     * @param id
     * @return
     */
    DictionaryVo findById(String id);
}
