package com.jiaxin.pda.entity.dto;

import com.jiaxin.pda.entity.PageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DictionaryDto extends PageEntity {

    /**
     * 字典项名称
     */
    private String itemName;
}
