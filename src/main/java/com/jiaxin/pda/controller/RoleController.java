package com.jiaxin.pda.controller;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.ListPageVo;
import com.jiaxin.pda.entity.dto.RoleDto;
import com.jiaxin.pda.entity.vo.GeneralVo;
import com.jiaxin.pda.entity.vo.RolePrivilegeVo;
import com.jiaxin.pda.entity.vo.RoleVo;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.service.IRoleService;
import com.jiaxin.pda.swagger.note.RoleNote;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 角色控制器类
 * @author milo
 */
@RestController
@Api(value = "role",tags = {"role_controller"})
@RequestMapping(value = "/role")
public class RoleController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    /**
     * 角色业务类
     */
    @Autowired
    private IRoleService roleService;

    /**
     * 插入角色信息
     * @param roleVo 角色对象
     * @param result 校验对象
     * @return 响应结果
     */
    @PutMapping("/insertRole")
    @ApiImplicitParam(name = "roleVo", value = RoleNote.INSERT_VALUE, required = true, dataType = "RoleVo")
    @ApiOperation(value = "插入角色",notes = RoleNote.INSERT_NOTE)
    public GeneralVo insertRole(HttpServletRequest request, HttpServletResponse response,@RequestBody RoleVo roleVo, BindingResult result){
        try{
            if(null == roleVo.getRoleName() || roleVo.getRoleName().trim().length() == 0){
                return new GeneralVo(ErrorListEnum.ROLE_NAME_NOT_EXIST,null);
            }else{
                RoleVo queryResult = roleService.selectByRoleName(roleVo.getRoleName());
                if(null != queryResult && (!queryResult.isDeleteFlag())){
                    return new GeneralVo(ErrorListEnum.ROLE_NAME_REPEAT,null);
                }
            }
            //初始化创建参数
            initOperateParam(request,response,roleVo, Constant.CREATE_TYPE);
            //插入角色
            roleService.insertRole(roleVo);
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 修改角色名称
     * @param roleVo 角色对象
     * @param result 校验对象
     * @return 响应结果
     */
    @PutMapping("/updateRoleName")
    @ApiImplicitParam(name = "roleVo", value = RoleNote.UPDATE_VALUE, required = true, dataType = "RoleVo")
    @ApiOperation(value = "修改角色姓名",notes = RoleNote.UPDATE_NOTE)
    public GeneralVo updateRoleName(HttpServletRequest request, HttpServletResponse response,@RequestBody @Valid RoleVo roleVo, BindingResult result){
        try{
            if(null == roleVo.getRoleName() || roleVo.getRoleName().trim().length() == 0){
                return new GeneralVo(ErrorListEnum.ROLE_NAME_NOT_EXIST,null);
            }else{
                RoleVo queryResult = roleService.selectByRoleName(roleVo.getRoleName());
                if(null != queryResult && (!queryResult.isDeleteFlag()) && (!queryResult.getId().equals(roleVo.getId()))){
                    return new GeneralVo(ErrorListEnum.ROLE_NAME_REPEAT,null);
                }
            }
            //初始化创建参数
            initOperateParam(request,response,roleVo, Constant.UPDATE_TYPE);
            //修改角色名称
            roleService.updateRoleName(roleVo);
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 删除角色
     * @param roleVo
     * @return 响应结果
     */
    @DeleteMapping("/deleteRole")
    @ApiImplicitParam(name = "roleVo", value = RoleNote.DELETE_VALUE, required = true, dataType = "RoleVo")
    @ApiOperation(value = "删除角色",notes = RoleNote.DELETE_NOTE)
    public GeneralVo deleteRole(HttpServletRequest request, HttpServletResponse response,@RequestBody RoleVo roleVo){
        try{
            RoleVo queryResult = roleService.queryById(roleVo.getId());
            if(null == queryResult ||(null != queryResult && queryResult.isDeleteFlag())){
                return new GeneralVo(ErrorListEnum.ROLE_NOT_EXIST,null);
            }
            //初始化创建参数
            initOperateParam(request,response,roleVo, Constant.UPDATE_TYPE);
            //删除角色名称
            int result = roleService.deleteRole(roleVo);
            return Constant.OPERATE_SUCCESS == result?new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null):new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 按照名称分页查找角色信息
     * @param roleDto 角色对象
     * @return 响应结果
     */
    @PostMapping(value = "/selectRoleByPage")
    @ApiImplicitParam(name = "roleDto", value = RoleNote.QUERY_BY_PAGE_VALUE, required = true, dataType = "RoleDto")
    @ApiOperation(value = "分页查找角色信息",notes = RoleNote.QUERY_BY_PAGE_NOTE)
    public ListPageVo selectRoleByPage(@RequestBody RoleDto roleDto){
        try{
            roleDto.build();
            return new ListPageVo(ErrorListEnum.OPERATE_SUCCESS,roleService.selectRoleByPage(roleDto),roleDto.getPageInfo());
        }catch(Exception e){
            e.printStackTrace();
            return new ListPageVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null,null);
        }
    }

    /**
     * 给角色授权
     * @param rolePrivilegeVo 角色权限对象
     * @return 响应结果
     */
    @PostMapping("/givePrivilege")
    @ApiImplicitParam(name = "rolePrivilegeVo", value = RoleNote.GIVE_PRIVILEGE_VALUE, required = true, dataType = "RolePrivilegeVo")
    @ApiOperation(value = "角色授权",notes = RoleNote.GIVE_PRIVILEGE_NOTE)
    public GeneralVo givePrivilege(HttpServletRequest request, HttpServletResponse response,@RequestBody RolePrivilegeVo rolePrivilegeVo){
        try{
            int result = roleService.queryMenuAuthorityByCondition(rolePrivilegeVo.getMenuId(),rolePrivilegeVo.getRoleId());
            if(Constant.EMPTY_INTEGER_VALUE == result){
                //初始化创建参数
                initSimpleOperateParam(request,response,rolePrivilegeVo, Constant.UPDATE_TYPE);
                roleService.insertRoleMenu(rolePrivilegeVo);
                return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
            }else{
                return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
            }
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 给角色取消授权
     * @param rolePrivilegeVo 角色权限对象
     * @return 响应结果
     */
    @DeleteMapping("/cancelPrivilege")
    @ApiImplicitParam(name = "rolePrivilegeVo", value = RoleNote.GIVE_PRIVILEGE_VALUE, required = true, dataType = "RolePrivilegeVo")
    @ApiOperation(value = "角色取消授权",notes = RoleNote.GIVE_PRIVILEGE_NOTE)
    public GeneralVo cancelPrivilege(HttpServletRequest request, HttpServletResponse response,@RequestBody RolePrivilegeVo rolePrivilegeVo){
        try{
            //初始化创建参数
            initSimpleOperateParam(request,response,rolePrivilegeVo, Constant.UPDATE_TYPE);
            int result = roleService.deleteRoleMenu(rolePrivilegeVo);
            return Constant.OPERATE_SUCCESS == result?new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null):new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 查询一个角色的权限
     * @param roleId 角色Id
     * @return 响应结果
     */
    @ApiOperation(value = "根据角色信息查询权限信息")
    @GetMapping("/queryPrivilegeByRoleId/{roleId}")
    public GeneralVo queryPrivilegeByRoleId(@PathVariable("roleId") Integer roleId){
        try{
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,roleService.selectByRoleId(roleId));
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }
}
