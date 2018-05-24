package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.platform.ApiLog;
import com.nilo.wms.dto.platform.Notify;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiLogDao extends BaseDao<Long, ApiLog> {

    List<ApiLog> queryBy();

}
