package com.jiaxin.pda.service.impl;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.dto.RoleDto;
import com.jiaxin.pda.entity.vo.RolePrivilegeVo;
import com.jiaxin.pda.entity.vo.RoleVo;
import com.jiaxin.pda.mapper.RoleMapper;
import com.jiaxin.pda.mapper.RolePrivilegeMapper;
import com.jiaxin.pda.service.RoleService;
import com.jiaxin.pda.util.GenerateUtil;
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
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
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
        logger.info("插入角色，入参为-{}",roleVo);
        return roleMapper.insertRole(roleVo);
    }

    @Override
    public int deleteRole(RoleVo  roleVo) {
        roleVo.setDeleteFlag(true);
        logger.info("删除角色，入参为-{}",roleVo);
        return roleMapper.deleteRole(roleVo);
    }

    @Override
    public int updateRoleName(RoleVo roleVo) {
        logger.info("修改角色名称，入参为-{}",roleVo);
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


}
