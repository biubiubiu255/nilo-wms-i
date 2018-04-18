package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.ClientConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientConfigDao extends BaseDao<Long, ClientConfig> {

    List<ClientConfig> queryAll();

}
