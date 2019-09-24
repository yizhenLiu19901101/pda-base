package com.jiaxin.pda.entity.vo;

import lombok.Data;

/**
 * 基本输出格式
 * @author  milo
 * @date 2018-09-26
 */
@Data
public class BaseVo {
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;

    /**
     * 含参构造器
     * @param code
     * @param msg
     */
    public BaseVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 无参构造器
     */
    public BaseVo() {
    }
}
