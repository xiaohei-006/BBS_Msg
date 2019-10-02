package com.bdqn.bbs.service;

import com.bdqn.bbs.domain.Msg;
import com.bdqn.bbs.domain.User;

/**
 * @author: 赖榕
 * @date: 2019/9/29
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.bbs.service
 */
public interface IUserService {

    /**
     * 登录后台
     * @param username 用户名
     * @param password 密码
     * @return 返回登录用户信息，若登录失败返回null值
     */
    User login(String username, String password) throws Exception;

    void register(String username, String password, String email) throws Exception;
}
