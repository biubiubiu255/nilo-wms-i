package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.system.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<Long, User> {

    User queryByUserName(String userName);

}