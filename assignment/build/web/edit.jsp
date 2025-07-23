<%-- 
    Document   : edit
    Created on : Jul 22, 2025, 10:35:08 AM
    Author     : ASUS
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sửa Game</title>
    <link rel="stylesheet" href="assets/css/edit.css">
</head>
<body>
    <c:if test="${sessionScope.role != 'admin'}">
        <c:redirect url="MainController?action=Login"/>
    </c:if>
    <h2>Sửa Game</h2>
    <form action="MainController" method="post">
        <input type="hidden" name="action" value="UpdateGame" />
        <input type="hidden" name="gameID" value="${game.gameID}" />

        Tên Game: <input type="text" name="title" value="${game.title}" required/><br/>
        Mô tả: <textarea name="description" required>${game.description}</textarea><br/>
        Link ảnh: <input type="text" name="imageURL" value="${game.imageURL}" required/><br/>
        Link file: <input type="text" name="fileURL" value="${game.fileURL}" required/><br/>
        Thể loại:
        <select name="categoryID">
            <c:forEach var="c" items="${categories}">
                <option value="${c.categoryID}" <c:if test="${c.categoryID == game.categoryID}">selected</c:if>>${c.categoryName}</option>
            </c:forEach>
        </select><br/>
        <input type="submit" value="Cập nhật"/>
    </form>
    <form action="MainController" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn xóa game này không?');">
        <input type="hidden" name="action" value="DeleteGame"/>
        <input type="hidden" name="gameID" value="${game.gameID}" />
        <input type="submit" value="DeleteGame" />
    </form>
</body>
</html>
