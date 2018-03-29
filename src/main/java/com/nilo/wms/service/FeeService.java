package com.nilo.wms.service;

import com.nilo.wms.dto.Fee;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */
public interface FeeService {

    List<Fee> queryInboundOrder(String date);

    List<Fee> queryOrderHandlerFee(String date);

    List<Fee> queryOrderReturnHandlerFee(String date);

    List<Fee> queryReturnMerchantHandlerFee(String date);

    void syncToNOS(List<Fee> list, String date, String moneyType);
}
