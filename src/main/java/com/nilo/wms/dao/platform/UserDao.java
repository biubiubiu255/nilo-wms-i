package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.platform.parameter.UserParam;
import com.nilo.wms.dto.platform.system.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseDao<Long, User> {

    User queryByUserName(String userName);

    List<User> queryUsers(UserParam parameter);

    int queryUsersCount(UserParam parameter);

    Integer delete(String userId);
}