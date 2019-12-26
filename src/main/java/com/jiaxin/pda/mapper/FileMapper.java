package com.jiaxin.pda.mapper;

import com.jiaxin.pda.entity.dto.DictionaryDto;
import com.jiaxin.pda.entity.vo.DictionaryVo;
import com.jiaxin.pda.entity.vo.FileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文件映射接口
 * @author milo
 */
@Mapper
public interface FileMapper {

    /**
     * 插入字典项
     * @param fileVo 字典对象
     * @return 返回结果
     */
    int insertFile(FileVo fileVo);

    /**
     * 查询字典表中最大的ID
     * @return
     */
    Integer selectMaxUuid();

}
