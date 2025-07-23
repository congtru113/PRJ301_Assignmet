<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ Game</title>
        <link rel="stylesheet" href="assets/css/search.css">
    <style>
        .game-card {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 15px;
            margin: 10px;
            width: 300px;
            display: inline-block;
            vertical-align: top;
        }
        .game-card img {
            width: 100%;
            height: 180px;
            object-fit: cover;
        }
    </style>
</head>
<body>
    <h2>Chào mừng ${sessionScope.username} đến với kho game!</h2>
    <a href="MainController?action=logout">Logout</a>
    <c:if test="${sessionScope.role == 'admin'}">
    | <a href="MainController?action=showAddGameForm">Add Game</a>
    </c:if>
    <form action="MainController" method="get">
        <input type="hidden" name="action" value="search"/>
        <input type="text" name="keyword" value="${param.keyword}"/>
        <input type="submit" value="search"/>
    </form>
    <c:if test="${not empty requestScope.message}">
        <p class="text-danger">${requestScope.message}</p>
    </c:if>
    <c:forEach var="game" items="${GAMES}">
        <div class="game-card">
            <img src="${game.imageURL}" alt="game image" />
            <h3>${game.title}</h3>
            <p>${game.description}</p>
            <a href="MainController?action=GameDetail&gameID=${game.gameID}">Chi tiết</a>
            <c:if test="${not empty sessionScope.username}">
                |   <a href="${game.fileURL}">Tải xuống</a>
            </c:if>
            <c:if test="${sessionScope.role == 'admin'}">
                | <a href="MainController?action=EditGameForm&gameID=${game.gameID}">Sửa</a>
            </c:if>
        </div>
    </c:forEach>
</body>
</html>
