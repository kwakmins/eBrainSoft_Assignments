<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.ebsoft.ebstudytemplates2week.domain.category.application.CategoryService" %><%--
  Created by IntelliJ IDEA.
  User: alstj
  Date: 2023-09-19
  Time: 오전 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>웹 게시판</title>
</head>
<body>
<%
    CategoryService categoryService = new CategoryService(); //피드백 반영 : JSP의 선언문은 맨 위로
%>

<div class="container">
    <div class="highlight" style="padding-top: 20px;">
        <form method="post" action="boardWriteAction.jsp">
            <div class="row mb-3">
                <div class="col-sm-4">
                    <label for="categoryId" class="form-label">카테고리</label>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <select class="form-select" id="categoryId" name="categoryId" required>
                            <option value="" disabled selected>카테고리 선택</option>
                            <%
                                for (String s : categoryService.getAllCategoryNames()) {
                            %>
                            <%--                            피드백 반영 map 사용 X--%>
                            <option value="<%=categoryService.getCategoryIdByName(s)%>">
                                <%=s%>
                            </option>
                            <% }
                            %>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-4">
                    <label for="user" class="form-label">작성자</label>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <input type="text" class="form-control" id="user" name="user" minlength="3"
                               maxlength="5" required>
                    </div>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-4">
                    <label for="password" class="form-label">비밀번호</label>
                </div>
                <div class="col-sm-3">
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="비밀번호" minlength="4" maxlength="16" required
                               pattern="^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{4,16}$">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="form-group">
                        <label for="passwordConfirm"></label>
                        <input type="password" class="form-control" id="passwordConfirm"
                               name="passwordConfirm" placeholder="비밀번호 확인">
                    </div>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-4">
                    <label for="title" class="form-label">제목</label>
                </div>
                <div class="col-sm-8">
                    <div class="form-group">
                        <input type="text" class="form-control" id="title" name="title"
                               minlength="4" maxlength="99" required>
                    </div>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-4 d-flex align-items-center">
                    <label for="content" class="form-label">내용</label>
                </div>
                <div class="col-sm-8">
                    <div class="form-group">
                        <textarea class="form-control" id="content" name="content"
                                  style="height: 350px;" minlength="4" maxlength="2000"
                                  required></textarea>
                    </div>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-11">
                    <a href="boardList.jsp" class="btn btn-danger">취소</a>
                </div>
                <div class="col-sm-1">
                    <button type="submit" class="btn btn-primary" id="save">저장</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
  document.getElementById('save').addEventListener('click', function () {
    var password = document.getElementById('password').value;
    var passwordConfirm = document.getElementById('passwordConfirm').value;

    if (password !== passwordConfirm) {
      alert('비밀번호가 일치하지 않습니다.');
    }
  });
</script>
<link href=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
      crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
</body>
</html>
