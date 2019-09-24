package com.jiaxin.pda.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 数据字典类
 * @author milo
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DictionaryTypeVo extends OperateVo{
    /**
     * 数据库主键
     */
    private String id;

    /**
     * 业务主键
     */
    private int uuid;

    /**
     * 字典类型名称
     */
    @NotNull(message = "字典类型名称")
    private String typeName;
}
