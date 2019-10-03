package com.bdqn.bbs.service;

import com.bdqn.bbs.domain.Msg;

import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/9/29
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.bbs.service
 */
public interface IMsgService {

    void sendMsg( Msg msg) throws Exception;
    public List<Msg> findAllMsgsByUserName(String username) throws Exception;

    void delMsg(String msgId) throws Exception;

    /**
     * 读消息，更改消息状态
     * @param msgid
     * @return 返回该条消息的对象
     */
    Msg readMsg(String msgid) throws Exception;
}
