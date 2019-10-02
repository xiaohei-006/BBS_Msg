<%@page import="cn.jbit.demo.dao.BaseDao"%>
<%@page import="cn.jbit.demo.entity.BBSMessage"%>
<%@page import="cn.jbit.demo.entity.UserInfo"%>
<jsp:useBean id="databaseMgr" class="cn.jbit.demo.dao.BaseDao" scope="session"/>
<jsp:useBean id="ufd" type="cn.jbit.demo.dao.UserinfoDao" class="cn.jbit.demo.dao.impl.UserinfoDaoImpl" scope="session"/>
<jsp:useBean id="bmd" type="cn.jbit.demo.dao.BBSMessageDao" class="cn.jbit.demo.dao.impl.BBSMessageDaoImpl" scope="session"/>
<jsp:useBean id="ufb" type="cn.jbit.demo.biz.UserInfoBiz" class="cn.jbit.demo.biz.impl.UserInfoBizImpl" scope="session"/>
<jsp:useBean id="bmb" type="cn.jbit.demo.biz.BBSMessageBiz" class="cn.jbit.demo.biz.impl.BBSMessageBizImpl" scope="session"/>

