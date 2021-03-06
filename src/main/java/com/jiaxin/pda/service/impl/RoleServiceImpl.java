package com.jiaxin.pda.service.impl;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.dto.RoleDto;
import com.jiaxin.pda.entity.vo.RolePrivilegeVo;
import com.jiaxin.pda.entity.vo.RoleVo;
import com.jiaxin.pda.mapper.RoleMapper;
import com.jiaxin.pda.mapper.RolePrivilegeMapper;
import com.jiaxin.pda.service.IRoleService;
import com.jiaxin.pda.util.GenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色业务实现类
 * @author milo
 */
@Service
@Slf4j
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePrivilegeMapper rolePrivilegeMapper;

    @Override
    public RoleVo queryById(String id) {
        return roleMapper.queryById(id);
    }

    @Override
    public int insertRole(RoleVo roleVo) {
        roleVo.setId(GenerateUtil.generateRandomString());
        roleVo.setRoleId(roleMapper.selectMaxRoleId()+ Constant.INCREASE_PACE);
        log.info("插入角色，入参为-{}",roleVo);
        return roleMapper.insertRole(roleVo);
    }

    @Override
    public int deleteRole(RoleVo  roleVo) {
        roleVo.setDeleteFlag(true);
        log.info("删除角色，入参为-{}",roleVo);
        return roleMapper.deleteRole(roleVo);
    }

    @Override
    public int updateRoleName(RoleVo roleVo) {
        log.info("修改角色名称，入参为-{}",roleVo);
        return roleMapper.updateRoleName(roleVo);
    }

    @Override
    public List<RoleVo> selectRoleByPage(RoleDto roleDto) {
        return roleMapper.selectRoleByPage(roleDto);
    }

    @Override
    public int insertRoleMenu(RolePrivilegeVo rolePrivilegeVo) {
        rolePrivilegeVo.setId(GenerateUtil.generateRandomString());
        return rolePrivilegeMapper.insertRoleMenu(rolePrivilegeVo);
    }

    @Override
    public int deleteRoleMenu(RolePrivilegeVo rolePrivilegeVo) {
        return rolePrivilegeMapper.deleteRoleMenu(rolePrivilegeVo);
    }

    @Override
    public List<RolePrivilegeVo> selectByRoleId(Integer roleId) {
        return rolePrivilegeMapper.selectByRoleId(roleId);
    }

    @Override
    public RoleVo selectByRoleName(String roleName) {
        return roleMapper.selectByRoleName(roleName);
    }

    @Override
    public int queryMenuAuthorityByCondition(String menuId, String roleId) {
        return rolePrivilegeMapper.queryMenuAuthorityByCondition(menuId,roleId);
    }
}
