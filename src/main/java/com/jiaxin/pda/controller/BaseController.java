package com.jiaxin.pda.controller;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.vo.OperateVo;
import com.jiaxin.pda.entity.vo.SimpleOperateVo;
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
     * 获得当前用户的用户ID
     * @param request
     * @param response
     * @return
     */
    public static int getCurrentUserId(HttpServletRequest request, HttpServletResponse response){
        if(null != request && null != request.getSession().getAttribute(Constant.USER_ID)){
            return (int)request.getSession().getAttribute(Constant.USER_ID);
        }else{
            return Constant.SUPER_ADMIN;
        }
    }

    /**
     * 获得当前用户的ID
     * @param request
     * @param response
     * @return
     */
    public static String getCurrentId(HttpServletRequest request, HttpServletResponse response){
        if(null != request && null != request.getSession().getAttribute(Constant.ID)){
            return (String)request.getSession().getAttribute(Constant.ID);
        }else{
            return null;
        }
    }

    /**
     * 初始化操作对象参数
     * @param request
     * @param response
     * @param operateVo
     * @param type
     */
    public static void initOperateParam(HttpServletRequest request, HttpServletResponse response, OperateVo operateVo, Integer type){
        //初始化简单操作对象参数
        initSimpleOperateParam(request,response,operateVo,type);
        if(type == Constant.CREATE_TYPE){
            operateVo.setDeleteFlag(false);
            operateVo.setReversion(Constant.INIT_REVERSION);

        }
    }

    /**
     * 初始化简单操作对象参数
     * @param request
     * @param response
     * @param simpleOperateVo
     * @param type
     */
    public static void initSimpleOperateParam(HttpServletRequest request, HttpServletResponse response, SimpleOperateVo simpleOperateVo, Integer type){
        int currentUserId = getCurrentUserId(request,response);
        simpleOperateVo.setUpdatedBy(currentUserId);
        if(null == simpleOperateVo.getUpdatedTime()){
            simpleOperateVo.setUpdatedTime(Constant.NOW);
        }
        if(type == Constant.CREATE_TYPE){
            simpleOperateVo.setCreatedTime(Constant.NOW);
            simpleOperateVo.setCreatedBy(currentUserId);
        }
    }
}
