package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.platform.outbound.OutboundDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboundItemDao extends BaseDao<Long, OutboundDetail> {

    Long insertBatch(List<OutboundDetail> list);

    List<OutboundDetail> queryByReferenceNo(@Param(value="clientCode")String clientCode, @Param(value="referenceNo")String referenceNo);
}
