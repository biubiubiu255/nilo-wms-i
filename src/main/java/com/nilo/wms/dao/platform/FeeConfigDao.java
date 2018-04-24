package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.fee.FeeConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeConfigDao extends BaseDao<Long, FeeConfig> {

    List<FeeConfig> queryByClientCode(String clientCode);
}
