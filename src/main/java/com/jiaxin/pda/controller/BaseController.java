package com.jiaxin.pda.controller;

import com.jiaxin.pda.constant.Constant;
import org.springframework.http.HttpRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 控制器基类
 * @author milo
 */
public class BaseController {

    /**
     * 获得当前用户的ID
     * @param request
     * @param response
     * @return
     */
    public static int getCurrentUserId(ServletRequest request, ServletResponse response){
        if(null != request && null != request.getAttribute(Constant.USER_ID)){
            return (int)request.getAttribute(Constant.USER_ID);
        }else{
            return Constant.EMPTY_INTEGER_VALUE;
        }

    }

}
