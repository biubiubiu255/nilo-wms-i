package com.nilo.wms.dao.flux;

import com.nilo.wms.dto.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
@Repository
public interface FluxOutboundDao {

    FluxOutbound queryByReferenceNo(String referenceNo);

    List<FluxWeight> queryWeight(List<String> list);
}
