<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="elements/head.jsp"  %>
<html>
  <head>    
    <title>读短消息</title>	    
  </head>
	<body>
		<div id="main">
			<div class="mainbox">
				<div class="title readMessage png"></div>
				<div class="menu">
					<span>当前用户：<a href="main.jsp">${sessionScope.loginuser}</a></span>
					<span><a href="UserServlet?action=findUsers">发短消息</a></span>
					<span><a href="UserServlet?action=logout">退出</a></span>
				</div>
				<div class="content">
					<div class="message">
						<c:forEach var="msg" items="${msg}">
							<div class="tmenu">
								<ul class="clearfix">
									<li>题目：${msg.title} </li>
									<li>来自：${msg.sendto}</li>
									<li>时间：${msg.datetime}</li>									
								</ul>
							</div>
					  	 	<div class="view">
								<p>${msg.content}</p>
							</div>
				  	 	</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
