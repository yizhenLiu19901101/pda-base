package com.jiaxin.pda.mapper;

import com.jiaxin.pda.entity.dto.UserDto;
import com.jiaxin.pda.entity.vo.UserTokenVo;
import com.jiaxin.pda.entity.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户token映射类
 * @author milo
 */
@Mapper
public interface UserTokenMapper {

    /**
     * 插入用户token
     * @param userTokenVo
     * @return
     */
    int insertUserToken(UserTokenVo userTokenVo);

    /**
     * 根据用户ID查找用户token
     * @param userId
     * @return
     */
    UserTokenVo findUserTokenByUserId(int userId);

    /**
     * 删除用户token
     * @param userTokenVo
     * @return
     */
    int deleteUserToken(UserTokenVo userTokenVo);

    /**
     * 查询用户token表中最大的ID
     * @return
     */
    Integer selectMaxTokenId();



}
