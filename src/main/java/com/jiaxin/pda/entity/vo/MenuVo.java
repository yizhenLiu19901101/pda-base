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
     * 菜单图标
     */
    private String menuImage;

    /**
     * 菜单次序
     */
    private int menuNo;

    /**
     * 菜单路径
     */
    private String menuPath;

    /**
     * 菜单类型，1代表PC端，2代表移动端
     */
    private int menuType;
}
