package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.system.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PermissionDao extends BaseDao<Long, Permission> {


    List<Permission> queryByUserId(String userId);


}