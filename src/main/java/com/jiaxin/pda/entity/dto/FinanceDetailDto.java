package com.jiaxin.pda.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiaxin.pda.entity.PageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 财务数据数据传输类
 * @author milo
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FinanceDetailDto extends PageEntity {
    /**
     * 用户
     */
    private int peopleId;

    /**
     * 开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date startDate;

    /**
     * 结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date endDate;

    /**
     * 查询类型
     */
    private int queryType;

    /**
     * 日期类型
     */
    private int timeType;
}
