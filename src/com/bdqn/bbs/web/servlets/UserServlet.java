package com.bdqn.bbs.web.servlets;

import com.bdqn.bbs.domain.Msg;
import com.bdqn.bbs.domain.User;
import com.bdqn.bbs.service.IUserService;
import com.bdqn.bbs.service.impl.IUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: 赖榕
 * @date: 2019/9/29
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.bbs.servlets
 */
public class UserServlet extends HttpServlet {


    private IUserService service=new IUserServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("login".equals(action)){
            this.login(request,response);
        }else if("logout".equals(action)){
            this.logout(request,response);
        }else if("regist".equals(action)){
            this.regist(request,response);
        }
    }

    private void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        try {
            service.register(username,password,email);
            request.getSession().setAttribute("error","注册成功");
        } catch (Exception e) {
            request.getSession().setAttribute("error",e.getMessage());
        }
        response.sendRedirect("register.jsp");
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        session.removeAttribute("loginuser");
        response.sendRedirect("login.jsp");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        User loginUser= null;
        try {
            loginUser = service.login(username,password);
        } catch (Exception e) {
            request.getSession().setAttribute("error",e.getMessage());
            response.sendRedirect("index.jsp");
            return;
        }
        request.getSession().setAttribute("loginuser",loginUser);
        response.sendRedirect("main.jsp");

    }
}
