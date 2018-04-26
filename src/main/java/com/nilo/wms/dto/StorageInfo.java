package com.nilo.wms.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by admin on 2018/3/21.
 */
public class StorageInfo implements Serializable{

    @JSONField(name = "sku")
    private String sku;
    private int storage;
    @JSONField(name = "lock_storage")
    private int lockStorage;
    @JSONField(name = "store_id")
    private String storeId;
    @JSONField(name = "safe_storage")
    private int safeStorage;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getLockStorage() {
        return lockStorage;
    }

    public void setLockStorage(int lockStorage) {
        this.lockStorage = lockStorage;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public int getSafeStorage() {
        return safeStorage;
    }

    public void setSafeStorage(int safeStorage) {
        this.safeStorage = safeStorage;
    }
}
