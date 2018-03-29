package com.nilo.wms.dao.flux;

import com.nilo.wms.dto.FluxOutbound;
import com.nilo.wms.dto.OutboundHeader;
import com.nilo.wms.dto.StorageInfo;
import com.nilo.wms.dto.StorageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
@Repository
public interface FluxOutboundDao {

    FluxOutbound queryByReferenceNo(String referenceNo);

}
