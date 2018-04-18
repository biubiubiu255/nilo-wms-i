package com.nilo.wms.dao.flux;

import com.nilo.wms.dto.FluxWeight;
import com.nilo.wms.dto.flux.FluxInbound;
import com.nilo.wms.dto.flux.FluxInboundDetails;
import com.nilo.wms.dto.flux.FluxOutbound;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
@Repository
public interface FluxInboundDao {

    FluxInbound queryByReferenceNo(@Param(value = "customerId") String customerId, @Param(value = "referenceNo") String referenceNo);

    List<FluxInboundDetails> queryDetailsByAsnNo(@Param(value = "customerId") String customerId, @Param(value = "asnNo") String asnNo);

}
