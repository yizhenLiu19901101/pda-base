package com.jiaxin.pda.mapper;

import com.jiaxin.pda.entity.dto.MenuDto;
import com.jiaxin.pda.entity.vo.MenuVo;
import com.jiaxin.pda.entity.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 菜单映射类
 * @author milo
 */
@Mapper
public interface MenuMapper {

    /**
     * 查询菜单表中最大的ID
     * @return
     */
    Integer selectMaxMenuId();

    /**
     * 插入菜单信息
     * @param menuVo
     * @return
     */
    int insertMenu(MenuVo menuVo);

    /**
     * 修改菜单名
     * @param menuVo 菜单对象
     * @return 是否操作成功
     */
    Integer updateMenuName(MenuVo menuVo);

    /**
     * 删除菜单信息
     * @param  menuVo 菜单对象
     * @return 是否操作成功
     */
    Integer deleteMenu(MenuVo menuVo);

    /**
     * 根据ID查找菜单对象
     * @param id 菜单ID
     * @return 菜单对象
     */
    MenuVo findMenuById(String id);

    /**
     * 分页查询菜单对象
     * @param menuDto
     * @return
     */
    List<MenuVo> queryMenuListByPage(MenuDto menuDto);
}
