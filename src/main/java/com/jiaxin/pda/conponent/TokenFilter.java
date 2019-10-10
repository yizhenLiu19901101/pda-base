package com.jiaxin.pda.conponent;

import com.jiaxin.pda.entity.vo.UserTokenVo;
import com.jiaxin.pda.entity.vo.UserVo;
import com.jiaxin.pda.service.UserService;
import com.jiaxin.pda.util.JWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token过滤器
 * @author milo
 */
@Component
public class TokenFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("初始化token过滤器......");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        //header方式
        String token = req.getHeader("token");
        boolean isFilter = false;
        if (null == token || token.isEmpty()) {
          logger.info("token不存在");
        } else {
            //根据用户ID查询相应的token
            UserTokenVo userToken = userService.findUserToken(token);
            if (null == userToken || userToken.getUserToken().trim().length() == 0) {
                logger.info("token失效");
            } else {
                String userId = JWT.unsign(userToken.getUserToken(), String.class);
                UserVo userVo = userService.findUserById(userId);
                if(null != userVo && (!userVo.isDeleteFlag())){
                    isFilter = true;
                }else{
                    logger.info("用户不存在");
                }
            }
        }
        if (isFilter) {
            logger.info("token filter过滤ok!");
            chain.doFilter(request, response);
       }
    }

    @Override
    public void destroy() {
        logger.info("销毁token过滤器");
    }
}
