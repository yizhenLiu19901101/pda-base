package com.jiaxin.pda.conponent;

import com.alibaba.fastjson.JSONObject;
import com.jiaxin.pda.entity.vo.GeneralVo;
import com.jiaxin.pda.entity.vo.UserTokenVo;
import com.jiaxin.pda.entity.vo.UserVo;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.service.UserService;
import com.jiaxin.pda.util.JWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * token过滤器
 * @author milo
 */
@Component
public class TokenFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(TokenFilter.class);
    /**
     * 排除不拦截的url
     */
    private static final String[] EXCLUDE_PATH_PATTENS = { "/user/registerUser","/user/login"};

    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("初始化token过滤器......");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        //header方式
        String token = req.getHeader("token");
        boolean isFilter = false;
        boolean flag = true;
        String url = ((HttpServletRequest) request).getRequestURI();
        if(Arrays.asList(EXCLUDE_PATH_PATTENS).contains(url)){
            logger.info("登陆和注册接口不拦截");
            chain.doFilter(request, response);
            flag = false;
        }
        if(flag){
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            if (null == token || token.isEmpty()) {
                logger.info("token不存在");
                GeneralVo generalVo = new GeneralVo(ErrorListEnum.INVALID_TOKEN,null);
                response.getWriter().write(generalVo.toString());
            } else {
                //根据用户ID查询相应的token
                UserTokenVo userToken = userService.findUserToken(token);
                if (null == userToken || userToken.getUserToken().trim().length() == 0) {
                    logger.info("未登录");
                    GeneralVo generalVo = new GeneralVo(ErrorListEnum.NOT_LOGIN,null);
                    response.getWriter().write(generalVo.toString());
                } else {
                    String userId = JWT.unsign(userToken.getUserToken(), String.class);
                    UserVo userVo = userService.findUserById(userId);
                    if(null != userVo && (!userVo.isDeleteFlag())){
                        request.setAttribute("userId",userVo.getUserId());
                        isFilter = true;
                    }else{
                        logger.info("用户不存在");
                        GeneralVo generalVo = new GeneralVo(ErrorListEnum.INVALID_USER,null);
                        response.getWriter().write(generalVo.toString());
                    }
                }
            }
            if (isFilter) {
                logger.info("token filter过滤ok!");
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        logger.info("销毁token过滤器");
    }
}
