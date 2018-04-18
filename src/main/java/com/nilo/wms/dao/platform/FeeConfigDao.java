package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.FeeConfig;
import com.nilo.wms.dto.OutboundDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeConfigDao extends BaseDao<Long, FeeConfig> {

    List<FeeConfig> queryByClientCode(String clientCode);
}
