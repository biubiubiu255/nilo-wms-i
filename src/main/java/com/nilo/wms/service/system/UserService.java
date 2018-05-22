package com.nilo.wms.service.system;


import com.nilo.wms.dto.common.PageResult;
import com.nilo.wms.dto.platform.parameter.UserParam;
import com.nilo.wms.dto.system.User;

public interface UserService {

    void add(User user);

    void update(User user);

    User queryByUserName(String username);

    PageResult<User> queryUsers(UserParam parameter);

}
