package com.nilo.wms.dto.platform.parameter;

import com.nilo.wms.dto.common.Page;

/**
 * Created by admin on 2018/4/26.
 */
public class StorageParameter {

    private String sku;

    private String storeName;

    private Page page;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getOffset() {
        return this.page.getOffset();
    }

    public int getLimit() {
        return this.page.getLimit();
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
