package com.jiaxin.pda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 * @author milo
 */
@SpringBootApplication
@EnableSwagger2
public class PdaApplication {
    private static final Logger logger = LoggerFactory.getLogger(PdaApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(PdaApplication.class, args);
        logger.info("项目启动成功");
    }
}
