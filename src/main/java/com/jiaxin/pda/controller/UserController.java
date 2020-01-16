package com.jiaxin.pda.controller;


import com.auth0.jwt.internal.org.apache.commons.lang3.ArrayUtils;
import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.ListPageVo;
import com.jiaxin.pda.entity.dto.UserDto;
import com.jiaxin.pda.entity.vo.*;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.enumeration.LoginStatusEnum;
import com.jiaxin.pda.exception.PDAException;
import com.jiaxin.pda.service.MenuService;
import com.jiaxin.pda.service.RoleService;
import com.jiaxin.pda.service.UserService;
import com.jiaxin.pda.swagger.note.RoleNote;
import com.jiaxin.pda.swagger.note.UserNote;
import com.jiaxin.pda.util.JWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户控制器类
 * @author milo
 */
@RestController
@Api(value = "user",tags = {"user_controller"})
@RequestMapping("/api/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    /**
     * 根据ID查找用户信息
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    @ApiOperation(value = "根据ID查找用户信息")
    public GeneralVo findById(@PathVariable("id") String id){
        try{
            logger.info("用户的ID为 {}",id);
            UserVo userVo = userService.findUserById(id);
            if(null == userVo){
                return new GeneralVo(ErrorListEnum.NOT_EXIST,null);
            }else{
                return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,userVo);
            }
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 根据token查找用户信息
     * @return
     */
    @GetMapping("/findByToken")
    @ApiOperation(value = "根据token查找用户信息")
    public GeneralVo findByToken(HttpServletRequest request, HttpServletResponse response){
        try{
            String userId = getCurrentId(request,response);
            UserVo userVo = userService.findUserById(userId);
            if(null == userVo){
                return new GeneralVo(ErrorListEnum.NOT_EXIST,null);
            }else{
                return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,userVo);
            }
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 插入用户信息
     * @param userVo
     * @return
     */
    @PutMapping("/registerUser")
    @ApiOperation(value = "注册用户",notes = UserNote.REGISTER_NOTE)
    @ApiImplicitParam(name = "userVo", value = UserNote.REGISTER_VALUE, required = true, dataType = "UserVo")
    public GeneralVo insertUser(HttpServletRequest request, HttpServletResponse response,@RequestBody UserVo userVo){
        try{
            logger.info("插入用户-参数,{}",userVo);
            //用户名不能为空/重复
            if(null == userVo.getUserName() || userVo.getUserName().trim().length() == 0){
                return new GeneralVo(ErrorListEnum.USERNAME_NOT_EMPTY,null);
            }else{
                //根据用户名查找用户信息
                UserVo queryResult = userService.findUserByName(userVo.getUserName());
                if(null != queryResult){
                    return new GeneralVo(ErrorListEnum.USERNAME_REPEAT,null);
                }
            }
            //密码不能为空
            if(null == userVo.getPassword() || userVo.getPassword().trim().length() == 0){
                return new GeneralVo(ErrorListEnum.PASSWORD_NOT_EMPTY,null);
            }
            //初始化创建人和修改人
            initOperateParam(request,response,userVo, Constant.CREATE_TYPE);
            //插入用户信息
            userService.insertUser(userVo);
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 修改用户信息
     * @param userVo
     * @return
     */
    @PutMapping("/updateUserInfo")
    @ApiImplicitParam(name = "userVo", value = UserNote.UPDATE_USER_NAME_VALUE, required = true, dataType = "UserVo")
    @ApiOperation(value = "修改用户信息",notes = UserNote.UPDATE_USER_NAME_NOTE)
    public GeneralVo updateUserInfo(HttpServletRequest request, HttpServletResponse response,@RequestBody @Valid UserVo userVo, BindingResult result){
        try{
            logger.info("修改用户-参数,{}",userVo);
            //用户名不能为空/重复
            if(null == userVo.getUserName() || userVo.getUserName().trim().length() == 0){
                return new GeneralVo(ErrorListEnum.USERNAME_NOT_EMPTY,null);
            }else{
                //根据用户名查找用户信息
                UserVo queryResult = userService.findUserByName(userVo.getUserName());
                if(null != queryResult && (!userVo.getId().equals(queryResult.getId()))){
                    return new GeneralVo(ErrorListEnum.USERNAME_REPEAT,null);
                }
            }
            //修改修改人
            initOperateParam(request,response,userVo,Constant.UPDATE_TYPE);
            //修改用户信息
            userService.updateUserInfo(userVo);
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 修改用户密码
     * @param userVo
     * @return
     */
    @PutMapping("/updateUserPassword")
    @ApiImplicitParam(name = "userVo", value = UserNote.UPDATE_USER_PASSWORD_VALUE, required = true, dataType = "UserVo")
    @ApiOperation(value = "修改密码",notes = UserNote.UPDATE_USER_PASSWORD_NOTE)
    public GeneralVo updateUserPassword(HttpServletRequest request, HttpServletResponse response,@RequestBody UserVo userVo){
        try{
            //修改修改人
            initOperateParam(request,response,userVo, Constant.UPDATE_TYPE);
            //修改用户密码
            userService.updateUserPassword(userVo);
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 删除用户信息
     * @param userVo
     * @return
     */
    @DeleteMapping("/deleteUser")
    @ApiImplicitParam(name = "userVo", value = UserNote.DELETE_USER_VALUE, required = true, dataType = "UserVo")
    @ApiOperation(value = "删除用户",notes = UserNote.DELETE_USER_NOTE)
    public GeneralVo deleteUser(HttpServletRequest request, HttpServletResponse response,@RequestBody UserVo userVo){
        try{
            UserVo checkResult = userService.findUserById(userVo.getId());
            if(null == checkResult || checkResult.isDeleteFlag()){
                return new GeneralVo(ErrorListEnum.NOT_EXIST,null);
            }
            //修改修改人
            initOperateParam(request,response,userVo, Constant.UPDATE_TYPE);
            int operateResult = userService.deleteUserInfo(userVo);
            if(Constant.OPERATE_FAIL == operateResult){
                return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
            }
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 用户登陆
     * @param userVo
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登陆",notes = UserNote.REGISTER_NOTE)
    @ApiImplicitParam(name = "userVo", value = UserNote.REGISTER_VALUE, required = true, dataType = "UserVo")
    public GeneralVo login(@RequestBody UserVo userVo){
        try{
            String result = userService.userLogin(userVo);
            if(null != result && (!result.equals(LoginStatusEnum.USER_NOT_EXIST.getValue()) && !result.equals(LoginStatusEnum.PASSWORD_ERROR.getValue()))){
                return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,result);
            }else{
                return new GeneralVo(ErrorListEnum.OPERATE_FAIL.getKey(),result,null);
            }
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 用户退出
     * @param token
     * @return0
     */
    @GetMapping("/logout")
    @ApiOperation(value = "用户退出")
    public GeneralVo logout(@RequestHeader("token") String token){
        try{
            int result = userService.userLogout(token);
            if(result == Constant.OPERATE_SUCCESS){
                return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
            }else{
                return new GeneralVo(ErrorListEnum.INVALID_TOKEN,null);
            }
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 分页查询用户信息
     * @param userDto 查询条件
     * @return 响应结果
     */
    @PostMapping("/queryUserListByPage")
    @ApiImplicitParam(name = "userDto", value = UserNote.QUERY_BY_PAGE_VALUE, required = true, dataType = "UserDto")
    @ApiOperation(value = "分页查询用户信息",notes = UserNote.QUERY_BY_PAGE_NOTE)
    public ListPageVo queryUserListByPage(@RequestBody UserDto userDto){
        try{
            userDto.build();
            return new ListPageVo(ErrorListEnum.OPERATE_SUCCESS,userService.queryUserListByPage(userDto),userDto.getPageInfo());
        }catch(Exception e){
            e.printStackTrace();
            return new ListPageVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null,null);
        }
    }

    /**
     * 插入用户角色信息
     * @param userPrivilegeVo 用户角色对象
     * @return 响应结果
     */
    @PutMapping("/insertUserRole")
    @ApiImplicitParam(name = "userPrivilegeVo", value = UserNote.GIVE_USER_ROLE_VALUE, required = true, dataType = "UserPrivilegeVo")
    @ApiOperation(value = "给用户赋予角色",notes = UserNote.GIVE_USER_ROLE_NOTE)
    public GeneralVo insertUserRole(HttpServletRequest request, HttpServletResponse response,@RequestBody UserPrivilegeVo userPrivilegeVo){
        try{
            UserPrivilegeVo queryResult = userService.selectByUserId(userPrivilegeVo.getUserId());
            if(null == queryResult || queryResult.isDeleteFlag()){
                //初始化创建人和修改人
                initOperateParam(request,response,userPrivilegeVo, Constant.CREATE_TYPE);
                //插入用户角色信息
                userService.insertUserRole(userPrivilegeVo);
                return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
            }else{
                return new GeneralVo(ErrorListEnum.USER_ROLE_EXIST,null);
            }
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 删除用户角色信息
     * @param userPrivilegeVo 用户角色对象
     * @return 响应结果
     */
    @ApiImplicitParam(name = "userPrivilegeVo", value = UserNote.GIVE_USER_ROLE_VALUE, required = true, dataType = "UserPrivilegeVo")
    @ApiOperation(value = "取消用户的角色",notes = UserNote.GIVE_USER_ROLE_NOTE)
    @DeleteMapping(value = "/deleteUserRole")
    public GeneralVo deleteUserRole(HttpServletRequest request, HttpServletResponse response,@RequestBody UserPrivilegeVo userPrivilegeVo){
        try{
            //初始化创建人和修改人
            initOperateParam(request,response,userPrivilegeVo, Constant.UPDATE_TYPE);
            //删除用户角色信息
            userService.deleteUserRole(userPrivilegeVo);
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 根据用户ID查询角色信息
     * @param userId 用户ID
     * @return 响应结果
     */
    @ApiOperation(value = "根据用户ID查询角色信息")
    @GetMapping("/queryRoleByUserId/{userId}")
    public GeneralVo queryRoleByUserId(@PathVariable("userId") int  userId){
        try{
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS, userService.selectByUserId(userId));
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 根据用户ID查询授权的菜单列表
     */
    @ApiOperation(value = "根据token查看授权的菜单列表")
    @GetMapping("/queryUserPrivileges/{parentMenuId}")
    public GeneralVo queryUserPrivileges(HttpServletRequest request, HttpServletResponse response,@PathVariable("parentMenuId") int parentMenuId) {
        try {
            int userId = getCurrentUserId(request,response);
            UserPrivilegeVo userPrivilegeVo = userService.selectByUserId(userId);
            if (null != userPrivilegeVo) {
                List<RolePrivilegeVo> rolePrivilegeVoList = roleService.selectByRoleId(Integer.valueOf(userPrivilegeVo.getRoleId()));
                if (null != rolePrivilegeVoList && Constant.EMPTY_INTEGER_VALUE < rolePrivilegeVoList.size()) {
                    List<String> menuIdList = new ArrayList<>();
                    for (RolePrivilegeVo rolePrivilegeVo : rolePrivilegeVoList) {
                        int menuId = Integer.valueOf(rolePrivilegeVo.getMenuId()).intValue();
                        MenuVo menuVo = menuService.selectMenuById(menuId);
                        if(null != menuVo && menuVo.getParentMenuId() == parentMenuId){
                            menuIdList.add(rolePrivilegeVo.getMenuId());
                        }
                    }
                    List<MenuVo> menuVoList = menuService.queryMenuListByMenuIdList(menuIdList);
                    return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS, menuVoList);
                }
            }
            return new GeneralVo(ErrorListEnum.OPERATE_FAIL, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }
}
