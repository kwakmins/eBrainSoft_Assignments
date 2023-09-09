<%@ page import="com.domain.comment.dao.CommentRepository" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: alstj
  Date: 2023-09-09
  Time: 오후 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="comment" class="com.domain.comment.entity.Comment" scope="page"/>
<jsp:setProperty name="comment" property="content"/>

<html>
<head>
    <title>웹 게시판</title>
</head>
<body>
<%
    long boardId = Long.parseLong(request.getParameter("boardId"));
    CommentRepository commentRepository = new CommentRepository();
    comment.setCommentId(commentRepository.getNextId());
    comment.setBoardId(boardId);
    comment.setDateTime(LocalDateTime.now());
    if (!commentRepository.createComment(comment)) {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alter('오류 발생')");
        script.println("history.back()");
        script.println("</script>");
    } else {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.printf("location.href='board.jsp?id=%d'", boardId);
        script.println("</script>");
    }
%>

<link href=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
      crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
</body>
</html>
