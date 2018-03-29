package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.NotifyDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotifyDao extends BaseDao<Long, NotifyDO> {

    NotifyDO queryByNotifyId(@Param("notifyId") String notifyId);

    List<NotifyDO> queryFailed();

}
