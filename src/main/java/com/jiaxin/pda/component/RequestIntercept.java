package com.jiaxin.pda.component;

import com.alibaba.fastjson.JSON;
import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.vo.GeneralVo;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.util.JWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 请求拦截器
 * @author milo
 */
@Component
public class RequestIntercept implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(RequestIntercept.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(null == token || Constant.EMPTY_INTEGER_VALUE == token.trim().length()){
            logger.info("被拦截......");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            GeneralVo result = new GeneralVo(ErrorListEnum.INVALID_TOKEN,null);
            OutputStream out = response.getOutputStream();
            out.write(JSON.toJSONString(result).getBytes("utf-8"));
            out.flush();
            return false;
        }
        logger.info("没有被拦截......");
        Integer userId = JWT.unsign(token,Integer.class);
        request.getSession().setAttribute(Constant.USER_ID,userId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion...");
    }
}
