package com.nilo.wms.service.system.impl;

import com.nilo.wms.dao.platform.UserDao;
import com.nilo.wms.dto.PageResult;
import com.nilo.wms.dto.parameter.UserParameter;
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
    public void add(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User queryByUserName(String username) {
        return userDao.queryByUserName(username);
    }

    @Override
    public PageResult<User> queryUsers(UserParameter parameter) {
        return null;
    }
}
