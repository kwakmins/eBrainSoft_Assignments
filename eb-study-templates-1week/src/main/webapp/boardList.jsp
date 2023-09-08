<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.domain.category.dao.CategoryRepository" %>
<%@ page import="com.domain.board.dao.BoardRepository" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.domain.board.entity.Board" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: alstj
  Date: 2023-09-08
  Time: 오전 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>웹 게시판</title>
</head>
<body>

<p>자유 게시판 - 목록</p>

<div class="container border rounded">

    <div class="row align-items-center">
        <div class="col-sm-1">
            등록일
        </div>
        <div class="col-sm-4">
            <label for="startDate"></label>
            <input type="date" id="startDate" name="startDate"
                   required>
            ~
            <label for="endDate"></label>
            <input type="date" id="endDate" name="endDate"
                   required><br><br>
        </div>
        <div class="col-sm-2">
            <select class="form-select" id="categoryId" name="categoryId" required>
                <option value="all">모든 카테고리</option>
                <%
                    Map<Long, String> allCategories = new HashMap<>();
                    CategoryRepository categoryRepository = new CategoryRepository();
                    allCategories = categoryRepository.getAllCategory();

                    for (Long id : allCategories.keySet()) {
                %>
                <option value="<%=id%>">
                    <%=allCategories.get(id)%>
                </option>
                <% }
                %>
            </select>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <input type="text" class="form-control" id="searchContent" name="searchContent"
                       placeholder="검색어를 입력해 주세요.(제목+작성자+내용)" required>
            </div>
        </div>
        <div class="col-sm-1">
            <button type="submit" class="btn btn-primary" id="search">검색</button>
        </div>
    </div>
</div>

<p></p>
<p></p>

<div class="container">
    <div class="row">
        <table class="table table-striped" style="text-align: center; border: solid #dddddd">
            <thead>
            <tr>
                <th class="col-sm-1" style="background-color: #eeeeee; text-align: center;">
                    카테고리
                </th>
                <th class="col-sm-5" style="background-color: #eeeeee; text-align: center;">
                    제목
                </th>
                <th class="col-sm-1" style="background-color: #eeeeee; text-align: center;">
                    작성자
                </th>
                <th class="col-sm-1" style="background-color: #eeeeee; text-align: center;">
                    조회수
                </th>
                <th class="col-sm-2" style="background-color: #eeeeee; text-align: center;">
                    등록 일시
                </th>
                <th class="col-sm-2" style="background-color: #eeeeee; text-align: center;">
                    수정 일시
                </th>
            </tr>
            </thead>
            <tbody>
            <%
                BoardRepository boardRepository = new BoardRepository();
                List<Board> list = boardRepository.getList(1);
                for (int i = 0; i < list.size(); i++) {
            %>
            <tr>
                <td>
                    <%= categoryRepository.findNameById(list.get(i).getCategoryId())%>
                </td>
                <td>
                    <%= list.get(i).getTitle()%>
                </td>
                <td>
                    <%= list.get(i).getUser()%>
                </td>
                <td>
                    <%= list.get(i).getViewCount()%>
                </td>
                <td>
                    <%= list.get(i).getCreatedAt()%>
                </td>
                <td>
                    <%= list.get(i).getUpdatedAt()%>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
    <div class="row-sm-11">
        <a href="boardWrite.jsp" class="btn btn-primary" style="float: right">등록</a>
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
