package com.jiaxin.pda.conponent;


import com.jiaxin.pda.constant.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置文件
 * @author milo
 */
@Configuration
@EnableSwagger2
@ComponentScan(value = {"com.jiaxin.pda.controller"})
public class SwaggerConfig {

    private Boolean enable = true;

    private Docket getDocket(String groupName,String regexName) {

        List<Parameter> pars = getParameters();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jiaxin.pda"))
                .paths(PathSelectors.regex(Constant.CODE_VERSION + regexName))
                .build().globalOperationParameters(pars);
    }

    @Bean
    public Docket createABCDApi() { return getDocket("ABCD","/(a|b|c|d|A|B|C|D).*/.*"); }

    @Bean
    public Docket createEFGHApi() {
        return getDocket("EFGH","/(e|f|g|h|E|F|G|H).*/.*");
    }

    @Bean
    public Docket createIJKLApi() {
        return getDocket("IJKL","/(i|j|k|l|I|J|K|L).*/.*");
    }

    @Bean
    public Docket createMNOPApi() {
        return getDocket("MNOP","/(m|n|o|p|M|N|O|P).*/.*");
    }

    @Bean
    public Docket createQRSTApi() {
        return getDocket("QRST","/(q|r|s|t|Q|R|S|T).*/.*");
    }

    @Bean
    public Docket createUVWXYZApi() {
        return getDocket("UVWXYZ","/(u|v|w|x|y|z|U|V|W|X|Y|Z).*/.*");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("")
                .version("v1")
                .build();
    }

    private List<Parameter> getParameters() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("token").description("用户令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        List<Parameter> pars = new ArrayList<>();
        pars.add(tokenPar.build());
        return pars;
    }
}
