package com.nilo.wms.service;

import com.nilo.wms.dto.*;
import com.nilo.wms.dto.common.PageResult;
import com.nilo.wms.dto.outbound.OutboundHeader;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/3/19.
 */
public interface BasicDataService {

    void updateSku(List<SkuInfo> list);

    void updateSupplier(List<SupplierInfo> list);

    PageResult<StorageInfo> queryStorageDetail(StorageParam param);

    List<Map<String, String>> lockStorage(OutboundHeader header);

    void unLockStorage(String orderNo);

    void successStorage(OutboundHeader header);

    void syncStock(String clientCode);

    void storageChangeNotify(List<StorageInfo> list);

    void updateStorage(String sku, Integer cacheStorage,Integer lockStorage,Integer safeStorage);

    void sync(List<String> sku);
}
