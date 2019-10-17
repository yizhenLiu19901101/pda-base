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
            return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
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
    public GeneralVo insertDictionaryType(@RequestBody DictionaryVo dictionaryVo){
        dictionaryTypeService.insertDictionaryItem(dictionaryVo);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 修改字典类型信息
     * @param dictionaryTypeVo 字典类型对象
     * @return 响应结果
     */
    @PutMapping("/dictionary/updateDictionaryType")
    public GeneralVo updateDictionaryType(@RequestBody DictionaryTypeVo dictionaryTypeVo){
        dictionaryTypeService.updateDictionaryType(dictionaryTypeVo);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 删除字典类型信息
     * @return 响应结果
     */
    @DeleteMapping("/dictionary/deleteDictionaryType/{id}")
    public GeneralVo deleteDictionaryType(@PathVariable("id") String id){
        dictionaryTypeService.deleteDictionaryType(id);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 按照条件分页查找字典类型信息
     * @return
     */
    @PostMapping("/dictionary/queryDictionaryTypeByPage")
    public ListPageVo queryDictionaryTypeByPage(@RequestBody DictionaryTypeDto dictionaryTypeDto){
        dictionaryTypeDto.build();
        return new ListPageVo(ErrorListEnum.OPERATE_SUCCESS,dictionaryTypeService.queryDictionaryTypeByPage(dictionaryTypeDto),dictionaryTypeDto.getPageInfo());
    }

    /**
     * 修改字典信息
     * @param dictionaryVo 字典对象
     * @return 响应结果
     */
    @PutMapping("/dictionary/updateDictionary")
    public GeneralVo updateDictionaryType(@RequestBody DictionaryVo dictionaryVo){
        dictionaryTypeService.updateDictionaryItem(dictionaryVo);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 删除字典信息
     * @return 响应结果
     */
    @DeleteMapping("/dictionary/deleteDictionaryItem/{id}")
    public GeneralVo deleteDictionary(@PathVariable("id") String id){
        dictionaryTypeService.deleteDictionaryItem(id);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 按照条件分页查找字典信息
     * @return
     */
    @PostMapping("/dictionary/queryDictionaryByPage")
    public ListPageVo queryDictionaryByPage(@RequestBody DictionaryDto dictionaryDto){
        dictionaryDto.build();
        return new ListPageVo(ErrorListEnum.OPERATE_SUCCESS,dictionaryTypeService.queryDictionaryByPage(dictionaryDto),dictionaryDto.getPageInfo());
    }

    /**
     * 根据typeId查找字典信息
     * @return
     */
    @GetMapping("/dictionary/queryDictionaryByTypeId/{typeId}")
    public GeneralVo queryDictionaryByPage(@PathVariable("typeId") Integer typeId){
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,dictionaryTypeService.queryDictionaryByTypeId(typeId));
    }

}
