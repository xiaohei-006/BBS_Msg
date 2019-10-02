package com.bdqn.bbs.domain;

import java.awt.print.PrinterAbortException;

/**
 * @author: 赖榕
 * @date: 2019/9/29
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.bbs.domain
 */
public class User {

    private int msgId ; // 消息Id
    private String username; // 用户名
    private String title; // 标题
    private String msgcontent; // 消息内容
    private String state; // 状态：未读、已读
    private  String sendto; // 收件人
    private String msg_create_date; // 创建时间


    public User(int msgId, String username, String title, String msgcontent, String state, String sendto, String msg_create_date) {
        this.msgId = msgId;
        this.username = username;
        this.title = title;
        this.msgcontent = msgcontent;
        this.state = state;
        this.sendto = sendto;
        this.msg_create_date = msg_create_date;
    }

    public User() {
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSendto() {
        return sendto;
    }

    public void setSendto(String sendto) {
        this.sendto = sendto;
    }

    public String getMsg_create_date() {
        return msg_create_date;
    }

    public void setMsg_create_date(String msg_create_date) {
        this.msg_create_date = msg_create_date;
    }

    @Override
    public String toString() {
        return "User{" +
                "msgId=" + msgId +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", msgcontent='" + msgcontent + '\'' +
                ", state='" + state + '\'' +
                ", sendto='" + sendto + '\'' +
                ", msg_create_date='" + msg_create_date + '\'' +
                '}';
    }
}
