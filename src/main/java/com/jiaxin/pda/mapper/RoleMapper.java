package com.jiaxin.pda.mapper;

import com.jiaxin.pda.entity.dto.RoleDto;
import com.jiaxin.pda.entity.vo.MenuVo;
import com.jiaxin.pda.entity.vo.RoleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色映射类
 * @author milo
 */
@Mapper
public interface RoleMapper {
    /**
     * 查询角色表中最大的ID
     * @return
     */
    Integer selectMaxRoleId();

    /**
     * 插入角色信息
     * @param roleVo
     * @return
     */
    int insertRole(RoleVo roleVo);

    /**
     * 删除角色
     * @param roleVo
     * @return
     */
    int deleteRole(RoleVo roleVo);

    /**
     * 根据ID查询角色信息
     * @param id
     * @return
     */
    RoleVo findRoleById(String id);

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
}
