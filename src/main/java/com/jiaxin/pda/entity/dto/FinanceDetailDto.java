package com.jiaxin.pda.entity.dto;

import com.jiaxin.pda.entity.PageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
}
