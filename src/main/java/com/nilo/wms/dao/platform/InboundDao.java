package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.InboundDO;
import com.nilo.wms.dto.OutboundDO;
import org.springframework.stereotype.Repository;

@Repository
public interface InboundDao extends BaseDao<Long, InboundDO> {

    InboundDO queryByAsnNo(String asnNo);
}
