package com.jiaxin.pda.entity.dto;

import com.jiaxin.pda.entity.PageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户数据传输对象
 * @author milo
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserDto extends PageEntity {
    /**
     * 主键
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;
}
