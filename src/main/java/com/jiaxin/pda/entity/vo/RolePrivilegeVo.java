package com.jiaxin.pda.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色权限类
 * @author milo
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class RolePrivilegeVo extends OperateVo{

    /**
     * 数据库主键
     */
    private String id;

    /**
     * 菜单ID
     */
    private String menuId;

    /**
     * 角色ID
     */
    private String roleId;
}
