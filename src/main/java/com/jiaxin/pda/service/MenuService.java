package com.jiaxin.pda.service;

import com.jiaxin.pda.entity.dto.MenuDto;
import com.jiaxin.pda.entity.vo.MenuVo;

import java.util.List;

/**
 * 菜单业务类
 * @author milo
 */
public interface MenuService {


    /**
     * 插入菜单信息
     * @param menuVo
     * @return
     */
    int insertMenu(MenuVo menuVo);

    /**
     * 修改菜单名
     * @param menuVo
     * @return
     */
    Integer updateMenuName(MenuVo menuVo);

    /**
     * 删除菜单信息
     */
    Integer deleteMenu(MenuVo menuVo);

    /**
     * 分页查询菜单对象
     * @param menuDto
     * @return
     */
    List<MenuVo> queryMenuListByPage(MenuDto menuDto);

    /**
     * 根据名称查询菜单数量
     * @param menuName
     * @return
     */
    int queryMenuCountByMenuName(String menuName);

    /**
     * 根据名称查询菜单信息
     * @param menuName
     * @return
     */
    MenuVo queryMenuInfoByMenuName(String menuName);

    /**
     * 根据ID列表查询菜单信息
     * @param menuIdList
     * @return
     */
    List<MenuVo> queryMenuListByMenuIdList(List<String> menuIdList);
}
