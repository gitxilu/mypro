<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
  </head>
  
  <body bgcolor="lightsteelblue">
   <center>
    <jsp:include page="head.jsp" />
  	<form action="${pageContext.request.contextPath}/servlet/BbsServlet?method=login" method="post">
  		<table>
  			<caption><h2>用户登录</h2></caption>
  			<tr>
  				<th>用户名</th>
  				<td><input type="text" name="username" /></td>
  			</tr>	
  			<tr>
  				<th>密码</th>
  				<td><input type="password" name="password" /></td>
  			</tr>	
  			<tr>
  				<td colspan="2" align="center">
  					<input type="submit" value="登录"/>  				
  					<input type="reset" value="取消"/>  				
  				</td>
  			</tr>
  		</table>
  	</form>	
  	<jsp:include page="foot.jsp" />
   </center>
  </body>
</html>
