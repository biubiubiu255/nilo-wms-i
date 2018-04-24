package com.nilo.wms.service;

import com.nilo.wms.dto.*;
import com.nilo.wms.dto.outbound.OutboundHeader;

import java.util.List;

/**
 * Created by admin on 2018/3/19.
 */
public interface BasicDataService {

    void updateSku(List<SkuInfo> list);

    void updateSupplier(List<SupplierInfo> list);

    List<StorageInfo> queryStorage(StorageParam param);

    List<StorageInfo> queryStorageDetail(StorageParam param);

    void lockStorage(OutboundHeader header);

    void unLockStorage(String orderNo);

    void successStorage(OutboundHeader header);

    void syncStock(String clientCode);

    void storageChangeNotify(List<StorageInfo> list);

}
