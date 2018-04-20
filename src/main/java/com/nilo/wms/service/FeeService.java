package com.nilo.wms.service;

import com.nilo.wms.dto.Fee;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */
public interface FeeService {

    List<Fee> queryStorageFee(String clientCode,String date);

    List<Fee> queryInboundOrder(String clientCode,String date);

    List<Fee> queryOrderHandlerFee(String clientCode,String date);

    List<Fee> queryOrderReturnHandlerFee(String clientCode,String date);

    List<Fee> queryReturnMerchantHandlerFee(String clientCode,String date);

    void syncToNOS(List<Fee> list,String clientCode, String date, String moneyType);
}
