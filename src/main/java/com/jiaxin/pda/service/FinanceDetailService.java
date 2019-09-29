package com.jiaxin.pda.service;

import com.jiaxin.pda.entity.dto.FinanceDetailDto;
import com.jiaxin.pda.entity.vo.FinanceDetailVo;

import java.util.List;

/**
 * 财务详情业务接口
 * @author milo
 */
public interface FinanceDetailService {
    /**
     * 插入财务数据
     * @param financeDetailVo
     * @return
     */
    int insertFinanceDetail(FinanceDetailVo financeDetailVo);

    /**
     * 修改财务数据
     * @param financeDetailVo
     * @return
     */
    int updateFinanceDetail(FinanceDetailVo financeDetailVo);

    /**
     * 删除财务数据
     * @param id
     * @return
     */
    int deleteFinanceDetail(String id);

    /**
     * 按照条件分页查询财务对象
     * @param financeDetailDto
     * @return
     */
    List<FinanceDetailVo> queryFinanceDetailByPage(FinanceDetailDto financeDetailDto);
}
