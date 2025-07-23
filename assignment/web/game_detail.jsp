<%-- 
    Document   : welcome
    Created on : Apr 26, 2025, 8:58:34 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết game</title>
    <link rel="stylesheet" href="assets/css/game_detail.css">
</head>
<body>
    <h2>${GAME.title}</h2>
    <img src="${GAME.imageURL}" alt="game image" width="400"/><br/><br/>
    <p>${GAME.description}</p>
    <c:if test="${not empty sessionScope.username}">
        <a href="${GAME.fileURL}">Tải game</a><br/><br/>
    </c:if>
    <c:if test="${empty sessionScope.username}">
        <p style="color: red;">Hãy đăng nhập để tải game</p><br/>
        <a href="login.jsp">Login</a>
    </c:if>
    <a href="MainController?action=viewAll">Quay lại danh sách</a>
</body>
</html>