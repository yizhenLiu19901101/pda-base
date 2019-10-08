package com.jiaxin.pda.util;

import com.jiaxin.pda.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * 创建工具类
 * @author milo
 */
public class GenerateUtil {

    private static final Logger logger = LoggerFactory.getLogger(GenerateUtil.class);
    /**
     * 创建随机字符串
     * @return
     */
    public static String generateRandomString(){
        return  UUID.randomUUID().toString().replace("-", Constant.EMPTY_STRING);
    }


}
