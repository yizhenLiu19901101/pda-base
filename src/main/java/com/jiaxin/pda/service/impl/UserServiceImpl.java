package com.jiaxin.pda.service.impl;


import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.Page;
import com.jiaxin.pda.entity.dto.UserDto;
import com.jiaxin.pda.entity.vo.UserPrivilegeVo;
import com.jiaxin.pda.entity.vo.UserTokenVo;
import com.jiaxin.pda.entity.vo.UserVo;
import com.jiaxin.pda.enumeration.LoginStatusEnum;
import com.jiaxin.pda.mapper.UserMapper;
import com.jiaxin.pda.mapper.UserPrivilegeMapper;
import com.jiaxin.pda.mapper.UserTokenMapper;
import com.jiaxin.pda.service.IUserService;
import com.jiaxin.pda.util.GenerateUtil;
import com.jiaxin.pda.util.JWT;
import com.jiaxin.pda.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 用户业务实现类
 * @author milo
 */
@Service
public class UserServiceImpl implements IUserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    /**
     * 用户映射类
     */
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private UserPrivilegeMapper userPrivilegeMapper;
    /**
     * 操作结果
     */
    private int result;
    @Override
    public UserVo findUserById(String id) {
        UserVo userVo;
        boolean flag =  redisTemplate.opsForHash().hasKey(id, Constant.USER_KEY);
        if(flag){
            logger.info("从redis中获得数据");
            userVo= (UserVo) redisTemplate.opsForHash().get(id,Constant.USER_KEY);
        }else{
            logger.info("从mysql中获得数据");
            userVo = userMapper.findUserById(id);
            redisTemplate.opsForHash().put(id,Constant.USER_KEY,userVo);
            redisTemplate.expire(id,Constant.VALID_PERIOD,TimeUnit.SECONDS);
        }
        return userVo;
    }

    @Override
    public Integer insertUser(UserVo userVo) throws NoSuchAlgorithmException {
        userVo.setId(GenerateUtil.generateRandomString());
        userVo.setUserId(userMapper.selectMaxUserId() + Constant.INCREASE_PACE);
        userVo.setPassword(Md5Util.MD5Encode(userVo.getPassword()));
        result = userMapper.insertUser(userVo);
        //成功将数据插入mysql数据库后，再将对象放到redis中
        if(Constant.OPERATE_SUCCESS == result){
            redisTemplate.opsForHash().put(userVo.getId(),Constant.USER_KEY,userVo);
            logger.info("将对象放入redis");
        }
        return result;
    }

    @Override
    public Integer deleteUserInfo(UserVo userVo) {
        userVo.setDeleteFlag(true);
        result = userMapper.deleteUserInfo(userVo);
        //成功将数据插入mysql数据库后，再将对象放到redis中
        if(Constant.OPERATE_SUCCESS == result){
            redisTemplate.opsForHash().delete(userVo.getId(),Constant.USER_KEY);
            logger.info("从redis中删掉该对象");
        }
        return result;
    }

    @Override
    public Integer updateUserInfo(UserVo userVo) {
        result = userMapper.updateUserInfo(userVo);
        if(Constant.OPERATE_SUCCESS == result){
            this.rePutUserObject(userVo.getId());
       }
        return result;
    }

    @Override
    public Integer updateUserPassword(UserVo userVo) throws NoSuchAlgorithmException{
        userVo.setPassword(Md5Util.MD5Encode(userVo.getPassword()));
        result = userMapper.updateUserPassword(userVo);
        if(Constant.OPERATE_SUCCESS == result){
            this.rePutUserObject(userVo.getId());
        }
        return result;
    }

    @Override
    public String userLogin(UserVo userVo) throws NoSuchAlgorithmException{
        //根据用户名和密码查找是否有目标用户存在，如果有，则成功登陆，否则，提示说用户不存在
        UserVo queryResult = userMapper.findUserInfoByUserName(userVo.getUserName());
        if(null != queryResult){
            String token = JWT.sign(queryResult.getId(),Constant.TOKEN_VALID_PERIOD);
            String password = Md5Util.MD5Encode(userVo.getPassword());
            if(password.equals(queryResult.getPassword())){
                UserTokenVo userTokenVo = new UserTokenVo();
                userTokenVo.setId(userTokenMapper.selectMaxTokenId() + Constant.INCREASE_PACE);
                userTokenVo.setUserId(queryResult.getId());
                userTokenVo.setUserToken(token);
                userTokenVo.setDeleteFlag(false);
                userTokenVo.setReversion(Constant.INIT_REVERSION);
                userTokenVo.setUpdatedBy(Constant.SUPER_ADMIN);
                userTokenVo.setUpdatedTime(Constant.NOW);
                userTokenVo.setCreatedBy(Constant.SUPER_ADMIN);
                userTokenVo.setCreatedTime(Constant.NOW);
                result = userTokenMapper.insertUserToken(userTokenVo);
                if(Constant.OPERATE_SUCCESS == result){
                    redisTemplate.opsForHash().put(userTokenVo.getUserToken(),Constant.USER_TOKEN_KEY,userTokenVo);
                    logger.info("将对象放入redis");
                }
                return token;
            }else{
                return LoginStatusEnum.PASSWORD_ERROR.getValue();
            }
        }else{
            return LoginStatusEnum.USER_NOT_EXIST.getValue();
        }
    }

    @Override
    public Integer userLogout(String token) {
        UserTokenVo queryResult = (UserTokenVo) redisTemplate.opsForHash().get(token,Constant.USER_TOKEN_KEY);
        if(null != queryResult){
            queryResult.setDeleteFlag(true);
            queryResult.setUpdatedTime(Constant.NOW);
            queryResult.setUpdatedBy(Constant.SUPER_ADMIN);
            result = userTokenMapper.deleteUserToken(queryResult);
            if(Constant.OPERATE_SUCCESS == result){
                redisTemplate.opsForHash().delete(token,Constant.USER_TOKEN_KEY);
                logger.info("将对象从redis中移除");
            }
            return result;
        }else{
            return Constant.OPERATE_FAIL;
        }
    }

    @Override
    public List<UserVo> queryUserListByPage(UserDto userDto) {
        List<UserVo> userVoList;
        if(redisTemplate.hasKey(Constant.USER_LIST_KEY + userDto.getUserName()) && redisTemplate.hasKey(Constant.LIST_PAGE_KEY)){
            logger.info("从redis中获得数据");
            userVoList = redisTemplate.opsForList().range(Constant.USER_LIST_KEY+userDto.getUserName(),0,-1);
            userDto.setPageInfo((Page) redisTemplate.opsForHash().get(Constant.LIST_PAGE_KEY,Constant.USER_LIST_KEY+userDto.getUserName()));
        }else{
            logger.info("从mysql中获得数据");
            userVoList = userMapper.queryUserListByPage(userDto);
            redisTemplate.opsForList().leftPushAll(Constant.USER_LIST_KEY + userDto.getUserName(),userVoList);
            redisTemplate.expire(Constant.USER_LIST_KEY + userDto.getUserName(),Constant.VALID_PERIOD,TimeUnit.SECONDS);
            redisTemplate.opsForHash().put(Constant.LIST_PAGE_KEY,Constant.USER_LIST_KEY+userDto.getUserName(),userDto.getPageInfo());
            redisTemplate.expire(Constant.LIST_PAGE_KEY,Constant.VALID_PERIOD,TimeUnit.SECONDS);
        }
        return userVoList;
    }

    @Override
    public int insertUserRole(UserPrivilegeVo userPrivilegeVo) {
        userPrivilegeVo.setId(GenerateUtil.generateRandomString());
        return userPrivilegeMapper.insertUserRole(userPrivilegeVo);
    }

    @Override
    public int deleteUserRole(UserPrivilegeVo userPrivilegeVo) {
        return userPrivilegeMapper.deleteUserRole(userPrivilegeVo);
    }

    @Override
    public UserPrivilegeVo selectByUserId(Integer userId) {
        return userPrivilegeMapper.selectByUserId(userId);
    }

    @Override
    public UserTokenVo findUserToken(String userId) {
        if(redisTemplate.opsForHash().hasKey(userId,Constant.USER_TOKEN_KEY)){
            return (UserTokenVo) redisTemplate.opsForHash().get(userId,Constant.USER_TOKEN_KEY);
        }else{
            return null;
        }
    }

    @Override
    public UserVo findUserByName(String userName) {
        return userMapper.findUserByName(userName);
    }

    /**
     * 将用户对象重新放到redis中
     * @param userId
     */
    private void rePutUserObject(String userId){
        redisTemplate.opsForHash().delete(userId,Constant.USER_KEY);
        redisTemplate.opsForHash().put(userId,Constant.USER_KEY,userMapper.findUserById(userId));
        logger.info("将对象重新放入redis");
    }
}
