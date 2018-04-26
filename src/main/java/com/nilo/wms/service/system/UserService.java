package com.nilo.wms.service.system;


import com.nilo.wms.dto.PageResult;
import com.nilo.wms.dto.system.User;
import javafx.scene.control.Pagination;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void updateUserRoles(String merchantId, String userId, Long[] roles);

    void updateUserNetwork(String merchantId, String userId, Long[] networks);

    User queryByUserName(String username);

    PageResult<User> findUserPageBy(String merchantId, String userName, Pagination pagination);

}
