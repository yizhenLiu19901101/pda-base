package com.jiaxin.pda.controller;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.vo.OperateVo;
import com.jiaxin.pda.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器基类
 * @author milo
 */
public class BaseController {

     static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    /**
     * 获得当前用户的ID
     * @param request
     * @param response
     * @return
     */
    public static int getCurrentUserId(HttpServletRequest request, HttpServletResponse response){
        if(null != request && null != request.getAttribute(Constant.USER_ID)){
            return (int)request.getAttribute(Constant.USER_ID);
        }else{
            return Constant.SUPER_ADMIN;
        }
    }

    /**
     * 初始化用户参数
     * @param request
     * @param response
     * @param operateVo
     * @param type
     */
    public static void initPeopleParam(HttpServletRequest request, HttpServletResponse response, OperateVo operateVo, Integer type){
        int currentUserId = getCurrentUserId(request,response);
        operateVo.setUpdatedBy(currentUserId);
        operateVo.setUpdatedTime(Constant.NOW);
        if(type == Constant.CREATE_TYPE){
            operateVo.setDeleteFlag(false);
            operateVo.setReversion(Constant.INIT_REVERSION);
            operateVo.setCreatedTime(Constant.NOW);
            operateVo.setCreatedBy(currentUserId);
        }
    }

}
