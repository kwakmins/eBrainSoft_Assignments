<%@ page import="com.domain.board.entity.Board" %>
<%@ page import="com.domain.board.dao.BoardRepository" %>
<%@ page import="com.domain.category.dao.CategoryRepository" %><%--
  Created by IntelliJ IDEA.
  User: alstj
  Date: 2023-09-08
  Time: 오전 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>웹 게시판</title>
</head>
<body>
<%
    long boardId = Long.parseLong(request.getParameter("id"));
    BoardRepository boardRepository = new BoardRepository();
    CategoryRepository categoryRepository = new CategoryRepository();
    Board board = boardRepository.findOne(boardId);
%>
<div class="container mt-5">
    게시판 - 보기

    <!-- 두번째 줄: 이름, 등록일시, 수정일시 -->
    <div class="row mt-3">
        <div class="col-md-6">
            <h6><%=board.getUser()%>
            </h6>
        </div>
        <div class="col-md-6 text-right">
            <h6>등록일시: <%=board.getCreatedAt()%> 수정일시: <%=board.getUpdatedAt()%>

            </h6>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-md-9">
            <h4>[<%=categoryRepository.findNameById(board.getCategoryId())%>] <%=board.getTitle()%>
            </h4>
        </div>
        <div class="col-md-3 text-right">
            <p>조회수 : <%=board.getViewCount()%>
            </p>
        </div>
    </div>
    <hr>

    <div class="row mt-5">
        <div class="col-md-12">
            <p><%=board.getContent()%>
            </p>
        </div>
    </div>
</div>

<link href=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
      crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
</body>
</html>
