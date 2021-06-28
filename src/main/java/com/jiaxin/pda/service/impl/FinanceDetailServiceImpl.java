package com.jiaxin.pda.service.impl;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.dto.FinanceDetailDto;
import com.jiaxin.pda.entity.vo.FinanceDetailVo;
import com.jiaxin.pda.mapper.FinanceDetailMapper;
import com.jiaxin.pda.service.IFinanceDetailService;
import com.jiaxin.pda.util.GenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 财务详情业务实现类
 * @author milo
 */
@Service
public class FinanceDetailServiceImpl implements IFinanceDetailService {
    @Autowired
    private FinanceDetailMapper financeDetailMapper;

    @Override
    public int insertFinanceDetail(FinanceDetailVo financeDetailVo) {
        financeDetailVo.setId(GenerateUtil.generateRandomString());
        financeDetailVo.setUuid(financeDetailMapper.selectMaxUuid() + Constant.INCREASE_PACE);
        return financeDetailMapper.insertFinanceDetail(financeDetailVo);
    }

    @Override
    public int updateFinanceDetail(FinanceDetailVo financeDetailVo) {
        return financeDetailMapper.updateFinanceDetail(financeDetailVo);
    }

    @Override
    public int deleteFinanceDetail(FinanceDetailVo financeDetailVo) {
        financeDetailVo.setDeleteFlag(true);
        return financeDetailMapper.deleteFinanceDetail(financeDetailVo);
    }

    @Override
    public List<FinanceDetailVo> queryFinanceDetailByPage(FinanceDetailDto financeDetailDto) {
        return financeDetailMapper.queryFinanceDetailByPage(financeDetailDto);
    }

    @Override
    public List<FinanceDetailVo> queryFinanceDetailByUserId(FinanceDetailDto financeDetailDto) {
        return financeDetailMapper.queryFinanceDetailByUserId(financeDetailDto);
    }

    @Override
    public List<FinanceDetailVo> queryFinanceSumStatisticsByUserId(FinanceDetailDto financeDetailDto) {
        return financeDetailMapper.queryFinanceSumStatisticsByUserId(financeDetailDto);
    }

    @Override
    public List<FinanceDetailVo> queryFinanceNetStatisticsByUserId(FinanceDetailDto financeDetailDto) {
        return financeDetailMapper.queryFinanceNetStatisticsByUserId(financeDetailDto);
    }


}
