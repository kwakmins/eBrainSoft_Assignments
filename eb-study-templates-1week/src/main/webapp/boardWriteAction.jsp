<%@ page import="com.domain.board.dao.BoardRepository" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: alstj
  Date: 2023-09-07
  Time: 오후 2:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="board" class="com.domain.board.entity.Board" scope="page"/>
<jsp:setProperty name="board" property="categoryId"/>
<jsp:setProperty name="board" property="user"/>
<jsp:setProperty name="board" property="password"/>
<jsp:setProperty name="board" property="title"/>
<jsp:setProperty name="board" property="content"/>

<html>
<head>
    <title>웹 게시판</title>
</head>
<body>
<%
    BoardRepository boardRepository = new BoardRepository();
    board.setBoardId(boardRepository.getNextId());
    board.setCreatedAt(LocalDateTime.now());
    board.setUpdatedAt(LocalDateTime.now());
    board.setViewCount(0);
    if (!boardRepository.createBoard(board)) {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alter('오류 발생')");
        script.println("history.back()");
        script.println("</script>");
    } else {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("location.href='boardList.jsp'");
        script.println("</script>");
    }
%>

</body>
</html>
