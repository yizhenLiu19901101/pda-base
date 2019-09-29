package com.jiaxin.pda.service.impl;

import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.entity.dto.MenuDto;
import com.jiaxin.pda.entity.vo.MenuVo;
import com.jiaxin.pda.entity.vo.UserVo;
import com.jiaxin.pda.mapper.MenuMapper;
import com.jiaxin.pda.service.MenuService;
import com.jiaxin.pda.util.GenerateUtil;
import com.jiaxin.pda.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单业务实现类
 * @author milo
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int insertMenu(MenuVo menuVo) {
        menuVo.setId(GenerateUtil.generateRandomString());
        menuVo.setMenuId(menuMapper.selectMaxMenuId() + Constant.INCREASE_PACE);
        menuVo.setDeleteFlag(false);
        menuVo.setReversion(Constant.INIT_REVERSION);
        menuVo.setCreatedBy(Constant.SUPER_ADMIN);
        menuVo.setCreatedTime(Constant.NOW);
        this.initUpdateParam(menuVo);
        return menuMapper.insertMenu(menuVo);
    }

    @Override
    public Integer updateMenuName(MenuVo menuVo) {
        this.initUpdateParam(menuVo);
        return menuMapper.updateMenuName(menuVo);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @Override
    public Integer deleteMenu(String id) {
        MenuVo menuVo = menuMapper.findMenuById(id);
        this.initUpdateParam(menuVo);
        menuVo.setDeleteFlag(true);
        return menuMapper.deleteMenu(menuVo);
    }

    @Override
    public List<MenuVo> queryMenuListByPage(MenuDto menuDto) {
        return menuMapper.queryMenuListByPage(menuDto);
    }

    /**
     * 初始化用户更新参数
     * @param menuVo
     */
    private void initUpdateParam(MenuVo menuVo){
        menuVo.setUpdatedBy(Constant.SUPER_ADMIN);
        menuVo.setUpdatedTime(Constant.NOW);
    }
}
