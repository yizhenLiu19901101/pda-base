package com.jiaxin.pda.entity;

import java.io.Serializable;

/**
 * 分页实体类
 * @author  milo
 */
public class PageEntity implements Serializable {
    private Integer current;
    private Integer offset;
    private Page pageInfo;

    public PageEntity() {
    }

    public void build() {
        if (null == this.current) {
            this.current = Integer.valueOf(1);
        }

        if (null == this.offset) {
            this.offset = Integer.valueOf(10);
        }

        this.build(this.current, this.offset);
    }

    public void build(Integer current, Integer offset) {
        if (null == this.pageInfo) {
            this.pageInfo = new Page();
        }

        if (null != current) {
            this.pageInfo.setCurrent(current);
        } else if (null == this.pageInfo.getCurrent()) {
            this.pageInfo.setCurrent(Integer.valueOf(1));
        }

        if (null != offset) {
            this.pageInfo.setOffset(offset);
        } else if (null == this.pageInfo.getOffset()) {
            this.pageInfo.setOffset(Integer.valueOf(10));
        }

    }

    public Integer getCurrent() {
        return this.current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Page getPageInfo() {
        return this.pageInfo;
    }

    public void setPageInfo(Page pageInfo) {
        this.pageInfo = pageInfo;
    }
}
