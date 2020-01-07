package com.jiaxin.pda.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;

    /**
     * 创建人
     */
    private int createdBy;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedTime;

    /**
     * 修改人
     */
    private int updatedBy;
}
