package com.jiaxin.pda.entity.vo;


import com.google.gson.annotations.Expose;
import com.jiaxin.pda.enumeration.ErrorListEnum;



/**
 * 通用数据传输对象
 * @author milo
 * @date 2018-09-26
 * @param <T>
 */
public class GeneralVo<T> extends BaseVo {
    @Expose
    private T body;

    public GeneralVo() {
    }

    public GeneralVo(int code, String msg, T body) {
        this.setCode(code);
        this.setMsg(msg);
        this.setBody(body);
    }

    public GeneralVo(ErrorListEnum listEnum, T body) {
        this.setCode(listEnum.getKey());
        this.setMsg(listEnum.getValue());
        this.setBody(body);
    }

    public T getBody() {
        return this.body;
    }

    public void setBody(T t) {
        this.body = t;
    }

    @Override
    public String toString() {
        String result = '{' + "\"code\":" + this.getCode() + ',' + "\"msg\":\"" + this.getMsg() + "\"," + "\"body\":" + this.getBody() + '}';
        return result;
    }
}
