package com.jiaxin.pda.entity.dto;

import com.jiaxin.pda.entity.PageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 字典类型数据传输类
 * @author milo
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DictionaryTypeDto extends PageEntity {

    /**
     * 字典类型名称
     */
    private String typeName;
}
