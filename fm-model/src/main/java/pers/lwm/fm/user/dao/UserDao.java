package pers.lwm.fm.user.dao;


import pers.lwm.fm.user.model.User;

import java.util.List;

/**
 * Created by lwm on 2016/3/22.
 */
public interface UserDao {
    List<User> findAll();
}
