package com.jiaxin.pda.mapper;

import com.jiaxin.pda.entity.vo.RolePrivilegeVo;
import com.jiaxin.pda.entity.vo.RoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限映射类
 * @author milo
 */
@Mapper
public interface RolePrivilegeMapper {

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

    /**
     * 根据菜单ID和角色ID查询菜单是否得到授权
     * @param menuId
     * @param roleId
     * @return
     */
    int queryMenuAuthorityByCondition(@Param("menuId") String menuId,@Param("roleId") String roleId);
}
