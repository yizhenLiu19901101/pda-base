package com.jiaxin.pda.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 财务详情类
 * @author milo
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class FinanceDetailVo extends OperateVo{
    /**
     * 数据库主键
     */
    private String id;

    /**
     * 业务主键
     */
    private int uuid;

    /**
     * 数据项ID
     */
    private int itemId;

    /**
     * 花费金额
     */
    private double costMoney;

    /**
     * 备注
     */
    private String note;

    /**
     * 消费类型
     */
    private int costType;
}
