
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Categories</title></head>
<body>
<h2>Game Categories</h2>
<ul>
    <c:forEach var="cat" items="${categories}">
        <li><a href="GameListServlet?categoryID=${cat.categoryID}">${cat.categoryName}</a></li>
    </c:forEach>
</ul>
</body>
</html>
