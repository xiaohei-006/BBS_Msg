<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="elements/head.jsp" %>
<body>
<div id="main">
    <div class="mainbox">

        <div class="title myMessage png"></div>
        <div class="menu">
            <span>当前用户：<a href="msgServlet?action=findMsgs">${sessionScope.loginuser.username}</a></span>
            <span><a href="userServlet?action=findUsers">发短消息</a></span>
            <span><a href="userServlet?action=logout">退出</a></span>
        </div>
        <form action="msgServlet?action=selectMsg" method="post">
            标题：<input type="text" name="title">
            内容：<input type="text" name="msgcontent">
            创建时间：<input type="date" name="startDate"> 到 <input type="date" name="endDate">
            <input type="submit" value="查询">
        </form>
        <div id="error">${error}</div>
        <c:remove var="error"></c:remove>
        <div class="content messageList">
            <ul>
                <c:forEach var="msg" items="${msgs}">
                <c:if test="${msg.state == '0'}">
                <li class="unReaded">
                    </c:if>
                    <c:if test="${msg.state == '1'}">
                <li>
                    </c:if>
                    <em><c:out value="${msg.msg_create_date}"/></em>
                    <em><a href=${"userServlet?action=findUsers&sendto="}${msg.username}${"&state="}${msg.state}>回信</a></em>
                    <em><a href=${"msgServlet?action=del&msgid="}${msg.msgId}>删除</a></em>
                    <p>
                        <strong><a href=${"msgServlet?action=read&msgid="}${msg.msgId}${"&state="}${msg.state}><c:out
                                value="${msg.title}"/></a></strong>
                        <c:if test="${fn:length(msg.msgcontent) > 8}">
                            <c:out value="${fn:substring(msg.msgcontent,0,7)}"/>....
                        </c:if>
                        <c:if test="${fn:length(msg.msgcontent) <= 8}">
                            <c:out value="${msg.msgcontent}"/>
                        </c:if>
                    </p>
                    </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
