package com.bdqn.bbs.dao.impl;

import com.bdqn.bbs.dao.BaseDao;
import com.bdqn.bbs.dao.MsgDao;
import com.bdqn.bbs.domain.Msg;
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
public class MsgDaoImpl extends BaseDao implements MsgDao {
    @Override
    public void register(Msg msg) throws Exception {
        String sql="insert into msg_userinfo(username,password,email) values(?,?,?)";
        super.update(sql,msg.getUsername(),msg.getPassword(),msg.getEmail());
    }

    @Override
    public List<Msg> findByUserName(String username) throws Exception {
        String sql="select * from msg_userinfo where username = ?";
        ResultSet resultSet = null;
            resultSet = super.query(sql,username);
        List<Msg> msgs = new ArrayList<Msg>();
        while (resultSet.next()){
            msgs.add(this.getInfo(resultSet));
        }
        return msgs;
    }

    @Override
    public Msg findByUserNameAndPassword(String username, String password) {
        String sql="select * from msg_userinfo where username = ? and password = ?";
        Msg msg=null;
        try {
            ResultSet resultSet = super.query(sql, username, password);
            if (resultSet.next()){
                msg=this.getInfo(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    private Msg getInfo(ResultSet resultSet) throws SQLException {
        Msg msg=new Msg();
        msg.setUsername(resultSet.getString("username"));
        msg.setPassword(resultSet.getString("password"));
        msg.setEmail(resultSet.getString("email"));
        return msg;
    }

}
