package com.jiaxin.pda.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户权限类
 * @author milo
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserPrivilegeVo extends OperateVo{

    /**
     * 数据库主键
     */
    private String id;

    /**
     * 菜单ID
     */
    private int userId;

    /**
     * 角色ID
     */
    private String roleId;
}
