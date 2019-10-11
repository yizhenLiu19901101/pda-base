package com.jiaxin.pda.mapper;

import com.jiaxin.pda.entity.dto.UserDto;
import com.jiaxin.pda.entity.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户映射类
 * @author milo
 */
@Mapper
public interface UserMapper {
    /**
     * 根据ID查找用户对象
     * @param id
     * @return
     */
    UserVo findUserById(String id);

    /**
     * 插入用户信息
     * @param userVo
     * @return
     */
    Integer insertUser(UserVo userVo);

    /**
     * 查询用户表中最大的用户ID
     * @return
     */
    Integer selectMaxUserId();

    /**
     * 删除用户信息
     * @param userVo
     * @return
     */
    Integer deleteUserInfo(UserVo userVo);

    /**
     * 修改用户名
     * @param userVo
     * @return
     */
    Integer updateUserName(UserVo userVo);

    /**
     * 修改用密码
     * @param userVo
     * @return
     */
    Integer updateUserPassword(UserVo userVo);

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    UserVo findUserInfoByUserName(String userName);

    /**
     * 分页查询用户信息
     * @param userDto
     * @return
     */
    List<UserVo> queryUserListByPage(UserDto userDto);

    /**
     * 根据用户名查找用户信息
     * @param userName
     * @return
     */
    UserVo findUserByName(String userName);
}
