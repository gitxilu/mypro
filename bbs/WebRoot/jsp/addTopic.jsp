<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
  </head>
  
  <body bgcolor="lightsteelblue">
   <center>
    <jsp:include page="head.jsp" />
  	<form action="${pageContext.servletContext.contextPath}/servlet/BbsServlet?method=addTopic" method="post">
  		<table>
  			<caption><h2>发表主题</h2></caption>
  			<tr>
                <td>
                   <input type="hidden" name="typeid" value="${param.typeid}"/>
                </td>
  			</tr>	
  			<tr>
  				<th>主题名</th>
  				<td width="30"><input type="text" name="name" maxlength="30"/></td>
  			</tr>  			
  			<tr>
  				<th>作者</th>
  				<td><input type="text" name="author" value="${user.username}" readonly/></td>
  			</tr>	
  			<tr>
  				<th>内容</th>
  				<td>
  				    <textarea name="content" rows="7" cols="34"></textarea>
  				</td>
  			</tr>
  			<tr>
  				<td colspan="2" align="center">
  					<input type="submit" value="发表"/>  				
  					<input type="reset" value="取消"/>  				
  				</td>
  			</tr>
  		</table>
  	</form>	
  	<jsp:include page="foot.jsp" />
   </center>
  </body>
</html>
