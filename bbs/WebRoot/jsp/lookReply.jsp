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
          <a href="${pageContext.servletContext.contextPath}/servlet/BbsServlet?method=toReply&topicid=${param.topicid}"
          style="text-decoration:none">继续回复</a>
      </c:if>
       <caption><h2>论坛系统</h2></caption>
       <table border="1">
        <tr>
           <td>编号</td>
           <td>名称</td>
           <td>作者</td>
           <td>内容</td>
           <td>时间</td>
        </tr>
        <c:forEach var="reply" items="${page.replyList}">
           <tr>
              <td>
                  ${reply.replyid}
              </td>
              <td>
                  ${reply.name}
              </td>
              <td>
                  ${reply.author}
              </td>
              <td>
                  ${reply.content}
              </td>
              <td>
		             <fmt:formatDate 
		                 value="${reply.time}"
		                 type="both"
		                 dateStyle="default"
		                 timeStyle="default"     
		             /><br>
               </td>
             </tr>              
          </c:forEach>
       </table>
     <p>
        <a href="BbsServlet?method=lookReply&pageNow=1&topicid=${page.topicid}" style="text-decoration:none">首页</a>
        <a href="BbsServlet?method=lookReply&pageNow=${page.pageUp}&topicid=${page.topicid}" style="text-decoration:none">上一页</a>
        <a href="BbsServlet?method=lookReply&pageNow=${page.pageNext}&topicid=${page.topicid}" style="text-decoration:none">下一页</a>
        <a href="BbsServlet?method=lookReply&&pageNow=${page.pageCount}&topicid=${page.topicid}" style="text-decoration:none">尾页</a>
     </p><!--
    <p><a href="${pageContext.servletContext.contextPath}/servlet/BbsServlet?method=backTotopicList&typeid=${param.typeid}"
    style="text-decoration:none">返回</a></p>  
    --><p><a href="${pageContext.servletContext.contextPath}/servlet/BbsServlet?method=findAllTopicBytypeid&typeid=${param.typeid}&token=${param.token}"
    style="text-decoration:none">返回</a></p>  
    <jsp:include page="foot.jsp" />
   </center>
  </body>
</html>
