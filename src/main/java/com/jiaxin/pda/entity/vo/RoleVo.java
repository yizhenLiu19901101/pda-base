package com.jiaxin.pda.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 角色类
 * @author milo
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class RoleVo extends OperateVo implements Serializable {
    /**
     * 数据库主键
     */
    private String id;

    /**
     * 角色ID
     */
    private int roleId;

    /**
     * 角色名称
     */
    private String roleName;
}
