<%--
  Created by IntelliJ IDEA.
  User: alstj
  Date: 2023-09-22
  Time: 오후 4:04
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html>
<head>
    <title>웹 게시판</title>
</head>
<body>

<div class="container mt-5">
    게시판 - 보기

    <div class="row mt-3">
        <div class="col-md-6">
            <h6>
                <c:out value="${board.user}"/>
            </h6>
        </div>
        <div class="col-md-6 text-right">
            <h6>등록일시: <c:out value="${board.createdAt}"/> 수정일시: <c:out value="${board.updatedAt}"/>
            </h6>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-md-9">
            <h4>[<c:out value="${board.category.categoryName}"/>] <c:out value="${board.title}"/>
            </h4>
        </div>
        <div class="col-md-3 text-right">
            <p>조회수 : <c:out value="${board.viewCount}"/>
            </p>
        </div>
    </div>
    <hr>

    <div class="row mt-5">
        <div class="col-md-12">
            <p><c:out value="${board.content}"/>
            </p>
        </div>
    </div>
    <div class="row mt-4" style="background-color: #dddddd">
        <div class="col-md-12">
        </div>
        <form method="post" action="" class="row mt-3">
            <div class="col-md-11">
                <label for="content"></label>
                <input type="text" class="form-control" placeholder="댓글을 입력해 주세요" id="content"
                       name="content" minlength="1" required>
            </div>
            <div class="col-md-1 text-right">
                <button type="submit" class="btn btn-primary" id="save">등록</button>
            </div>
        </form>
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