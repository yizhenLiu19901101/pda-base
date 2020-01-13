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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/getDetailDate/{queryType}")
    @ApiOperation(value = "根据token查找当前用户的数据")
    public GeneralVo findByToken(@PathVariable("queryType") int queryType, HttpServletRequest request, HttpServletResponse response){
        try{
            Integer userId = getCurrentUserId(request,response);
            List<FinanceDetailVo> financeDetailList;
            if(QueryTypeEnum.QUERY_DETAIL.getKey() == queryType){
                //查询明细数据
                financeDetailList = financeDetailService.queryFinanceDetailByUserId(userId);
            }else{
                //查询统计数据
                financeDetailList = financeDetailService.queryFinanceStatisticsByUserId(userId);
            }
            if(null == financeDetailList){
                return new GeneralVo(ErrorListEnum.NOT_EXIST,null);
            }else{
                //以明细的方式展示
                List<Map<String,Object>> result = new ArrayList<>();
                if(QueryTypeEnum.QUERY_DETAIL.getKey() == queryType){
                    Map<String,Object> detailData;
                    for(FinanceDetailVo financeDetailVo:financeDetailList){
                        detailData= new HashMap<>(4);
                        if(financeDetailVo.getCostMoney() >= Constant.EMPTY_INTEGER_VALUE ){
                            detailData.put("type","circle");
                            detailData.put("color","red");
                        }else{
                            detailData.put("type","star");
                            detailData.put("color","green");
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat(Constant.TIME_FORMAT);
                        detailData.put("tag",sdf.format(financeDetailVo.getUpdatedTime()));
                        detailData.put("content",Math.abs(financeDetailVo.getCostMoney()));
                        detailData.put("note",financeDetailVo.getNote());
                        detailData.put("reversion",financeDetailVo.getReversion());
                        //以明细的方式展示数据
                        DictionaryVo dictionaryVo = dictionaryTypeService.queryDictionaryItemInfoByUuid(financeDetailVo.getItemId());
                        detailData.put("itemName",dictionaryVo.getItemName());
                        detailData.put("id",financeDetailVo.getId());
                        result.add(detailData);
                    }
                    return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,result);
                }
                //以统计的方式展示
                else{
                    for(FinanceDetailVo financeDetailVo:financeDetailList){
                        HashMap<String,Object> item = new HashMap<>(2);
                        DictionaryVo dictionaryVo = dictionaryTypeService.queryDictionaryItemInfoByUuid(financeDetailVo.getItemId());
                        item.put("item",dictionaryVo.getItemName());
                        item.put("money",financeDetailVo.getCostMoney());
                        result.add(item);
                    }
                    return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,result);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

}
