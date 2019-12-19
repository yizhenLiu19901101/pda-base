package com.jiaxin.pda.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 菜单类
 * @author milo
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MenuVo extends OperateVo implements Serializable {

    /**
     * 数据库主键
     */
    private String id;

    /**
     * 菜单ID
     */
    private int menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单ID
     */
    private int parentMenuId;

    /**
     * 菜单层级
     */
    private int menuLevel;

    /**
     * 菜单次序
     */
    private int menuNo;

    /**
     * 菜单路径
     */
    private String menuPath;
}
