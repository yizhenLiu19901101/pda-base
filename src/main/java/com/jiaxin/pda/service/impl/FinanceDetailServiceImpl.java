package com.jiaxin.pda.service.impl;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.dto.FinanceDetailDto;
import com.jiaxin.pda.entity.vo.FinanceDetailVo;
import com.jiaxin.pda.entity.vo.MenuVo;
import com.jiaxin.pda.entity.vo.OperateVo;
import com.jiaxin.pda.mapper.FinanceDetailMapper;
import com.jiaxin.pda.service.FinanceDetailService;
import com.jiaxin.pda.util.GenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 财务详情业务实现类
 * @author milo
 */
@Service
public class FinanceDetailServiceImpl implements FinanceDetailService {
    @Autowired
    private FinanceDetailMapper financeDetailMapper;

    @Override
    public int insertFinanceDetail(FinanceDetailVo financeDetailVo) {
        financeDetailVo.setId(GenerateUtil.generateRandomString());
        financeDetailVo.setUuid(financeDetailMapper.selectMaxUuid() + Constant.INCREASE_PACE);
        this.initCreateParam(financeDetailVo);
        return financeDetailMapper.insertFinanceDetail(financeDetailVo);
    }

    @Override
    public int updateFinanceDetail(FinanceDetailVo financeDetailVo) {
        this.initUpdateParam(financeDetailVo);
        return financeDetailMapper.updateFinanceDetail(financeDetailVo);
    }

    @Override
    public int deleteFinanceDetail(String id) {
        FinanceDetailVo financeDetailVo = financeDetailMapper.queryFinanceDetailById(id);
        financeDetailVo.setDeleteFlag(true);
        return financeDetailMapper.deleteFinanceDetail(financeDetailVo);
    }

    @Override
    public List<FinanceDetailVo> queryFinanceDetailByPage(FinanceDetailDto financeDetailDto) {
        return financeDetailMapper.queryFinanceDetailByPage(financeDetailDto);
    }


    /**
     * 初始化创建参数
     * @param operateVo
     */
    private void initCreateParam(OperateVo operateVo){
        operateVo.setDeleteFlag(false);
        operateVo.setReversion(Constant.INIT_REVERSION);
        operateVo.setCreatedBy(Constant.SUPER_ADMIN);
        operateVo.setCreatedTime(Constant.NOW);
        //初始化更次参数
        this.initUpdateParam(operateVo);
    }

    /**
     * 初始化更新参数
     * @param operateVo
     */
    private void initUpdateParam(OperateVo operateVo){
        operateVo.setUpdatedBy(Constant.SUPER_ADMIN);
        operateVo.setUpdatedTime(Constant.NOW);
    }
}
