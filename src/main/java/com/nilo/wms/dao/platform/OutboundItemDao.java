package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.platform.outbound.OutboundItemDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboundItemDao extends BaseDao<Long, OutboundItemDO> {

    Long insertBatch(List<OutboundItemDO> list);

    List<OutboundItemDO> queryByReferenceNo(@Param(value="clientCode")String clientCode, @Param(value="referenceNo")String referenceNo);
}
