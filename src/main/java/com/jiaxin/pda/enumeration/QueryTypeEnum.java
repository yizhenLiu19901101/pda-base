package com.jiaxin.pda.enumeration;

/**
 * 查询类型枚举类
 * @author milo
 */
public enum QueryTypeEnum {
    /**
     * 查询明细数据
     */
    QUERY_DETAIL (1,"查询明细数据"),
    /**
     * 查询统计数据
     */
    QUERY_STATISTICS(0,"查询统计数据");


    private final int key;
    private final String value;

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    QueryTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
