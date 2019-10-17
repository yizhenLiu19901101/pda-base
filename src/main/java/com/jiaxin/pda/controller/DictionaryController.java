package com.jiaxin.pda.controller;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.ListPageVo;
import com.jiaxin.pda.entity.dto.DictionaryDto;
import com.jiaxin.pda.entity.dto.DictionaryTypeDto;
import com.jiaxin.pda.entity.vo.DictionaryTypeVo;
import com.jiaxin.pda.entity.vo.DictionaryVo;
import com.jiaxin.pda.entity.vo.GeneralVo;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.service.DictionaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 字典控制器类
 * @author milo
 */
@RestController
public class DictionaryController extends BaseController{

    @Autowired
    private DictionaryTypeService dictionaryTypeService;

    /**
     * 插入字典类型信息
     * @param dictionaryTypeVo 字典类型对象
     * @return 响应结果
     */
    @PutMapping("/dictionary/insertDictionaryType")
    public GeneralVo insertDictionaryType(HttpServletRequest request,HttpServletResponse response, @RequestBody DictionaryTypeVo dictionaryTypeVo){
        logger.info("插入数据字典类型：{}",dictionaryTypeVo);
        //数据字典类型名称校验
        if(null == dictionaryTypeVo.getTypeName() || Constant.EMPTY_INTEGER_VALUE == dictionaryTypeVo.getTypeName().trim().length()){
            return new GeneralVo(ErrorListEnum.DICTIONARY_TYPE_NAME_NOT_BLANK,null);
        }
        DictionaryTypeVo queryResult = dictionaryTypeService.queryDictionaryTypeInfoByName(dictionaryTypeVo.getTypeName());
        int result;
        if(null == queryResult){
            initOperateParam(request,response,dictionaryTypeVo,Constant.CREATE_TYPE);
            result = dictionaryTypeService.insertDictionaryType(dictionaryTypeVo);
        }else{
            return new GeneralVo(ErrorListEnum.DICTIONARY_TYPE_NAME_REPEAT,null);
        }
        if(Constant.OPERATE_SUCCESS == result){
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }else{
            return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }
    }

    /**
     * 插入字典信息
     * @param dictionaryVo 字典对象
     * @return 响应结果
     */
    @PutMapping("/dictionary/insertDictionary")
    public GeneralVo insertDictionaryType(HttpServletRequest request,HttpServletResponse response,@RequestBody DictionaryVo dictionaryVo){
        logger.info("插入字典项，参数信息为：{}",dictionaryVo);
        int result = 0;
        //数据字典类型名称校验
        if(null == dictionaryVo.getItemName() || Constant.EMPTY_INTEGER_VALUE == dictionaryVo.getItemName().trim().length()){
            return new GeneralVo(ErrorListEnum.ITEM_NAME_NOT_BLANK,null);
        }
        DictionaryVo queryResult = dictionaryTypeService.queryDictionaryItemInfoByName(dictionaryVo.getItemName());
        if(null == queryResult){
            initOperateParam(request,response,dictionaryVo,Constant.CREATE_TYPE);
            result = dictionaryTypeService.insertDictionaryItem(dictionaryVo);
        }else{
            return new GeneralVo(ErrorListEnum.ITEM_NAME_REPEAT,null);
        }
        if(Constant.OPERATE_SUCCESS == result){
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }else{
            return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }
    }

    /**
     * 修改字典类型信息
     * @param dictionaryTypeVo 字典类型对象
     * @return 响应结果
     */
    @PutMapping("/dictionary/updateDictionaryType")
    public GeneralVo updateDictionaryType(HttpServletRequest request,HttpServletResponse response,@RequestBody DictionaryTypeVo dictionaryTypeVo){
        logger.info("修改字典类型，入参为:{}",dictionaryTypeVo);
        //数据字典类型名称校验
        if(null == dictionaryTypeVo.getTypeName() || Constant.EMPTY_INTEGER_VALUE == dictionaryTypeVo.getTypeName().trim().length()){
            return new GeneralVo(ErrorListEnum.DICTIONARY_TYPE_NAME_NOT_BLANK,null);
        }
        DictionaryTypeVo queryResult = dictionaryTypeService.queryDictionaryTypeInfoByName(dictionaryTypeVo.getTypeName());
        int result;
        if(null != queryResult && (!queryResult.getId().equals(dictionaryTypeVo.getId()))){
            return new GeneralVo(ErrorListEnum.DICTIONARY_TYPE_NAME_REPEAT,null);
        }else{
            initOperateParam(request,response,dictionaryTypeVo,Constant.UPDATE_TYPE);
            result = dictionaryTypeService.updateDictionaryType(dictionaryTypeVo);
        }
        if(Constant.OPERATE_SUCCESS == result){
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }else{
            return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }
    }

