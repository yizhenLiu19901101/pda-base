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
     * 查询消费统计数据
     */
    QUERY_SUM_CONSUME_STATISTICS(2,"查询统计数据（总消费）"),
    /**
     * 查询收入统计数据
     */
    QUERY_SUM_INCOME_STATISTICS(3,"查询统计数据（总收入）"),
    /**
     * 查询净收入统计数据
     */
    QUERY_NET_INCOME_STATISTICS(4,"查询统计数据（净收入）");
    
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
