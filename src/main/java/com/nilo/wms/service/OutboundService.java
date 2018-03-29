package com.nilo.wms.service;

import com.nilo.wms.dto.FluxOutbound;
import com.nilo.wms.dto.OutboundHeader;

import java.util.List;

/**
 * Created by admin on 2018/3/19.
 */
public interface OutboundService {

    void createOutBound(OutboundHeader outBound);

    void cancelOutBound(String orderNo);

    void confirmSO(List<String> list, boolean result);

    FluxOutbound queryFlux(String orderNo);
}
