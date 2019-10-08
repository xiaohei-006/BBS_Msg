package com.bdqn.bbs.service.impl;

import com.bdqn.bbs.dao.IMsgDao;
import com.bdqn.bbs.dao.impl.IMsgDaoImpl;
import com.bdqn.bbs.domain.Msg;
import com.bdqn.bbs.domain.User;
import com.bdqn.bbs.service.IMsgService;
import com.bdqn.bbs.web.servlets.MsgServlet;
import com.sun.org.apache.regexp.internal.REUtil;

import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/9/29
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.bbs.service.impl
 */
public class IMsgServiceImpl implements IMsgService {

    private IMsgDao dao = new IMsgDaoImpl();

    @Override
    public Msg readMsg(String msgid) throws Exception {
        try {
            dao.updateStateByMsgId(msgid);
        } catch (Exception e) {
            throw new Exception("修改失败");
        }
        Msg msg = dao.readMsg(msgid);
        if (msg==null){
            throw new Exception("读取消息失败");
        }
        return msg;
    }

    @Override
    public void delMsg(String msgId) throws Exception {
        try {
            dao.delMsgByMsgId(msgId);
        } catch (Exception e) {
            throw new Exception("删除失败");
        }
    }

    @Override
    public void sendMsg(Msg msg) throws Exception {
        try {
            dao.send(msg);
        } catch (Exception e) {
            throw new Exception("发送失败");
        }
    }

    public List<Msg> findAllMsgsByUserName(String username) throws Exception {
        List<Msg> msgs = null;
        try {
            msgs = dao.findMsgsByUserName(username);
        } catch (Exception e) {
            throw new Exception("查询失败");
        }
        if (msgs==null || msgs.size()==0){
            throw new Exception("未获取到信息");
        }
        return msgs;
    }

    public List<Msg> findMsg(String title,String msgcontent,String startDate,String endDate) throws Exception {
        List<Msg> msgs = null;
        try {
            msgs = dao.findMsgByCondition(title, msgcontent, startDate, endDate);
        } catch (Exception e) {
            throw e;
        }
        if (msgs==null || msgs.size()==0){
            throw new Exception("没有查找到数据");
        }
        return msgs;
    }

}
