<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body bgcolor="lightsteelblue">
    <center>
    <br><br><br>
    <p><h2>主题列表</h2></p>
     <table border="1">
        <tr>
           <td>编号</td>
           <td>名称</td>
           <td>作者</td>
           <td>内容</td>
           <td>时间</td>
           <td>回复数</td>
        </tr>
        <c:forEach var="topic" items="${page.topicList}">
           <tr>
              <td>${topic.topicid}</td>
              <td>${topic.name}</td>
              <td>${topic.author}</td>
              <td>${topic.content}</td>
              <td>${topic.time}</td>
              <td>${topic.replycount}</td>
           </tr>
        </c:forEach>
     </table>
     <p>
        <a href="Servlet?pageNow=1&typeid=${page.typeid}" style="text-decoration:none">首页</a>
        <a href="Servlet?pageNow=${page.pageUp}&typeid=${page.typeid}" style="text-decoration:none">上一页</a>
        <a href="Servlet?pageNow=${page.pageNext}&typeid=${page.typeid}" style="text-decoration:none">下一页</a>
        <a href="Servlet?pageNow=${page.pageCount}&typeid=${page.typeid}" style="text-decoration:none">尾页</a>
     </p>
    </center>
  </body>
</html>
