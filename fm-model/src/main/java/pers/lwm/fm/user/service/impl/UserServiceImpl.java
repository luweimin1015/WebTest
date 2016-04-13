package pers.lwm.fm.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lwm.fm.user.dao.UserDao;
import pers.lwm.fm.user.domain.User;
import pers.lwm.fm.user.service.UserService;

import java.util.List;

/**
 * Created by lwm on 2016/3/15.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
}
