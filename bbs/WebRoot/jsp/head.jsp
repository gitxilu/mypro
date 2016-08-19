<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <script type="text/javascript">
        function init(){
           window.setInterval("updateTime()",1000);
        }
        function updateTime(){
           var now = new Date().toLocaleString();
           var spanElement = document.getElementById("timeid");
           spanElement.innerHTML = now;
        }
     </script>
  </head>
  
  <body onload="init()">
            欢迎
  		<font color="indigo"><!--
  			${!empty user?user.username:'<font color="red">游客</font>'}
  			-->
  			${!empty user?user.username:'<font color="indigo">游客</font>'}
  		</font>
           光临，当前时间为：<span id="timeid"></span><br>
  	<div style="position:absolute;left:80%;font-size:12px">
		你的IP：${address.ip}<br/>  	
  		归属地：${address.location}<br/>
  		在线人数：${!empty online?online:'0'}<br/>
  		今日流量：${todayFlow}；昨日流量：${yesterday}
  	</div>
     <div align="left">
     <a href="${pageContext.servletContext.contextPath}/servlet/BbsServlet?method=back" style="text-decoration:none">首页</a>
     <c:choose>
         <c:when test="${empty user}">
            <a href="${pageContext.servletContext.contextPath}/servlet/BbsServlet?method=toRegist" 
            style="text-decoration:none">注册</a>
            <a href="${pageContext.servletContext.contextPath}/servlet/BbsServlet?method=toLogin" 
            style="text-decoration:none">登录</a>
         </c:when>
         <c:otherwise>
             <a href="${pageContext.servletContext.contextPath}/servlet/BbsServlet?method=exit"
             style="text-decoration:none">退出</a>
         </c:otherwise>
     </c:choose>
     </div>
  </body>
</html>
