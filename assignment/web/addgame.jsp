<%-- 
    Document   : addgame
    Created on : Jul 22, 2025, 10:20:08 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Game Mới</title>
    <link rel="stylesheet" href="assets/css/addgame.css">
</head>
<body>
    <c:if test="${sessionScope.role != 'admin'}">
        <c:redirect url="MainController?action=Login"/>
    </c:if>
    <h2>Thêm Game Mới</h2>
    
    <form action="MainController" method="post">
        <label>Tiêu đề:</label><br/>
        <input type="text" name="title" required/><br/>

        <label>Mô tả:</label><br/>
        <textarea name="description" rows="5" cols="40" required></textarea><br/>

        <label>Image URL:</label><br/>
        <input type="text" name="imageURL" required/><br/>

        <label>File URL:</label><br/>
        <input type="text" name="fileURL" required/><br/>

        <label>Thể loại:</label><br/>
        <select name="categoryID">
            <c:forEach var="cat" items="${CATEGORIES}">
                <option value="${cat.categoryID}">${cat.categoryName}</option>
            </c:forEach>
        </select><br/><br/>
        <input type="hidden" name="action" value="addgame"/>
        <input type="submit" value="Thêm Game"/>
    </form>
    
    <p style="color: red;">${error}</p>
    <p style="color: green;">${success}</p>

    <p><a href="MainController?action=viewAll">Quay về trang chủ</a></p>
</body>
</html>
