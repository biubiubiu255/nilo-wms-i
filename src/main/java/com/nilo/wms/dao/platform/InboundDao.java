package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.inbound.Inbound;
import com.nilo.wms.dto.inbound.InboundDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InboundDao extends BaseDao<Long, Inbound> {

    Inbound queryByReferenceNo(@Param(value="clientCode")String clientCode, @Param(value="referenceNo")String referenceNo);

    int insertDetail(InboundDetail inboundDetail);

    int updateDetail(InboundDetail inboundDetail);

    //int updateDetails(@Param("referenceNo") String referenceNo, @Param("list") List<String> list);

    List<InboundDetail> queryNotComplete();
}
