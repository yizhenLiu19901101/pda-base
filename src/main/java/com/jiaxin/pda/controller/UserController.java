package com.jiaxin.pda.controller;


import com.jiaxin.pda.entity.ListPageVo;
import com.jiaxin.pda.entity.dto.UserDto;
import com.jiaxin.pda.entity.vo.GeneralVo;
import com.jiaxin.pda.entity.vo.UserPrivilegeVo;
import com.jiaxin.pda.entity.vo.UserTokenVo;
import com.jiaxin.pda.entity.vo.UserVo;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.service.UserService;
import com.jiaxin.pda.util.JWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

/**
 * 用户控制器类
 * @author milo
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 根据ID查找用户信息
     * @param id
     * @return
     */
    @GetMapping("/user/findById/{id}")
    public GeneralVo findById(@RequestHeader("token") String token,@PathVariable("id") String id){
        String userId = JWT.unsign(token,String.class);
        logger.info("userId = " + userId);
        if(null == userId){
            return new GeneralVo(ErrorListEnum.NOT_LOGIN,null);
        }
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,userService.findUserById(id));
    }

    /**
     * 插入用户信息
     * @param userVo
     * @return
     */
    @PutMapping("/user/insertUser")
    public GeneralVo insertUser(@RequestBody UserVo userVo){
        try{
            //插入用户信息
            userService.insertUser(userVo);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 修改用户名
     * @param userVo
     * @return
     */
    @PutMapping("/user/updateUserName")
    public GeneralVo updateUserName(@RequestBody @Valid UserVo userVo, BindingResult result){
        logger.info("修改用户-参数,{}",userVo);
        //入参校验
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            logger.error("修改用户-参数:{}", fieldError.getDefaultMessage());
            return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }
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
    public GeneralVo updateUserPassword(@RequestBody UserVo userVo){
        try{
            //修改用户密码
            userService.updateUserPassword(userVo);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 根据ID删除用户信息
     * @param id
     * @return
     */
    @DeleteMapping("/user/deleteById/{id}")
    public GeneralVo deleteById(@PathVariable("id") String id){
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,userService.deleteUserInfo(id));
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
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,userService.userLogout(userTokenVo));
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
     * 插入用户权限信息
     * @param userPrivilegeVo 用户权限对象
     * @return 响应结果
     */
    @PutMapping("/user/insertUserPrivilege")
    public GeneralVo insertUser(@RequestBody UserPrivilegeVo userPrivilegeVo){
        //插入用户权限信息
        userService.insertUserRole(userPrivilegeVo);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 删除用户权限信息
     * @param userPrivilegeVo 用户权限对象
     * @return 响应结果
     */
    @DeleteMapping("/user/deleteUserPrivilege")
    public GeneralVo deleteUserPrivilege(@RequestBody UserPrivilegeVo userPrivilegeVo){
        //删除用户权限信息
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
