package com.jiaxin.pda.config;

import com.jiaxin.pda.component.RequestIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 跨域Cross-Origin Resource Sharing（跨域资源共享）
 * 权限拦截
 * @author milo
 */
@Configuration
public class CrossOriginResourceSharingConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private RequestIntercept requestIntercept;
    /**
     * 向注册中心添加允许的路径
     * @param registry 注册中心
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /**
         * 分别表示是否允许发送cookie、接受的域名、允许的方法以及超时时间
         */
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    /**
     * 向注册中心添加自定义的拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestIntercept).addPathPatterns("/**/**")
                .excludePathPatterns("/user/registerUser","/v2/*",
                        "/swagger-resources","/swagger-resources/*/*","/user/login");
        super.addInterceptors(registry);
    }
}
