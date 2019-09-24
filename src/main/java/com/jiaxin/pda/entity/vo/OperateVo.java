package com.jiaxin.pda.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 操作对象类
 * @author milo
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class OperateVo extends SimpleOperateVo{
    /**
     * 版本
     */
    private int reversion;

    /**
     * 删除标志
     */
    private boolean deleteFlag;
}
