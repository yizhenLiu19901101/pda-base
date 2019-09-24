package com.jiaxin.pda.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * 简易版操作对象类
 * @author milo
 */
@Data
public class SimpleOperateVo {
    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 创建人
     */
    private int createdBy;

    /**
     * 修改时间
     */
    private Date updatedTime;

    /**
     * 修改人
     */
    private int updatedBy;
}
