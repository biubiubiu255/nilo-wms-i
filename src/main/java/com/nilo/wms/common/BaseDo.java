package com.nilo.wms.common;

import java.io.Serializable;

/**
 * Created by ronny on 2017/7/31.
 */
public class BaseDo<K> implements Serializable{
    private K id;
    private Long createdTime;
    private Long updatedTime;
    private String createdBy;
    private String updatedBy;
    private String version;

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
