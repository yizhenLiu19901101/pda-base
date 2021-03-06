package com.jiaxin.pda.service;

import com.jiaxin.pda.entity.dto.UserDto;
import com.jiaxin.pda.entity.vo.UserPrivilegeVo;
import com.jiaxin.pda.entity.vo.UserTokenVo;
import com.jiaxin.pda.entity.vo.UserVo;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 用户业务类
 * @author milo
 */
public interface IUserService {

    /**
     * 根据ID查找用户对象
     * @param id
     * @return
     */
    UserVo findUserById(String id);

    /**
     * 插入用户信息
     *
     * @param userVo 用户信息
     * @throws NoSuchAlgorithmException 算法不存在异常
     * @return Integer 操作结果
     */
    Integer insertUser(UserVo userVo) throws NoSuchAlgorithmException;

    /**
     * 删除用户信息
     * @param userVo
     * @return
     */
    Integer deleteUserInfo(UserVo userVo);

    /**
     * 修改用户信息
     * @param userVo
     * @return
     */
    Integer updateUserInfo(UserVo userVo) ;

    /**
     * 修改用密码
     * @param userVo
     * @return
     */
    Integer updateUserPassword(UserVo userVo) throws NoSuchAlgorithmException;

    /**
     * 用户登陆
     * @param userVo
     * @return
     */
    String userLogin(UserVo userVo) throws NoSuchAlgorithmException;

    /**
     * 用户退出
     * @param token
     * @return
     */
    Integer userLogout(String token);

    /**
     * 分页查询用户信息
     * @param userDto
     * @return
     */
    List<UserVo> queryUserListByPage(UserDto userDto);

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

    /**
     * 根据用户ID查找对应的token
     * @param userId
     * @return
     */
    UserTokenVo findUserToken(String userId);

    /**
     * 根据用户名查找用户信息
     * @param userName
     * @return
     */
    UserVo findUserByName(String userName);
}
