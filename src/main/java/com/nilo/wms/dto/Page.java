package com.nilo.wms.dto;

public class Page{

    public Page(int offset,int limit){
        this.offset = offset;
        this.limit = limit;
    }

    private int offset;

    private int limit;

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
