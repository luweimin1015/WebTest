package pers.lwm.fm.user.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.lwm.fm.user.dao.UserDao;
import pers.lwm.fm.user.model.User;

import java.util.List;


/**
 * Created by lwm on 2016/3/22.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<User> findAll() {
        return sqlSession.selectList("pers.lwm.fm.mapper.UserMapper.findAll");
    }
}
