package com.jiaxin.pda.constant;

import java.util.Date;

/**
 * 常量类
 * @author milo
 */
public class Constant {
    /**
     * 用户-键
     */
    public static final String USER_KEY = "user";

    /**
     * 用户token-键
     */
    public static final String USER_TOKEN_KEY = "user_token";

    /**
     * 用户列表-键
     */
    public static final String USER_LIST_KEY = "user_list";

    /**
     * 分页对象-键
     */
    public static final String LIST_PAGE_KEY = "list_page";

    /**
     * 当前时间
     */
    public static final Date NOW = new Date();

    /**
     * 初始版本
     */
    public static final int INIT_REVERSION = 1;

    /**
     * 超级管理员
     */
    public static final int SUPER_ADMIN = -1;

    /**
     * 递增幅度
     */
    public static final int INCREASE_PACE = 1;

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 空字符
     */
    public static final String EMPTY_STRING = "";

    /**
     * 操作成功
     */
    public static final int OPERATE_SUCCESS = 1;

    /**
     * 操作失败
     */
    public static final int OPERATE_FAIL = 0;

    /**
     * 空整形值
     */
    public static final int EMPTY_INTEGER_VALUE = 0;

    /**
     * 用户ID
     */
    public static final String USER_ID = "userId";

    /**
     * token有效时间
     */
    public static final long TOKEN_VALID_PERIOD = 60L* 1000L* 60L * 24L *7L;

    /**
     * 有效期
     */
    public static final int VALID_PERIOD = 120;
}
