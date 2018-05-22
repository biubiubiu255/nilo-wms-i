package com.nilo.wms.service.system.impl;

import com.nilo.wms.common.exception.BizErrorCode;
import com.nilo.wms.common.exception.CheckErrorCode;
import com.nilo.wms.common.exception.SysErrorCode;
import com.nilo.wms.common.exception.WMSException;
import com.nilo.wms.common.util.AssertUtil;
import com.nilo.wms.common.util.IdWorker;
import com.nilo.wms.dao.platform.UserDao;
import com.nilo.wms.dto.common.PageResult;
import com.nilo.wms.dto.platform.parameter.UserParam;
import com.nilo.wms.dto.system.User;
import com.nilo.wms.service.system.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void add(User user) {
        AssertUtil.isNotNull(user, SysErrorCode.REQUEST_IS_NULL);
        AssertUtil.isNotBlank(user.getUsername(), CheckErrorCode.USER_NAME_EMPTY);
        //校验userName 是否存在
        User query = userDao.queryByUserName(user.getUsername());
        if (query != null) {
            throw new WMSException(BizErrorCode.USERNAME_EXIST);
        }
        user.setPassword(DigestUtils.md5Hex("12345678"));
        user.setUserId("" + IdWorker.getInstance().nextId());
        user.setStatus(1);
        userDao.insert(user);
    }

    @Override
    public void update(User user) {
        AssertUtil.isNotNull(user, SysErrorCode.REQUEST_IS_NULL);
        AssertUtil.isNotBlank(user.getUserId(), CheckErrorCode.USER_ID_EMPTY);

        userDao.update(user);
    }

    @Override
    public User queryByUserName(String username) {
        return userDao.queryByUserName(username);
    }

    @Override
    public PageResult<User> queryUsers(UserParam parameter) {

        PageResult<User> list = new PageResult<>();
        int count = userDao.queryUsersCount(parameter);
        if (count > 0) {
            list.setCount(count);
            list.setData(userDao.queryUsers(parameter));
        }
        return list;
    }
}
