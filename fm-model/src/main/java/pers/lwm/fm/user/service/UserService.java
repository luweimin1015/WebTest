package pers.lwm.fm.user.service;

import pers.lwm.fm.user.domain.User;

import java.util.List;

/**
 * Created by lwm on 2016/3/15.
 */
public interface UserService {
    List<User> findAll();
    void addUser(User user);
}
