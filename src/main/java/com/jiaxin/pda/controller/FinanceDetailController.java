package com.jiaxin.pda.controller;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.ListPageVo;
import com.jiaxin.pda.entity.dto.FinanceDetailDto;
import com.jiaxin.pda.entity.vo.FinanceDetailVo;
import com.jiaxin.pda.entity.vo.GeneralVo;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.service.FinanceDetailService;
import com.jiaxin.pda.swagger.note.FinanceDetailNote;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 财务详情控制器类
 * @author milo
 */
@RestController
@RequestMapping(value = "/finance")
@Api(value = "finance",tags = {"finance_controller"})
public class FinanceDetailController extends BaseController{
    @Autowired
    private FinanceDetailService financeDetailService;

    /**
     * 插入财务信息
     * @param financeDetailVo 财务对象
     * @return 响应结果
     */
    @PutMapping("/finance/insertFinanceDetail")
    @ApiImplicitParam(name = "financeDetailVo", value = FinanceDetailNote.INSERT_VALUE, required = true, dataType = "FinanceDetailVo")
    @ApiOperation(value = "插入财务详情",notes = FinanceDetailNote.INSERT_NOTE)
    public GeneralVo insertFinanceDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody FinanceDetailVo financeDetailVo){
        logger.info("插入财务信息，入参为：{}",financeDetailVo);
        initOperateParam(request,response,financeDetailVo, Constant.CREATE_TYPE);
        int result = financeDetailService.insertFinanceDetail(financeDetailVo);
        if(Constant.OPERATE_SUCCESS == result){
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }else{
            return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }
    }

    /**
     * 修改财务信息
     * @param financeDetailVo 财务对象
     * @return 响应结果
     */
    @PutMapping("/finance/updateFinanceDetail")
    @ApiImplicitParam(name = "financeDetailVo", value = FinanceDetailNote.UPDATE_VALUE, required = true, dataType = "FinanceDetailVo")
    @ApiOperation(value = "修改财务详情",notes = FinanceDetailNote.UPDATE_NOTE)
    public GeneralVo updateFinanceDetail(HttpServletRequest request, HttpServletResponse response,@RequestBody FinanceDetailVo financeDetailVo){
        logger.info("修改财务信息，入参为：{}",financeDetailVo);
        initOperateParam(request,response,financeDetailVo, Constant.UPDATE_TYPE);
        int result = financeDetailService.updateFinanceDetail(financeDetailVo);
        if(Constant.OPERATE_SUCCESS == result){
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }else{
            return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }
    }

    /**
     * 删除财务信息
     * @param financeDetailVo
     * @return 响应结果
     */
    @DeleteMapping("/finance/deleteFinanceDetail")
    @ApiImplicitParam(name = "financeDetailVo", value = FinanceDetailNote.DELETE_VALUE, required = true, dataType = "FinanceDetailVo")
    @ApiOperation(value = "删除财务详情",notes = FinanceDetailNote.DELETE_NOTE)
    public GeneralVo deleteFinanceDetail(HttpServletRequest request, HttpServletResponse response,@RequestBody FinanceDetailVo financeDetailVo){
        logger.info("删除财务信息，入参为：{}",financeDetailVo);
        initOperateParam(request,response,financeDetailVo, Constant.UPDATE_TYPE);
        int result = financeDetailService.deleteFinanceDetail(financeDetailVo);
        if(Constant.OPERATE_SUCCESS == result){
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }else{
            return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }
    }

    /**
     * 按照条件分页查找财务信息
     * @return
     */
    @ApiOperation(value = "分页查询财务详情",notes = FinanceDetailNote.QUERY_BY_PAGE_NOTE)
    @ApiImplicitParam(name = "financeDetailDto", value = FinanceDetailNote.QUERY_BY_PAGE_VALUE, required = true, dataType = "FinanceDetailDto")
    @PostMapping("/finance/queryFinanceDetailByPage")
    public ListPageVo queryFinanceDetailByPage(@RequestBody FinanceDetailDto financeDetailDto){
        financeDetailDto.build();
        return new ListPageVo(ErrorListEnum.OPERATE_SUCCESS,financeDetailService.queryFinanceDetailByPage(financeDetailDto),financeDetailDto.getPageInfo());
    }
}
