<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="cn.qidian.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body bgcolor="lightsteelblue">
   <center>
     <jsp:include page="head.jsp" />
      <c:if test="${!empty user}">
          <a href="${pageContext.servletContext.contextPath}/servlet/BbsServlet?method=toAddTopic&typeid=${param.typeid}"
          style="text-decoration:none">发表主题</a>
      </c:if>
       <caption><h2>论坛系统</h2></caption>
       <table border="1">
          <tr>
             <td>编号</td>
             <td>图片</td>
             <td>名称</td>
             <td>点击数</td>
             <td>主题数</td>
             <td>最新主题</td>
             <td>版主</td>
          </tr>
  		<%
  			String token = WebUtil.getRandom();//token = 1111
  			session.setAttribute("token",token);
  		%>
          <c:forEach var="type" items="${typeList}">
   			<tr>
  				<td>
  					${type.typeid}
  				</td>
  				<td>
  					<img src="${type.imagepath}" width="50px" height="30px"/>
  				</td>
  				<td>
  				    <a href="${pageContext.servletContext.contextPath}/servlet/BbsServlet?method=findAllTopicBytypeid&typeid=${type.typeid}&token=${token}" 
  					style="text-decoration:none">${type.name}
  					</a>
  				</td>
  				<td>${type.click}</td>
  				<td>${type.topicnum}</td>
  				<td>
  					<div style="font-size:10px">
  						主题：${type.newtopic.name}<br/>
  						作者：${type.newtopic.author}<br/>
  						时间：
  							<fmt:formatDate
		  						value="${type.newtopic.time}"
		  						type="both"
		  						dateStyle="full"
		  						timeStyle="default"
  							/>				
  							<br/>
  					</div>
  				</td>
  				<td>${type.admin.name}</td>
  			</tr>
          </c:forEach>
       </table>
  	<div style="position:absolute;margin-top:10px;margin-left:85%">
	  	<table  align="center">
	  		<c:forEach 
	  			var="type" 
	  			items="${typeDesc}" 
	  			varStatus="stat"
	  			begin="0"
	  			end="2">
		  		<tr>
					<td>${stat.count}</td>  	
					<td>${type.name}</td>  
					<td>${type.click}</td>  
					<td>
						<img
							height="5px" 
							width="${type.click}px"
							src="${pageContext.request.contextPath}/images/buttom.gif"/>
					</td>  	
		  		</tr>
	  		</c:forEach>
	  	</table>
  	</div>
    <jsp:include page="foot.jsp" />
   </center>
  </body>
</html>
