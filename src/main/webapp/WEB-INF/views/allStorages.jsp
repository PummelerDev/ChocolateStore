<%--
  Created by IntelliJ IDEA.
  User: Я
  Date: 27.02.2023
  Time: 00:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>all storages</title>
</head>
<body>
<c:forEach items="${storages}" var="storage">
    <h3>id: ${storage.id}</h3>
    <h3>name: ${storage.name}</h3>
    <h3>created: ${storage.created}</h3>
    <h3>changed: ${storage.changed}</h3>
    <h4>________________________</h4>
</c:forEach>
</body>
</html>
