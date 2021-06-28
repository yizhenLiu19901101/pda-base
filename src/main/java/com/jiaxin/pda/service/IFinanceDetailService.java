package com.jiaxin.pda.service;

import com.jiaxin.pda.entity.dto.FinanceDetailDto;
import com.jiaxin.pda.entity.vo.FinanceDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 财务详情业务接口
 * @author milo
 */
public interface IFinanceDetailService {
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
     * @param financeDetailVo
     * @return
     */
    int deleteFinanceDetail(FinanceDetailVo financeDetailVo);

    /**
     * 按照条件分页查询财务对象
     * @param financeDetailDto
     * @return
     */
    List<FinanceDetailVo> queryFinanceDetailByPage(FinanceDetailDto financeDetailDto);

    /**
     * 按照用户ID查询财务对象
     * @param financeDetailDto
     * @return
     */
    List<FinanceDetailVo> queryFinanceDetailByUserId(FinanceDetailDto financeDetailDto);

    /**
     * 按照用户ID查询财务统计数据
     * @param financeDetailDto
     * @return
     */
    List<FinanceDetailVo> queryFinanceSumStatisticsByUserId(FinanceDetailDto financeDetailDto);

    /**
     * 按照用户ID查询财务统计数据
     * @param financeDetailDto
     * @return
     */
    List<FinanceDetailVo> queryFinanceNetStatisticsByUserId(FinanceDetailDto financeDetailDto);
}
