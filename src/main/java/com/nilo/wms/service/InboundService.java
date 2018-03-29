package com.nilo.wms.service;

import com.nilo.wms.dto.FluxInbound;
import com.nilo.wms.dto.InboundHeader;
import com.nilo.wms.dto.OutboundHeader;

import java.util.List;

/**
 * Created by admin on 2018/3/19.
 */
public interface InboundService {

    void createInBound(InboundHeader inbound);

    void cancelInBound(String asnNo);

    void confirmASN(List<InboundHeader> list);

    FluxInbound queryFlux(String asnNo);
}
