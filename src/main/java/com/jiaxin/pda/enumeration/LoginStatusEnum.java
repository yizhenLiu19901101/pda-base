package com.jiaxin.pda.enumeration;

import lombok.Data;

/**
 * 用户登陆枚举类
 * @author milo
 */
public enum LoginStatusEnum {
    /**
     * 用户不存在
     */
    USER_NOT_EXIST (2,"用户不存在"),
    /**
     *
     */
    PASSWORD_ERROR(3,"密码不对");


    private final int key;
    private final String value;

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    LoginStatusEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
