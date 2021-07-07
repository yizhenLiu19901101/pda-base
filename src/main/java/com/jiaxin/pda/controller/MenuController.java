package com.jiaxin.pda.controller;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.ListPageVo;
import com.jiaxin.pda.entity.dto.MenuDto;
import com.jiaxin.pda.entity.vo.GeneralVo;
import com.jiaxin.pda.entity.vo.MenuVo;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.service.IMenuService;
import com.jiaxin.pda.swagger.note.MenuNote;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 菜单控制器类
 * @author milo
 */
@RestController
@Api(value = "menu",tags = {"menu_controller"})
@RequestMapping("/menu")
@Slf4j
public class MenuController extends BaseController{

    @Autowired
    private IMenuService menuService;

    /**
     * 插入菜单信息
     * @param menuVo 菜单对象
     * @return 响应结果
     */
    @PutMapping("/insertMenu")
    @ApiImplicitParam(name = "menuVo", value = MenuNote.INSERT_VALUE, required = true, dataType = "MenuVo")
    @ApiOperation(value = "插入菜单信息",notes = MenuNote.INSERT_NOTE)
    public GeneralVo insertMenu(HttpServletRequest request, HttpServletResponse response,@RequestBody MenuVo menuVo){
        try{
            log.info("菜单信息：{}",menuVo);
            int result;
            if(null == menuVo.getMenuName() || Constant.EMPTY_INTEGER_VALUE == menuVo.getMenuName().trim().length()){
                return new GeneralVo(ErrorListEnum.MENU_NAME_NOT_BLANK,null);
            }
            int count = menuService.queryMenuCountByMenuName(menuVo.getMenuName());
            if(count != Constant.EMPTY_INTEGER_VALUE){
                return new GeneralVo(ErrorListEnum.MENU_NAME_REPEAT,null);
            }else{
                initOperateParam(request,response,menuVo,Constant.CREATE_TYPE);
                result = menuService.insertMenu(menuVo);
            }
            return Constant.OPERATE_SUCCESS == result?new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null):new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 删除菜单信息
     * @param menuVo 菜单
     * @return 响应结果
     */
    @DeleteMapping("/delete")
    @ApiImplicitParam(name = "menuVo", value = MenuNote.DELETE_VALUE, required = true, dataType = "MenuVo")
    @ApiOperation(value = "删除菜单信息",notes = MenuNote.DELETE_NOTE)
    public GeneralVo deleteMenu(HttpServletRequest request, HttpServletResponse response,@RequestBody MenuVo menuVo){
        try{
            log.info("删除菜单，入参为：{}",menuVo);
            initOperateParam(request,response,menuVo,Constant.UPDATE_TYPE);
            int result = menuService.deleteMenu(menuVo);
            return Constant.OPERATE_SUCCESS == result?new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null):new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 修改菜单名称
     * @param menuVo 菜单对象
     * @return 响应对象
     */
    @PostMapping("/updateMenuName")
    @ApiImplicitParam(name = "menuVo", value = MenuNote.UPDATE_VALUE, required = true, dataType = "MenuVo")
    @ApiOperation(value = "修改菜单信息",notes = MenuNote.UPDATE_NOTE)
    public GeneralVo updateMenuName(HttpServletRequest request, HttpServletResponse response,@RequestBody MenuVo menuVo){
        try{
            log.info("修改菜单名称，入参为:{}",menuVo);
            if(null == menuVo.getMenuName() || Constant.EMPTY_INTEGER_VALUE == menuVo.getMenuName().trim().length()){
                return new GeneralVo(ErrorListEnum.MENU_NAME_NOT_BLANK,null);
            }
            MenuVo queryResult = menuService.queryMenuInfoByMenuName(menuVo.getMenuName());
            int result;
            if(null != queryResult && (!queryResult.getId().equals(menuVo.getId()))){
                return new GeneralVo(ErrorListEnum.MENU_NAME_REPEAT,null);
            }else{
                initOperateParam(request,response,menuVo,Constant.UPDATE_TYPE);
                result = menuService.updateMenuName(menuVo);
            }
            return Constant.OPERATE_SUCCESS == result?new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null):new GeneralVo(ErrorListEnum.OPERATE_FAIL,null);
        }catch(Exception e){
            e.printStackTrace();
            return new GeneralVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null);
        }
    }

    /**
     * 分页查找菜单对象
     * @param menuDto 菜单数据传输对象
     * @return 查询对象
     */
    @PostMapping("/queryMenuListByPage")
    @ApiImplicitParam(name = "menuDto", value = MenuNote.QUERY_BY_PAGE_VALUE, required = true, dataType = "MenuDto")
    @ApiOperation(value = "分页查找菜单信息",notes = MenuNote.QUERY_BY_PAGE_NOTE)
    public ListPageVo queryMenuListByPage(@RequestBody MenuDto menuDto){
        try{
            menuDto.build();
            log.info("分页查询菜单信息，入参为：{}",menuDto);
            return new ListPageVo(ErrorListEnum.OPERATE_SUCCESS,menuService.queryMenuListByPage(menuDto),menuDto.getPageInfo());
        }catch(Exception e){
            e.printStackTrace();
            return new ListPageVo(ErrorListEnum.SERVER_INTERNAL_ERROR,null,null);
        }
    }
}
