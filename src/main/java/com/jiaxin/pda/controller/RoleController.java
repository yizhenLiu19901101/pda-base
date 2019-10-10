package com.jiaxin.pda.controller;

import com.jiaxin.pda.entity.ListPageVo;
import com.jiaxin.pda.entity.dto.RoleDto;
import com.jiaxin.pda.entity.vo.GeneralVo;
import com.jiaxin.pda.entity.vo.RolePrivilegeVo;
import com.jiaxin.pda.entity.vo.RoleVo;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * 角色控制器类
 * @author milo
 */
@RestController
public class RoleController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    /**
     * 角色业务类
     */
    @Autowired
    private RoleService roleService;

    /**
     * 插入角色信息
     * @param roleVo 角色对象
     * @param result 校验对象
     * @return 响应结果
     */
    @PutMapping("/role/insertRole")
    public GeneralVo insertRole(@RequestBody @Valid RoleVo roleVo, BindingResult result){
        if(result.hasErrors()){
            FieldError fieldError = result.getFieldError();
            logger.info("插入角色-参数错误: " + fieldError.getDefaultMessage());
            return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }
        //插入角色
        roleService.insertRole(roleVo);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 修改角色名称
     * @param roleVo 角色对象
     * @param result 校验对象
     * @return 响应结果
     */
    @PutMapping("/role/updateRoleName")
    public GeneralVo updateRoleName(@RequestBody @Valid RoleVo roleVo, BindingResult result){
        if(result.hasErrors()){
            FieldError fieldError = result.getFieldError();
            logger.info("修改角色名称-参数错误: " + fieldError.getDefaultMessage());
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }
        //修改角色名称
        roleService.updateRoleName(roleVo);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 删除角色
     * @param id 角色ID
     * @return 响应结果
     */
    @DeleteMapping("/role/deleteRole/{id}")
    public GeneralVo deleteRole(@PathVariable("id") String id){
        //修改角色名称
        roleService.deleteRole(id);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 按照名称分页查找角色信息
     * @param roleDto 角色对象
     * @return 响应结果
     */
    @PostMapping("/role/selectRoleByPage")
    public ListPageVo selectRoleByPage(@RequestBody RoleDto roleDto){
        roleDto.build();
        return new ListPageVo(ErrorListEnum.OPERATE_SUCCESS,roleService.selectRoleByPage(roleDto),roleDto.getPageInfo());
    }

    /**
     * 给角色授权
     * @param rolePrivilegeVo 角色权限对象
     * @return 响应结果
     */
    @PostMapping("/role/givePrivilege")
    public GeneralVo givePrivilege(@RequestBody RolePrivilegeVo rolePrivilegeVo){
        roleService.insertRoleMenu(rolePrivilegeVo);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 给角色取消授权
     * @param rolePrivilegeVo 角色权限对象
     * @return 响应结果
     */
    @DeleteMapping("/role/cancelPrivilege")
    public GeneralVo cancelPrivilege(@RequestBody RolePrivilegeVo rolePrivilegeVo){
        roleService.deleteRoleMenu(rolePrivilegeVo);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 查询一个角色的权限
     * @param roleId 角色Id
     * @return 响应结果
     */
    @GetMapping("/role/queryPrivilegeByRoleId/{roleId}")
    public GeneralVo queryPrivilegeByRoleId(@PathVariable("roleId") Integer roleId){
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,roleService.selectByRoleId(roleId));
    }
}
