package com.nilo.wms.service;

import com.nilo.wms.dto.*;

import java.util.List;

/**
 * Created by admin on 2018/3/19.
 */
public interface BasicDataService {

    void updateSku(List<SkuInfo> list);

    void updateSupplier(SupplierInfo supplierInfo);

    List<StorageInfo> queryStorage(StorageParam param);

    List<StorageInfo> queryStorageDetail(StorageParam param);

    void lockStorage(OutboundHeader header);

    void unLockStorage(String orderNo, String customerId, String warehouseId);

    void successStorage(String orderNo, String customerId, String warehouseId);
}
