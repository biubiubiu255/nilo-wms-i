package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.InterfaceConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterfaceConfigDao extends BaseDao<Long, InterfaceConfig> {

    List<InterfaceConfig> queryByCode(String clientCode);

}
