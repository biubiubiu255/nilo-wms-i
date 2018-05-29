package com.nilo.wms.dto.common;

public class Page {

    public void setPageInfo(Page page) {
        this.offset = page.getOffset();
        this.limit = page.getLimit();
    }

    public Page(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public Page() {
    }

    private int count;

    private int offset = 0;

    private int limit = 10;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
