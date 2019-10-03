package com.bdqn.bbs.dao.impl;

import com.bdqn.bbs.dao.BaseDao;
import com.bdqn.bbs.dao.IMsgDao;
import com.bdqn.bbs.domain.Msg;
import com.sun.corba.se.spi.transport.ReadTimeouts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class IMsgDaoImpl extends BaseDao implements IMsgDao {
    @Override
    public List<Msg> findMsgsByUserName(String username) {
        String sql = "select * from msg where sendto  = ?";
        List<Msg> msgs = new ArrayList<Msg>();
        try {
            ResultSet resultSet = super.query(sql, username);
            while (resultSet.next()) {
                msgs.add(this.getInfo(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgs;
    }

    @Override
    public Msg readMsg(String msgid) throws Exception {
        String sql="select * from msg where msgId = ?";
        ResultSet resultSet = super.query(sql, msgid);
        if (resultSet.next()) {
            return this.getInfo(resultSet);
        }
        return null;
    }

    @Override
    public void updateStateByMsgId(String msgid) throws Exception {
        String sql = "update msg set state='1' where msgId = ?";
        super.update(sql, msgid);
    }

    @Override
    public void delMsgByMsgId(String msgId) throws Exception {
        String sql = "delete from msg where msgId =?";
        super.update(sql, msgId);
    }

    @Override
    public void send(Msg msg) throws Exception {
        String sql = "insert into msg values(default,?,?,?,?,?,?)";
        super.update(sql, msg.getUsername(), msg.getTitle(), msg.getMsgcontent(), msg.getState(), msg.getSendto(), msg.getMsg_create_date());
    }

    private Msg getInfo(ResultSet rs) throws SQLException {
        Msg msg = new Msg();
        msg.setUsername(rs.getString("username"));
        msg.setState(rs.getString("state"));
        msg.setMsgcontent(rs.getString("msgcontent"));
        msg.setSendto(rs.getString("sendto"));
        msg.setTitle(rs.getString("title"));
        msg.setMsgId(Integer.valueOf(rs.getString("msgId")));
        msg.setMsg_create_date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(rs.getDate("msg_create_date")));
        return msg;
    }
}
