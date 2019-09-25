package com.jiaxin.pda.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 字典类
 * @author milo
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictionaryVo extends OperateVo{
    /**
     * 数据库主键
     */
    private String id;

    /**
     * 业务主键
     */
    private int uuid;

    /**
     * 条目名称
     */
    @NotNull(message = "条目名称")
    private String itemName;

    /**
     * 字典类型ID
     */
    private int dictionaryTypeId;

    /**
     * 次序
     */
    private int orderNo;
}
