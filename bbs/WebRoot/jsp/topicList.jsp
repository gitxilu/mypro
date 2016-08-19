<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
             <td>名称</td>
             <td>作者</td>
             <td>回帖数</td>
             <td>时间</td>
             <td>操作</td>
          </tr>
          <c:forEach var="topic" items="${topicList}">
             <tr> 
                <td>
                  ${topic.name}
                </td>
                <td>
                   ${topic.author}
                </td> 
                <td>
                   ${topic.replycount}
                </td>
                <td>
		             <fmt:formatDate 
		                 value="${topic.time}"
		                 type="both"
		                 dateStyle="default"
		                 timeStyle="default"     
		             />
                </td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/servlet/BbsServlet?method=lookReply&pageNow=1&topicid=${topic.topicid}&typeid=${param.typeid}&token=${param.token}"
                    style="text-decoration:none">查看回复</a>
                </td> 
             </tr>              
          </c:forEach>
       </table>
    <p><a href="${pageContext.servletContext.contextPath}/servlet/BbsServlet?method=findAllType"
    style="text-decoration:none">返回</a></p>  
    <jsp:include page="foot.jsp" />
   </center>
  </body>
</html>
