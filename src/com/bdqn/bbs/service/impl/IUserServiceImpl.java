package com.bdqn.bbs.service.impl;

import com.bdqn.bbs.dao.IUserDao;
import com.bdqn.bbs.dao.impl.IUserDaoImpl;
import com.bdqn.bbs.domain.Msg;
import com.bdqn.bbs.domain.User;
import com.bdqn.bbs.service.IUserService;

import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/9/29
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.bbs.service.impl
 */
public class IUserServiceImpl implements IUserService {

    private IUserDao dao = new IUserDaoImpl();

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public User login(String username, String password) throws Exception {
        User user = dao.findByUserNameAndPassword(username, password);
        if (user == null) {
            throw new Exception("用户名或密码错误");
        }
        return user;
    }

    @Override
    public void register(String username, String password, String email) throws Exception {
        List<User> list = null;
        list = dao.findByUserName(username);
        if (list == null || list.size() == 0) {
            dao.register(new User(username, password, email));
        } else {
            throw new Exception("用户名已经被注册");
        }

    }

    @Override
    public List<User> findUsers() throws Exception {
        List<User> users = dao.findAll();
        if (users == null || users.size() == 0) {
            throw new Exception("未查找到用户");
        }
        return users;
    }


}
