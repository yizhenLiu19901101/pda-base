package com.jiaxin.pda.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户token对象
 * @author milo
 */
@Data
public class UserTokenVo implements Serializable {

    /**
     * 数据库主键
     */
    private int id;

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 用户token
     */
    private String userToken;

    /**
     * 删除标志
     */
    private boolean deleteFlag;

    /**
     * 版本
     */
    private int reversion;

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
