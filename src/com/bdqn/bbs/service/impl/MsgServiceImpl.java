package com.bdqn.bbs.service.impl;

import com.bdqn.bbs.dao.MsgDao;
import com.bdqn.bbs.dao.impl.MsgDaoImpl;
import com.bdqn.bbs.domain.Msg;
import com.bdqn.bbs.domain.User;
import com.bdqn.bbs.service.MsgService;

import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/9/29
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.bbs.service.impl
 */
public class MsgServiceImpl implements MsgService {

    private MsgDao dao = new MsgDaoImpl();

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public Msg login(String username, String password) throws Exception {
        Msg msg = dao.findByUserNameAndPassword(username, password);
        if (msg == null) {
            throw new Exception("用户名或密码错误");
        }
        return msg;
    }

    @Override
    public void register(String username, String password, String email) throws Exception {
        List<Msg> list = null;
        list = dao.findByUserName(username);
        if (list==null || list.size() == 0) {
            dao.register(new Msg(username, password, email));
        } else {
            throw new Exception("用户名已经被注册");
        }

    }
}
