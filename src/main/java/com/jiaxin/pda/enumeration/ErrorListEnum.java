package com.jiaxin.pda.enumeration;

/**
 * 返回码
 * @author  milo
 * @date 2018-09-26
 */
public enum ErrorListEnum {
    /**
     * 操作成功
     */
    OPERATE_SUCCESS(200,"操作成功"),
    /**
     * 操作失败
     */
    OPERATE_FAIL(201,"操作失败"),
    /**
     * 未登录
     */
    NOT_LOGIN(204,"未登录"),
    /**
     * token不存在或者token失效
     */
    INVALID_TOKEN(205,"token不存在或者token失效"),
    /**
     * 用户不存在或者token失效
     */
    INVALID_USER(206,"用户不存在"),
    /**
     * 服务器内部错误
     */
    SERVER_INTERNAL_ERROR(202,"服务器内部错误");


    private final int key;
    private final String value;

    private ErrorListEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public static String getValue(int key) {
        ErrorListEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ErrorListEnum e = var1[var3];
            if (e.getKey() == key) {
                return e.value;
            }
        }
        return null;
    }
}
