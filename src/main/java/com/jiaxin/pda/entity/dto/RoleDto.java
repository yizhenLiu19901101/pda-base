package com.jiaxin.pda.entity.dto;

import com.jiaxin.pda.entity.PageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色数据传输类
 * @author milo
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleDto extends PageEntity {

    /**
     * 数据库主键
     */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;
}
