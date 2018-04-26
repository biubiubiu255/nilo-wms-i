package com.nilo.wms.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by admin on 2018/3/21.
 */
public class StorageInfo implements Serializable{

    @JSONField(name = "sku")
    private String sku;
    private Integer storage;
    @JSONField(name = "lock_storage")
    private Integer lockStorage;
    @JSONField(name = "store_id")
    private String storeId;
    @JSONField(name = "safe_storage")
    private Integer safeStorage;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public Integer getLockStorage() {
        return lockStorage;
    }

    public void setLockStorage(Integer lockStorage) {
        this.lockStorage = lockStorage;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getSafeStorage() {
        return safeStorage;
    }

    public void setSafeStorage(Integer safeStorage) {
        this.safeStorage = safeStorage;
    }
}
