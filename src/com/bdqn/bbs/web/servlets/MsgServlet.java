package com.bdqn.bbs.web.servlets;

import com.bdqn.bbs.domain.Msg;
import com.bdqn.bbs.domain.User;
import com.bdqn.bbs.service.IMsgService;
import com.bdqn.bbs.service.impl.IMsgServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: 赖榕
 * @date: 2019/10/2
 * @description: 短消息控制器
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.bbs.web.servlets
 */
public class MsgServlet extends HttpServlet {
    private IMsgService service = new IMsgServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("send".equals(action)) {
            this.send(request, response);
        } else if ("del".equals(action)) {
            this.del(request, response);
        } else if ("findMsgs".equals(action)) {
            this.select(request, response);
        }else if ("read".equals(action)){
            this.read(request,response);
        }else if("selectMsg".equals(action)){
            this.select(request,response);
        }

    }

    private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String msgcontent = request.getParameter("msgcontent");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        try {
            List<Msg> msgs = service.findMsg(title, msgcontent, startDate, endDate);
            request.setAttribute("msgs",msgs);
            request.getRequestDispatcher("main.jsp").forward(request,response);
        } catch (Exception e) {
            request.getSession().setAttribute("error",e.getMessage());
            response.sendRedirect("msgServlet?action=findMsgs");
        }
    }

    private void read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msgid = request.getParameter("msgid");
        String flag="";
        Msg msg=null;
        try {
            msg = service.readMsg(msgid);
        } catch (Exception e) {
            flag=e.getMessage();
        }
        System.out.println(msg);
        System.out.println(flag);
        request.setAttribute("msg",msg);
        request.getRequestDispatcher("readMsg.jsp").forward(request,response);
    }

    private void findAllMsgs(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Msg> msgs = new ArrayList<Msg>();
        try {
             /*
            获取所有关于登录账号的消息
             */
            msgs = new IMsgServiceImpl().findAllMsgsByUserName(((User) request.getSession().getAttribute("loginuser")).getUsername());
        } catch (Exception e) {

        }
        request.setAttribute("msgs", msgs);
        request.getRequestDispatcher("main.jsp").forward(request,response);
    }

    private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msgId = request.getParameter("msgid");
        String flag = "";
        try {
            service.delMsg(msgId);
            flag = "删除成功";
        } catch (Exception e) {
            flag = "删除失败";
        }
        System.out.println(flag);
        HttpSession session = request.getSession();

        List<Msg> msgs = null;
        try {
            msgs = service.findAllMsgsByUserName(((User) session.getAttribute("loginuser")).getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.setAttribute("error", flag);
        session.setAttribute("msgs", msgs);
        response.sendRedirect("msgServlet?action=findMsgs");
    }

    private void send(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Msg msg = new Msg();
        msg.setTitle(request.getParameter("title"));
        msg.setSendto(request.getParameter("toUser"));
        msg.setMsgcontent(request.getParameter("content"));
        msg.setMsg_create_date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        msg.setState("0");
        User loginUser = (User) request.getSession().getAttribute("loginuser");
        msg.setUsername(loginUser.getUsername());
        String flag = "";
        try {
            service.sendMsg(msg);
        } catch (Exception e) {
            flag = e.getMessage();
        }
        response.sendRedirect("main.jsp");
    }

}
