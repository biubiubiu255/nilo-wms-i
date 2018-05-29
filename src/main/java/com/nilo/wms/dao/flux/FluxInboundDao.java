package com.nilo.wms.dao.flux;

import com.nilo.wms.dto.flux.FluxInbound;
import com.nilo.wms.dto.flux.FluxInboundDetails;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/24.
 */
@Repository
public interface FluxInboundDao {

    FluxInbound queryByReferenceNo(@Param(value = "customerId") String customerId, @Param(value = "referenceNo") String referenceNo);

    List<FluxInboundDetails> queryDetailsByAsnNo(@Param(value = "customerId") String customerId, @Param(value = "referenceNo") String referenceNo);

    List<FluxInboundDetails> queryNotCompleteDetail();

    List<FluxInboundDetails> queryNotCompleteR(@Param("list") Set<String> referenceNos);

}
