package com.jiaxin.pda.entity.vo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * @author milo
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserVo extends OperateVo implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;


}
