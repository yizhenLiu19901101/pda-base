package com.jiaxin.pda.entity;

import java.io.Serializable;

public class Page implements Serializable {
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_CURRENT = 1;
    public static final int MAX_PAGE_SIZE = 999;
    private Integer offset;
    private Integer current;
    private int pages;
    private int rows;

    public Page() {
        this.current = Integer.valueOf(1);
        this.offset = Integer.valueOf(10);
    }

    public Page(int current, int offset) {
        this.current = current;
        this.offset = offset;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setOffset(Integer offset) {
        if (null == offset) {
            offset = Integer.valueOf(10);
        }

        if (offset.intValue() > 999) {
            offset = Integer.valueOf(999);
        }

        this.offset = offset;
    }

    public Integer getCurrent() {
        return this.current;
    }

    public void setCurrent(Integer current) {
        this.current = null != current ? current.intValue() : 1;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getRows() {
        return this.rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}