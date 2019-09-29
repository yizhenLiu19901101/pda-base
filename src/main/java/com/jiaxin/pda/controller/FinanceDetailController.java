package com.jiaxin.pda.controller;

import com.jiaxin.pda.entity.ListPageVo;
import com.jiaxin.pda.entity.dto.DictionaryDto;
import com.jiaxin.pda.entity.dto.FinanceDetailDto;
import com.jiaxin.pda.entity.vo.FinanceDetailVo;
import com.jiaxin.pda.entity.vo.GeneralVo;
import com.jiaxin.pda.entity.vo.MenuVo;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.service.FinanceDetailService;
import com.jiaxin.pda.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 财务详情控制器类
 * @author milo
 */
@RestController
public class FinanceDetailController {
    @Autowired
    private FinanceDetailService financeDetailService;

    /**
     * 插入财务信息
     * @param financeDetailVo 财务对象
     * @return 响应结果
     */
    @PutMapping("/finance/insertFinanceDetail")
    public GeneralVo insertFinanceDetail(@RequestBody FinanceDetailVo financeDetailVo){
        financeDetailService.insertFinanceDetail(financeDetailVo);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 修改财务信息
     * @param financeDetailVo 财务对象
     * @return 响应结果
     */
    @PutMapping("/finance/updateFinanceDetail")
    public GeneralVo updateFinanceDetail(@RequestBody FinanceDetailVo financeDetailVo){
        financeDetailService.updateFinanceDetail(financeDetailVo);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 删除财务信息
     * @param id 记录ID
     * @return 响应结果
     */
    @DeleteMapping("/finance/deleteFinanceDetail/{id}")
    public GeneralVo deleteFinanceDetail(@PathVariable("id") String id){
        financeDetailService.deleteFinanceDetail(id);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 按照条件分页查找财务信息
     * @return
     */
    @PostMapping("/finance/queryFinanceDetailByPage")
    public ListPageVo queryFinanceDetailByPage(@RequestBody FinanceDetailDto financeDetailDto){
        financeDetailDto.build();
        return new ListPageVo(ErrorListEnum.OPERATE_SUCCESS,financeDetailService.queryFinanceDetailByPage(financeDetailDto),financeDetailDto.getPageInfo());
    }
}
