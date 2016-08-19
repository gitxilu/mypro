<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
  </head>
  
  <body bgcolor="lightsteelblue">
   <center>
    <jsp:include page="head.jsp" />
  	<form action="${pageContext.servletContext.contextPath}/servlet/BbsServlet?method=reply&topicid=${param.topicid}" method="post">
  		<table>
  			<caption><h2>主题回复</h2></caption>
  			<tr>
                <td>
                   <input type="hidden" name="topicid" value="${param.topicid}"/>
                </td>
  			</tr>	
  			<tr>
  				<th>主题名</th>
  				<td width="30"><input type="text" name="name" /></td>
  			</tr>  			
  			<tr>
  				<th>作者</th>
  				<td><input type="text" name="author" value="${user.username}" readonly/></td>
  			</tr>	
  			<tr>
  				<th>内容</th>
  				<td>
  				    <textarea name="content" rows="5" cols="34"></textarea>
  				</td>
  			</tr>
  			<tr>
  				<td colspan="2" align="center">
  					<input type="submit" value="回复"/>  				
  					<input type="reset" value="取消"/>  				
  				</td>
  			</tr>
  		</table>
  	</form>	
  	<jsp:include page="foot.jsp" />
   </center>
  </body>
</html>
