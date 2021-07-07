package com.jiaxin.pda.controller;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.ListPageVo;
import com.jiaxin.pda.entity.dto.FinanceDetailDto;
import com.jiaxin.pda.entity.vo.DictionaryVo;
import com.jiaxin.pda.entity.vo.FinanceDetailVo;
import com.jiaxin.pda.entity.vo.GeneralVo;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.enumeration.QueryTypeEnum;
import com.jiaxin.pda.service.IDictionaryTypeService;
import com.jiaxin.pda.service.IFinanceDetailService;
import com.jiaxin.pda.swagger.note.FinanceDetailNote;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 财务详情控制器类(业务数据，被该微服务忽略)
 * @author milo
 */
@RestController
@RequestMapping(value = "/finance")
@ApiIgnore
@Api(value = "finance",tags = {"finance_controller"})
@Slf4j
public class FinanceDetailController extends BaseController{
    @Autowired
    private IFinanceDetailService financeDetailService;
    @Autowired
    private IDictionaryTypeService dictionaryTypeService;

    /**
     * 插入财务信息
     * @param financeDetailVo 财务对象
     * @return 响应结果
     */
    @PutMapping("/insertFinanceDetail")
    @ApiImplicitParam(name = "financeDetailVo", value = FinanceDetailNote.INSERT_VALUE, required = true, dataType = "FinanceDetailVo")
    @ApiOperation(value = "插入财务详情",notes = FinanceDetailNote.INSERT_NOTE)
    public GeneralVo insertFinanceDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody FinanceDetailVo financeDetailVo){
        try{
            log.info("插入财务信息，入参为：{}",financeDetailVo);
            initOperateParam(request,response,financeDetailVo, Constant.CREATE_TYPE);
            int result = financeDetailService.insertFinanceDetail(financeDetailVo);
            return Constant.OPERATE_SUCCESS == result?new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null):new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 修改财务信息
     * @param financeDetailVo 财务对象
     * @return 响应结果
     */
    @PutMapping("/updateFinanceDetail")
    @ApiImplicitParam(name = "financeDetailVo", value = FinanceDetailNote.UPDATE_VALUE, required = true, dataType = "FinanceDetailVo")
    @ApiOperation(value = "修改财务详情",notes = FinanceDetailNote.UPDATE_NOTE)
    public GeneralVo updateFinanceDetail(HttpServletRequest request, HttpServletResponse response,@RequestBody FinanceDetailVo financeDetailVo){
        try{
            log.info("修改财务信息，入参为：{}",financeDetailVo);
            initOperateParam(request,response,financeDetailVo, Constant.UPDATE_TYPE);
            int result = financeDetailService.updateFinanceDetail(financeDetailVo);
            return Constant.OPERATE_SUCCESS == result?new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null):new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 删除财务信息
     * @param financeDetailVo
     * @return 响应结果
     */
    @DeleteMapping("/deleteFinanceDetail")
    @ApiImplicitParam(name = "financeDetailVo", value = FinanceDetailNote.DELETE_VALUE, required = true, dataType = "FinanceDetailVo")
    @ApiOperation(value = "删除财务详情",notes = FinanceDetailNote.DELETE_NOTE)
    public GeneralVo deleteFinanceDetail(HttpServletRequest request, HttpServletResponse response,@RequestBody FinanceDetailVo financeDetailVo){
        try{
            log.info("删除财务信息，入参为：{}",financeDetailVo);
            initOperateParam(request,response,financeDetailVo, Constant.UPDATE_TYPE);
            int result = financeDetailService.deleteFinanceDetail(financeDetailVo);
            return Constant.OPERATE_SUCCESS == result?new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null):new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 按照条件分页查找财务信息
     * @return
     */
    @ApiOperation(value = "分页查询财务详情",notes = FinanceDetailNote.QUERY_BY_PAGE_NOTE)
    @ApiImplicitParam(name = "financeDetailDto", value = FinanceDetailNote.QUERY_BY_PAGE_VALUE, required = true, dataType = "FinanceDetailDto")
    @PostMapping("/queryFinanceDetailByPage")
    public ListPageVo queryFinanceDetailByPage(@RequestBody FinanceDetailDto financeDetailDto){
        try{
            financeDetailDto.build();
            return new ListPageVo(ErrorListEnum.OPERATE_SUCCESS,financeDetailService.queryFinanceDetailByPage(financeDetailDto),financeDetailDto.getPageInfo());
        }catch(Exception e){
            e.printStackTrace();
            return new ListPageVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null,null);
        }
    }

    /**
     * 获得当前用户的所有财务数据
     */
    @PostMapping("/getDetailDate")
    @ApiOperation(value = "根据token查找当前用户的数据")
    public GeneralVo queryDetailDate(@RequestBody FinanceDetailDto financeDetailDto, HttpServletRequest request, HttpServletResponse response){
        try{
            financeDetailDto.setPeopleId(getCurrentUserId(request,response));
            List<FinanceDetailVo> financeDetailList;
            //设置开始日期和结束日期
            this.settingDate(financeDetailDto);
            if(QueryTypeEnum.QUERY_DETAIL.getKey() == financeDetailDto.getQueryType()){
                //查询明细数据
                financeDetailList = financeDetailService.queryFinanceDetailByUserId(financeDetailDto);
                //处理明细数据
                return processDetailData(financeDetailList);
            }else if(QueryTypeEnum.QUERY_NET_INCOME_STATISTICS.getKey() == financeDetailDto.getQueryType()){
                //查询净收入统计数据
                financeDetailList = financeDetailService.queryFinanceNetStatisticsByUserId(financeDetailDto);
                return processNetIncome(financeDetailList);
            }else{
                //查询总收入/总支出统计数据
                financeDetailList = financeDetailService.queryFinanceSumStatisticsByUserId(financeDetailDto);
                return processSumDate(financeDetailList);
            }
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 设置日期
     * @param financeDetailDto
     */
    private void settingDate(FinanceDetailDto financeDetailDto){
        Calendar cal = Calendar.getInstance();
        //设置开始时间和结束时间
        switch (financeDetailDto.getTimeType()){
            //当日
            case Constant.CURRENT_DAY:
                //设置开始时间
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.MILLISECOND,000);
                financeDetailDto.setStartDate(cal.getTime());
                //设置结束时间
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.SECOND, 59);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.MILLISECOND,999);
                financeDetailDto.setEndDate(cal.getTime());
                break;
            //本周
            case Constant.CURRENT_WEEK:
                //设置开始时间
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
                if (day_of_week == 0){
                    day_of_week = 7;
                }
                cal.add(Calendar.DATE, -day_of_week + 1);
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.SECOND, 59);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.MILLISECOND,999);
                financeDetailDto.setStartDate(cal.getTime());
                //设置结束时间
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
                if (day_of_week == 0){
                    day_of_week = 7;
                }
                cal.add(Calendar.DATE, -day_of_week + 7);
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.SECOND, 59);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.MILLISECOND,999);
                financeDetailDto.setEndDate(cal.getTime());
                break;
            //当前月
            case Constant.CURRENT_MONTH:
                // 设置开始时间
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
                cal.set(Calendar.MILLISECOND,000);
                financeDetailDto.setStartDate(cal.getTime());
                // 设置结束时间
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.SECOND, 59);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.MILLISECOND,999);
                financeDetailDto.setEndDate(cal.getTime());
                break;
            //自定义
            case Constant.CUSTOMIZE:
                // 什么都不做
                cal = Calendar.getInstance();
                cal.setTime(financeDetailDto.getEndDate());
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.SECOND, 59);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.MILLISECOND,999);
                financeDetailDto.setEndDate(cal.getTime());
                break;
            default:
                // 什么都不做
                break;
        }
        logger.info("开始时间为 "+ financeDetailDto.getStartDate() + " 结束时间为"+ financeDetailDto.getEndDate());
    }

    /**
     * 处理明细数据
     * @param financeDetailList
     * @return
     */
    private GeneralVo processDetailData(List<FinanceDetailVo> financeDetailList){
        List<Map<String,Object>> result = new ArrayList<>();
        Map<String,Object> detailData;
        if(null == financeDetailList || 0 > financeDetailList.size()){
            return new GeneralVo(ErrorListEnum.NO_DATA,null);
        }
        for(FinanceDetailVo financeDetailVo:financeDetailList){
            detailData= new HashMap<>(9);
            if(financeDetailVo.getCostMoney() >= Constant.EMPTY_INTEGER_VALUE ){
                detailData.put("type","circle");
                detailData.put("color","red");
            }else{
                detailData.put("type","star");
                detailData.put("color","green");
            }
            SimpleDateFormat sdf = new SimpleDateFormat(Constant.TIME_FORMAT);
            detailData.put("tag",sdf.format(financeDetailVo.getUpdatedTime()));
            detailData.put("content",financeDetailVo.getCostMoney());
            detailData.put("costType",financeDetailVo.getCostType());
            detailData.put("note",financeDetailVo.getNote());
            detailData.put("itemId",financeDetailVo.getItemId());
            detailData.put("reversion",financeDetailVo.getReversion());
            // 以明细的方式展示数据
            DictionaryVo dictionaryVo = dictionaryTypeService.queryDictionaryItemInfoByUuid(financeDetailVo.getItemId());
            detailData.put("itemName",dictionaryVo == null?"":dictionaryVo.getItemName());
            detailData.put("id",financeDetailVo.getId());
            result.add(detailData);
        }
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,result);
    }

    /**
     * 处理净收入数据
     * @param financeDetailList
     * @return
     */
    private GeneralVo processSumDate(List<FinanceDetailVo> financeDetailList){
        Map<String,Object> statisticsResult = new HashMap<String,Object>(2);
        List<Map<String,Object>> detailResult = new ArrayList<>();
        BigDecimal sum = BigDecimal.ZERO.setScale(2,BigDecimal.ROUND_HALF_UP);
        if(null == financeDetailList || 0 > financeDetailList.size()){
            return new GeneralVo(ErrorListEnum.NO_DATA,null);
        }
        for(FinanceDetailVo financeDetailVo:financeDetailList){
            BigDecimal costMoney = new BigDecimal(financeDetailVo.getCostMoney()).setScale(2,BigDecimal.ROUND_HALF_UP);
            sum = sum.add(costMoney);
            HashMap<String,Object> item = new HashMap<>(2);
            DictionaryVo dictionaryVo = dictionaryTypeService.queryDictionaryItemInfoByUuid(financeDetailVo.getItemId());
            item.put("item",dictionaryVo.getItemName());
            item.put("money",Math.abs(financeDetailVo.getCostMoney()));
            detailResult.add(item);
        }
        statisticsResult.put("detail",detailResult);
        statisticsResult.put("sum",sum);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,statisticsResult);
    }

    /**
     * 处理净收入数据
     * @param financeDetailList
     * @return
     */
    private GeneralVo processNetIncome(List<FinanceDetailVo> financeDetailList){
        if(null == financeDetailList || 0 > financeDetailList.size()){
            return new GeneralVo(ErrorListEnum.NO_DATA,null);
        }
        Map<String,Object> statisticsResult = new HashMap<>(2);
        List<Map<String,Object>> detailResult = new ArrayList<>();
        BigDecimal sum = BigDecimal.ZERO.setScale(2,BigDecimal.ROUND_HALF_UP);
        for(FinanceDetailVo financeDetailVo:financeDetailList){
            HashMap<String,Object> item = new HashMap<>(2);
            BigDecimal costMoney = new BigDecimal(financeDetailVo.getCostMoney()).setScale(2,BigDecimal.ROUND_HALF_UP);
            if(financeDetailVo.getCostType() == 1){
                sum = sum.subtract(costMoney);
                item.put("item", "支出");
            }else{
                sum = sum.add(costMoney);
                item.put("item", "收入");
            }
            item.put("money",financeDetailVo.getCostMoney());
            detailResult.add(item);
        }
        statisticsResult.put("detail",detailResult);
        statisticsResult.put("sum",sum);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,statisticsResult);
    }
}
