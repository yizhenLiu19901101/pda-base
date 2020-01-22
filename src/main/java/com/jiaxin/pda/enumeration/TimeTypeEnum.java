package com.jiaxin.pda.enumeration;

/**
 * 时间类型枚举类
 * @author milo
 */
public enum TimeTypeEnum {
    /**
     * 当日
     */
    CURRENT_DAY (1,"当日"),
    /**
     * 本周
     */
    CURRENT_WEEK(2,"本周"),
    /**
     * 当前月
     */
    CURRENT_MONTH(3,"当前月"),
    /**
     * 自定义
     */
    CUSTOMIZE(4,"自定义");

    private final int key;
    private final String value;

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    TimeTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
