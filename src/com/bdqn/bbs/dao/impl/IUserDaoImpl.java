package com.bdqn.bbs.dao.impl;

import com.bdqn.bbs.dao.BaseDao;
import com.bdqn.bbs.dao.IUserDao;
import com.bdqn.bbs.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/9/29
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.bbs.dao.impl
 */
public class IUserDaoImpl extends BaseDao implements IUserDao {


    /**
     * @param user
     * @throws Exception
     */
    @Override
    public void register(User user) throws Exception {
        String sql="insert into msg_userinfo(username,password,email) values(?,?,?)";
        super.update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }


    @Override
    public List<User> findByUserName(String username) throws Exception {
        String sql="select * from msg_userinfo where username = ?";
        ResultSet resultSet = null;
            resultSet = super.query(sql,username);
        List<User> users = new ArrayList<User>();
        while (resultSet.next()){
            users.add(this.getInfo(resultSet));
        }
        return users;
    }


    @Override
    public User findByUserNameAndPassword(String username, String password) {
        String sql="select * from msg_userinfo where username = ? and password = ?";
        User user=null;
        try {
            ResultSet resultSet = super.query(sql, username, password);
            if (resultSet.next()){
                user=this.getInfo(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    private User getInfo(ResultSet resultSet) throws SQLException {
        User user =new User();
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }

}
