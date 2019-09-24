package com.jiaxin.pda.controller;

import com.jiaxin.pda.entity.ListPageVo;
import com.jiaxin.pda.entity.dto.MenuDto;
import com.jiaxin.pda.entity.vo.GeneralVo;
import com.jiaxin.pda.entity.vo.MenuVo;
import com.jiaxin.pda.enumeration.ErrorListEnum;
import com.jiaxin.pda.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单控制器类
 * @author milo
 */
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 插入菜单信息
     * @param menuVo 菜单对象
     * @return 响应结果
     */
    @PutMapping("/menu/insertMenu")
    public GeneralVo insertMenu(@RequestBody MenuVo menuVo){
        menuService.insertMenu(menuVo);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 删除菜单信息
     * @param id 菜单
     * @return 响应结果
     */
    @DeleteMapping("/menu/delete/{id}")
    public GeneralVo deleteMenu(@PathVariable("id") String id){
        menuService.deleteMenu(id);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 修改菜单名称
     * @param menuVo 菜单对象
     * @return 响应对象
     */
    @PostMapping("/menu/updateMenuName")
    public GeneralVo updateMenuName(@RequestBody MenuVo menuVo){
        menuService.updateMenuName(menuVo);
        return new GeneralVo(ErrorListEnum.OPERATE_SUCCESS,null);
    }

    /**
     * 分页查找菜单对象
     * @param menuDto 菜单数据传输对象
     * @return 查询对象
     */
    @PostMapping("/menu/queryMenuListByPage")
    public ListPageVo queryMenuListByPage(@RequestBody MenuDto menuDto){
        menuDto.build();
        return new ListPageVo(ErrorListEnum.OPERATE_SUCCESS,menuService.queryMenuListByPage(menuDto),menuDto.getPageInfo());
    }
}
