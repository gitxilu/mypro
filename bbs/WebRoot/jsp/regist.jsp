<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body bgcolor="lightsteelblue">
   <center>
     <jsp:include page="head.jsp"/>
       <form action="${pageContext.servletContext.contextPath}/servlet/BbsServlet?method=regist" method="post">
           <table>
              <caption><h2>用户注册</h2></caption>
  			<tr>
  				<th>用户名</th>
  				<td><input value="${registForm.username}" type="text" name="username" maxlength="10"/></td>
  				<td>
  					${registForm.errors.username}
  				</td>
  			</tr>	
  			<tr>
  				<th>密码</th>
  				<td><input type="password" name="password" maxlength="6"/></td>
  				<td>
  					${registForm.errors.password}
  				</td>
  			</tr>	
  			<tr>
  				<th>性别</th>
  				<td>
  					<input type="radio" name="gender" value="男"/>男
  					<input type="radio" name="gender" value="女" checked/>女  				
  				</td>
  			</tr>	
  			<tr>
  				<th>邮箱</th>
  				<td>
  					<input value="${registForm.email}" type="text" name="email"/>  				
  				</td>
  				<td>
  					${registForm.errors.email}
  				</td>
  			</tr>	
              <tr>
                <td colspan="2" align="center">
                   <input type="submit" value="注册" />
                   <input type="reset" value="取消" />
                </td>
              </tr>
           </table> 
       </form>
     <jsp:include page="foot.jsp"/>
   </center>
  </body>
</html>
