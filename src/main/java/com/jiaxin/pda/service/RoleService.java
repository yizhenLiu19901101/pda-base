package com.jiaxin.pda.service;

import com.jiaxin.pda.entity.dto.RoleDto;
import com.jiaxin.pda.entity.vo.RolePrivilegeVo;
import com.jiaxin.pda.entity.vo.RoleVo;

import java.util.List;

/**
 * 角色业务类
 * @author milo
 */
public interface RoleService {

    /**
     * 插入角色信息
     * @param roleVo
     * @return
     */
    int insertRole(RoleVo roleVo);

    /**
     * 删除角色
     * @param id
     * @return
     */
    int deleteRole(String id);

    /**
     * 修改角色名称
     * @param roleVo
     * @return
     */
    int updateRoleName(RoleVo roleVo);

    /**
     * 按照条件分页查询角色对象
     * @param roleDto
     * @return
     */
    List<RoleVo> selectRoleByPage(RoleDto roleDto);

    /**
     * 插入角色-菜单关系
     * @param  rolePrivilegeVo 角色权限对象
     * @return 是否成功
     */
    int insertRoleMenu(RolePrivilegeVo rolePrivilegeVo);

    /**
     * 删除角色-菜单关系
     * @param rolePrivilegeVo 角色权限对象
     * @return 是否成功
     */
    int deleteRoleMenu(RolePrivilegeVo rolePrivilegeVo);

    /**
     * 根据角色查询对应的菜单列表
     * @param roleId 角色ID
     * @return 角色权限对象
     */
    List<RolePrivilegeVo> selectByRoleId(Integer roleId);
}
