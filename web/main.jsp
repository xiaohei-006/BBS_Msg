<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="elements/head.jsp"  %>
<body>
<div id="main">
	<div class="mainbox">
		<div class="title myMessage png"></div>		
		<div class="menu">
			<span>当前用户：<a href="main.jsp">${sessionScope.loginuser.username}</a></span>
			<span><a href="UserServlet?action=findUsers">发短消息</a></span>
			<span><a href="UserServlet?action=logout">退出</a></span>
		</div>
		<div id="error">${error}</div>
		<div class="content messageList">
			<ul>
				<c:forEach var="msg" items="${msgs}">
		  	 		<c:if test="${msg.state == 0}">
		  	 			<li class="unReaded">
		  	 		</c:if>
		  	 		<c:if test="${msg.state == 1}">
		  	 			<li>
		  	 		</c:if>
		  	 		<em><c:out value="${msg.datetime}"/></em>
		  	 		<em><a href=${"UserServlet?action=findUsers&sendto="}${msg.username}${"&state="}${msg.state}>回信</a></em>
					<em><a href=${"MsgServlet?action=del&msgid="}${msg.msgid}>删除</a></em>
					<p>
						<strong><a href=${"MsgServlet?action=read&msgid="}${msg.msgid}${"&state="}${msg.state}><c:out value="${msg.title}"/></a></strong>
						<c:if test="${fn:length(msg.content) > 8}">
			  	 			<c:out value="${fn:substring(msg.content,0,7)}"/>....
			  	 		</c:if>
			  	 		<c:if test="${fn:length(msg.content) <= 8}">
			  	 			<c:out value="${msg.content}"/>
			  	 		</c:if>  
					</p>
			  	 </c:forEach>
		  	 </ul>
		</div>
	</div>
</div>
</body>
</html>
