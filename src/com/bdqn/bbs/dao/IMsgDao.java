package com.bdqn.bbs.dao;

import com.bdqn.bbs.domain.Msg;
import com.bdqn.bbs.web.servlets.MsgServlet;

import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/9/29
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.bbs.dao
 */
public interface IMsgDao {
    /**
     * 发送消息
     * @param msg 封装发送消息的对象
     */
    void send(Msg msg) throws Exception;

    List<Msg> findMsgsByUserName(String username);

    void delMsgByMsgId(String msgId) throws Exception;


    void updateStateByMsgId(String msgid) throws Exception;

    Msg readMsg(String msgid) throws Exception;
}
