package com.jiaxin.pda.mapper;


import com.jiaxin.pda.entity.vo.UserPrivilegeVo;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户权限映射类
 * @author milo
 */
@Mapper
public interface UserPrivilegeMapper {
    /**
     * 插入角色-用户关系
     * @param  userPrivilegeVo 用户权限对象
     * @return 是否成功
     */
    int insertUserRole(UserPrivilegeVo userPrivilegeVo);

    /**
     * 删除角色-菜单关系
     * @param userPrivilegeVo 用户权限对象
     * @return 是否成功
     */
    int deleteUserRole(UserPrivilegeVo userPrivilegeVo);

    /**
     * 根据用户ID查询对应的角色
     * @param userId 角色ID
     * @return 角色权限对象
     */
    UserPrivilegeVo selectByUserId(Integer userId);
}
