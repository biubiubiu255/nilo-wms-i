package com.nilo.wms.service.system.impl;

import com.nilo.wms.dao.platform.UserDao;
import com.nilo.wms.dto.system.User;
import com.nilo.wms.service.system.UserService;
import javafx.scene.control.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUserRoles(String merchantId, String userId, Long[] roles) {

    }

    @Override
    public void updateUserNetwork(String merchantId, String userId, Long[] networks) {

    }

    @Override
    public User queryByUserName(String username) {
        return userDao.queryByUserName(username);
    }

    @Override
    public List<User> findUserPageBy(String merchantId, String userName, Pagination pagination) {
        return null;
    }
}
