package com.jiaxin.pda.controller;


import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.ListPageVo;
import com.jiaxin.pda.entity.dto.UserDto;
import com.jiaxin.pda.entity.vo.*;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

/**
 * 用户控制器类
 * @author milo
 */
@RestController
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    /**
     * 根据ID查找用户信息
     * @param id
     * @return
     */
    @GetMapping("/user/findById/{id}")
    public GeneralVo findById(@PathVariable("id") String id){
        logger.info("用户的ID为 {}",id);
        UserVo userVo = userService.findUserById(id);
        if(null == userVo){
            return new GeneralVo(ErrorListEnum.NOT_EXIST,null);
        }else{
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,userVo);
        }
    }

    /**
     * 插入用户信息
     * @param userVo
     * @return
     */
    @PutMapping("/user/insertUser")
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
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }
    }

    /**
     * 修改用户名
     * @param userVo
     * @return
     */
    @PutMapping("/user/updateUserName")
    public GeneralVo updateUserName(HttpServletRequest request, HttpServletResponse response,@RequestBody @Valid UserVo userVo, BindingResult result){
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
        //修改用户名
        userService.updateUserName(userVo);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 修改用户密码
     * @param userVo
     * @return
     */
    @PutMapping("/user/updateUserPassword")
    public GeneralVo updateUserPassword(HttpServletRequest request, HttpServletResponse response,@RequestBody UserVo userVo){
        try{
            //修改修改人
            initOperateParam(request,response,userVo, Constant.UPDATE_TYPE);
            //修改用户密码
            userService.updateUserPassword(userVo);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 删除用户信息
     * @param userVo
     * @return
     */
    @DeleteMapping("/user/deleteUser")
    public GeneralVo deleteUser(HttpServletRequest request, HttpServletResponse response,@RequestBody UserVo userVo){
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
    }

    /**
     * 用户登陆
     * @param userVo
     * @return
     */
    @PostMapping("/user/login")
    public GeneralVo login(@RequestBody UserVo userVo){
        try{
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,userService.userLogin(userVo));
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 用户退出
     * @param userTokenVo
     * @return0
     */
    @PostMapping("/user/logout")
    public GeneralVo logout(@RequestBody UserTokenVo userTokenVo){
        int result = userService.userLogout(userTokenVo);
        if(result == Constant.OPERATE_SUCCESS){
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }else{
            return new GeneralVo(ErrorListEnum.INVALID_TOKEN,null);
        }
    }

    /**
     * 分页查询用户信息
     * @param userDto 查询条件
     * @return 响应结果
     */
    @PostMapping("/user/queryUserListByPage")
    public ListPageVo queryUserListByPage(@RequestBody UserDto userDto){
        userDto.build();
        return new ListPageVo(ErrorListEnum.OPERATE_SUCCESS,userService.queryUserListByPage(userDto),userDto.getPageInfo());
    }

    /**
     * 插入用户角色信息
     * @param userPrivilegeVo 用户角色对象
     * @return 响应结果
     */
    @PutMapping("/user/insertUserRole")
    public GeneralVo insertUserRole(HttpServletRequest request, HttpServletResponse response,@RequestBody UserPrivilegeVo userPrivilegeVo){
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
    }

    /**
     * 删除用户角色信息
     * @param userPrivilegeVo 用户角色对象
     * @return 响应结果
     */
    @DeleteMapping("/user/deleteUserRole")
    public GeneralVo deleteUserRole(HttpServletRequest request, HttpServletResponse response,@RequestBody UserPrivilegeVo userPrivilegeVo){
        //初始化创建人和修改人
        initOperateParam(request,response,userPrivilegeVo, Constant.UPDATE_TYPE);
        //删除用户角色信息
        userService.deleteUserRole(userPrivilegeVo);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 根据用户ID查询角色信息
     * @param userId 用户ID
     * @return 响应结果
     */
    @GetMapping("/user/queryRoleByUserId/{userId}")
    public GeneralVo queryRoleByUserId(@PathVariable("userId") int  userId){
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS, userService.selectByUserId(userId));
    }
}
