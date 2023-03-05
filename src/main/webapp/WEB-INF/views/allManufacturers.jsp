<%--
  Created by IntelliJ IDEA.
  User: Я
  Date: 26.02.2023
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>all manufacturers</title>
</head>
<body>
<c:forEach items="${manufacturers}" var="manufacturer">
    <h3>id: ${manufacturer.id}</h3>
    <h3>name: ${manufacturer.name}</h3>
    <h3>created: ${manufacturer.created}</h3>
    <h3>changed: ${manufacturer.changed}</h3>
    <h4>________________________</h4>
</c:forEach>

</body>
</html>