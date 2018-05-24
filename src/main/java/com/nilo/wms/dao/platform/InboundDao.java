package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.platform.inbound.Inbound;
import com.nilo.wms.dto.platform.inbound.InboundDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InboundDao extends BaseDao<Long, Inbound> {

    Inbound queryByReferenceNo(@Param(value="clientCode")String clientCode, @Param(value="referenceNo")String referenceNo);

    int insertDetail(InboundDetail inboundDetail);

    int updateDetail(InboundDetail inboundDetail);
}
