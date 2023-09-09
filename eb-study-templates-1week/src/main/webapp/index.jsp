<%@ page import="com.study.connection.ConnectionTest" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="boardList.jsp">게시판 목록</a>


<%

    ConnectionTest t = new ConnectionTest();
    out.println(t.getConnection());

%>

</body>
</html>
