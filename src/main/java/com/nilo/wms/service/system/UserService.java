package com.nilo.wms.service.system;


import com.nilo.wms.dto.common.PageResult;
import com.nilo.wms.dto.parameter.UserParameter;
import com.nilo.wms.dto.system.User;

public interface UserService {

    void add(User user);

    void update(User user);

    User queryByUserName(String username);

    PageResult<User> queryUsers(UserParameter parameter);

}
