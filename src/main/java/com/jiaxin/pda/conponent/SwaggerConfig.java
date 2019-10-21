package com.jiaxin.pda.conponent;



import com.jiaxin.pda.constant.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger配置文件
 * @author milo
 */
@Configuration
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private Boolean enable = true;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage(Constant.BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 项目信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(Constant.TITLE)
                .description(Constant.DESCRIPTION)
                .version(Constant.CODE_VERSION)
                .build();
    }
}
