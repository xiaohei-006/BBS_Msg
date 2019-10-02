package com.bdqn.bbs.dao;

import com.bdqn.bbs.domain.Msg;
import com.bdqn.bbs.domain.User;

import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/9/29
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.bbs.dao
 */
public interface IUserDao {
    /**
     * 根据用户名和密码查找信息
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User findByUserNameAndPassword(String username, String password);

    List<User> findByUserName(String username) throws Exception;

    /**
     * 用户注册
     * @param user
     */
    void register(User user) throws Exception;
}
