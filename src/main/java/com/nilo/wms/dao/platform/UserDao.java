package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.parameter.UserParameter;
import com.nilo.wms.dto.system.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseDao<Long, User> {

    User queryByUserName(String userName);

    List<User> queryUsers(UserParameter parameter);
}