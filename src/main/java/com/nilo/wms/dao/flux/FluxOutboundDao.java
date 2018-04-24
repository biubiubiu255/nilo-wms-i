package com.nilo.wms.dao.flux;

import com.nilo.wms.dto.flux.FluxOutbound;
import com.nilo.wms.dto.flux.FluxWeight;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
@Repository
public interface FluxOutboundDao {

    FluxOutbound queryByReferenceNo(@Param(value = "customerId") String customerId, @Param(value = "referenceNo") String referenceNo);

    List<FluxWeight> queryWeight(List<String> list);
}