    /**
     * 删除字典类型信息
     * @return 响应结果
     */
    @DeleteMapping("/dictionary/deleteDictionaryType")
    public GeneralVo deleteDictionaryType(HttpServletRequest request,HttpServletResponse response,@RequestBody DictionaryTypeVo dictionaryTypeVo){
        int result = dictionaryTypeService.deleteDictionaryType(dictionaryTypeVo);
        if(Constant.OPERATE_SUCCESS == result){
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }else{
            return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }
    }

    /**
     * 按照条件分页查找字典类型信息
     * @return
     */
    @PostMapping("/dictionary/queryDictionaryTypeByPage")
    public ListPageVo queryDictionaryTypeByPage(@RequestBody DictionaryTypeDto dictionaryTypeDto){
        logger.info("分页查询字典类型，入参为：{}",dictionaryTypeDto);
        dictionaryTypeDto.build();
        return new ListPageVo(ErrorListEnum.OPERATE_SUCCESS,dictionaryTypeService.queryDictionaryTypeByPage(dictionaryTypeDto),dictionaryTypeDto.getPageInfo());
    }

    /**
     * 修改字典信息
     * @param dictionaryVo 字典对象
     * @return 响应结果
     */
    @PutMapping("/dictionary/updateDictionary")
    public GeneralVo updateDictionaryType(HttpServletRequest request,HttpServletResponse response,@RequestBody DictionaryVo dictionaryVo){
        logger.info("修改字典条目信息，入参为：{}",dictionaryVo);
        //数据字典类型名称校验
        if(null == dictionaryVo.getItemName() || Constant.EMPTY_INTEGER_VALUE == dictionaryVo.getItemName().trim().length()){
            return new GeneralVo(ErrorListEnum.ITEM_NAME_NOT_BLANK,null);
        }
        DictionaryVo queryResult = dictionaryTypeService.queryDictionaryItemInfoByName(dictionaryVo.getItemName());
        int result;
        if(null != queryResult && (!queryResult.getId().equals(dictionaryVo.getId()))){
            return new GeneralVo(ErrorListEnum.ITEM_NAME_REPEAT,null);
        }else{
            initOperateParam(request,response,dictionaryVo,Constant.UPDATE_TYPE);
            result = dictionaryTypeService.updateDictionaryItem(dictionaryVo);
        }
        if(Constant.OPERATE_SUCCESS == result){
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }else{
            return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }
    }

    /**
     * 删除字典信息
     * @return 响应结果
     */
    @DeleteMapping("/dictionary/deleteDictionaryItem")
    public GeneralVo deleteDictionary(HttpServletRequest request,HttpServletResponse response,@RequestBody DictionaryVo dictionaryVo){
        logger.info("删除字典条目信息，入参为：{}",dictionaryVo);
        int result = dictionaryTypeService.deleteDictionaryItem(dictionaryVo);
        if(Constant.OPERATE_SUCCESS == result){
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
        }else{
            return new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }
    }

    /**
     * 按照条件分页查找字典信息
     * @return
     */
    @PostMapping("/dictionary/queryDictionaryByPage")
    public ListPageVo queryDictionaryByPage(@RequestBody DictionaryDto dictionaryDto){
        logger.info("分页查询字典项，入参为：{}",dictionaryDto);
        dictionaryDto.build();
        return new ListPageVo(ErrorListEnum.OPERATE_SUCCESS,dictionaryTypeService.queryDictionaryByPage(dictionaryDto),dictionaryDto.getPageInfo());
    }

    /**
     * 根据typeId查找字典信息
     * @return
     */
    @GetMapping("/dictionary/queryDictionaryByTypeId/{typeId}")
    public GeneralVo queryDictionaryByPage(@PathVariable("typeId") Integer typeId){
        logger.info("根据字典类型ID查询字典项，入参为：{}",typeId);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,dictionaryTypeService.queryDictionaryByTypeId(typeId));
    }

}
