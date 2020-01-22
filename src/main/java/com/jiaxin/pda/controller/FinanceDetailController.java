package com.jiaxin.pda.controller;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.ListPageVo;
import com.jiaxin.pda.entity.dto.FinanceDetailDto;
import com.jiaxin.pda.entity.vo.DictionaryTypeVo;
import com.jiaxin.pda.entity.vo.DictionaryVo;
import com.jiaxin.pda.entity.vo.FinanceDetailVo;
import com.jiaxin.pda.entity.vo.GeneralVo;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.enumeration.QueryTypeEnum;
import com.jiaxin.pda.enumeration.TimeTypeEnum;
import com.jiaxin.pda.service.DictionaryTypeService;
import com.jiaxin.pda.service.FinanceDetailService;
import com.jiaxin.pda.swagger.note.FinanceDetailNote;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/api/finance")
@ApiIgnore
@Api(value = "finance",tags = {"finance_controller"})
public class FinanceDetailController extends BaseController{
    @Autowired
    private FinanceDetailService financeDetailService;
    @Autowired
    private DictionaryTypeService dictionaryTypeService;

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
            logger.info("插入财务信息，入参为：{}",financeDetailVo);
            initOperateParam(request,response,financeDetailVo, Constant.CREATE_TYPE);
            int result = financeDetailService.insertFinanceDetail(financeDetailVo);
            if(Constant.OPERATE_SUCCESS == result){
                return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
            }else{
                return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
            }
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
            logger.info("修改财务信息，入参为：{}",financeDetailVo);
            initOperateParam(request,response,financeDetailVo, Constant.UPDATE_TYPE);
            int result = financeDetailService.updateFinanceDetail(financeDetailVo);
            if(Constant.OPERATE_SUCCESS == result){
                return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
            }else{
                return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
            }
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
            logger.info("删除财务信息，入参为：{}",financeDetailVo);
            initOperateParam(request,response,financeDetailVo, Constant.UPDATE_TYPE);
            int result = financeDetailService.deleteFinanceDetail(financeDetailVo);
            if(Constant.OPERATE_SUCCESS == result){
                return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
            }else{
                return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
            }
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
    public GeneralVo findByToken(@RequestBody FinanceDetailDto financeDetailDto, HttpServletRequest request, HttpServletResponse response){
        try{
            Integer userId = getCurrentUserId(request,response);
            List<FinanceDetailVo> financeDetailList;
            Calendar cal = Calendar.getInstance();
            //设置开始时间和结束时间
            switch (financeDetailDto.getTimeType()){
                //当日
                case Constant.CURRENT_DAY:
                    //设置开始时间
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MINUTE, 0);
                    financeDetailDto.setStartDate(cal.getTime());
                    //设置结束时间
                    cal.set(Calendar.HOUR_OF_DAY, 23);
                    cal.set(Calendar.SECOND, 59);
                    cal.set(Calendar.MINUTE, 59);
                    financeDetailDto.setEndDate(cal.getTime());
                    break;
                //本周
                case Constant.CURRENT_WEEK:
                    //设置开始时间
                    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                    financeDetailDto.setStartDate(cal.getTime());
                    //设置结束时间
                    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                    cal.add(Calendar.DAY_OF_WEEK, 7);
                    cal.set(Calendar.HOUR_OF_DAY, 23);
                    cal.set(Calendar.SECOND, 59);
                    cal.set(Calendar.MINUTE, 59);
                    financeDetailDto.setEndDate(cal.getTime());
                    break;
                //当前月
                case Constant.CURRENT_MONTH:
                    // 设置开始时间
                    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
                    financeDetailDto.setStartDate(cal.getTime());
                    // 设置结束时间
                    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                    cal.set(Calendar.HOUR_OF_DAY, 23);
                    cal.set(Calendar.SECOND, 59);
                    cal.set(Calendar.MINUTE, 59);
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
                    financeDetailDto.setEndDate(cal.getTime());
                    break;
                default:
                    // 什么都不做
                    break;
            }
            logger.info("开始时间为 "+ financeDetailDto.getStartDate() + " 结束时间为"+ financeDetailDto.getEndDate());
            if(QueryTypeEnum.QUERY_DETAIL.getKey() == financeDetailDto.getQueryType()){
                //查询明细数据
                financeDetailList = financeDetailService.queryFinanceDetailByUserId(userId);
            }else if(QueryTypeEnum.QUERY_NET_INCOME_STATISTICS.getKey() == financeDetailDto.getQueryType()){
                //查询净收入统计数据
                financeDetailList = financeDetailService.queryFinanceNetStatisticsByUserId(userId);
            }else{
                //查询总收入/总支出统计数据
                financeDetailList = financeDetailService.queryFinanceSumStatisticsByUserId(userId,financeDetailDto.getQueryType());
            }
            if(null == financeDetailList){
                return new GeneralVo(ErrorListEnum.NOT_EXIST,null);
            }else{
                //以明细的方式展示
                List<Map<String,Object>> result = new ArrayList<>();
                if(QueryTypeEnum.QUERY_DETAIL.getKey() == financeDetailDto.getQueryType()){
                    Map<String,Object> detailData;
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
                        //以明细的方式展示数据
                        DictionaryVo dictionaryVo = dictionaryTypeService.queryDictionaryItemInfoByUuid(financeDetailVo.getItemId());
                        detailData.put("itemName",dictionaryVo.getItemName());
                        detailData.put("id",financeDetailVo.getId());
                        result.add(detailData);
                    }
                    return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,result);
                }
                //以统计的方式展示-净收入
                else if(QueryTypeEnum.QUERY_NET_INCOME_STATISTICS.getKey() != financeDetailDto.getQueryType()){
                    Map<String,Object> statisticsResult = new HashMap<String,Object>(2);
                    List<Map<String,Object>> detailResult = new ArrayList<>();
                    BigDecimal sum = BigDecimal.ZERO.setScale(2,BigDecimal.ROUND_HALF_UP);
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
                //以统计的方式展示-总收入、总支出
                else{
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
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }
}
