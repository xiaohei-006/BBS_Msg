<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="elements/head.jsp" %>
<html>
<head>
    <title>读短消息</title>
</head>
<body>
<div id="main">
    <div class="mainbox">
        <div class="title readMessage png"></div>
        <div class="menu">
            <span>当前用户：<a href="msgServlet?action=findMsgs">${sessionScope.loginuser.username}</a></span>
            <span><a href="userServlet?action=findUsers">发短消息</a></span>
            <span><a href="userServlet?action=logout">退出</a></span>
        </div>
        <div class="content">
            <div class="message">
                <div class="tmenu">
                    <ul class="clearfix">
                        <li>题目：${msg.title} </li>
                        <li>来自：${msg.sendto}</li>
                        <li>时间：${msg.msg_create_date}</li>
                    </ul>
                </div>
                <div class="view">
                    <p>${msg.msgcontent}</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
