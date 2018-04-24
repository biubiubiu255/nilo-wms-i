package com.nilo.wms.service;

import com.nilo.wms.dto.flux.FluxInbound;
import com.nilo.wms.dto.inbound.InboundHeader;

import java.util.List;

/**
 * Created by admin on 2018/3/19.
 */
public interface InboundService {

    void createInBound(InboundHeader inbound);

    void cancelInBound(String referenceNo);

    void confirmASN(List<InboundHeader> list);

    FluxInbound queryFlux(String referenceNo);
}
