<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вывод списка</title>
</head>
<body>

<h2>Список</h2>

<!-- JSP if - условный оператор -->
<c:if test="${not empty lists}">
    <ul>
        <!-- JSP foreach -->
        <c:forEach var="listValue" items="${lists}">
            <li>${listValue}</li>
        </c:forEach>
    </ul>
</c:if>

</body>
</html>
