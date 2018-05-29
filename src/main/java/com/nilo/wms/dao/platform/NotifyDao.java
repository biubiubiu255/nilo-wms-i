package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.common.InterfaceConfig;
import com.nilo.wms.dto.platform.Notify;
import com.nilo.wms.dto.platform.parameter.NotifyParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotifyDao extends BaseDao<Long, Notify> {

    List<Notify> queryBy(NotifyParam param);

    Long queryCountBy(NotifyParam param);

    Notify queryByNotifyId(@Param("notifyId") String notifyId);

    List<Notify> queryFailed();

}
