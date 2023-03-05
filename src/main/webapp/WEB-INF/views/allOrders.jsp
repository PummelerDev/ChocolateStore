<%--
  Created by IntelliJ IDEA.
  User: Я
  Date: 27.02.2023
  Time: 00:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>all orders</title>
</head>
<body>
<c:forEach items="${orders}" var="order">
    <h3>id: ${order.id}</h3>
    <h3>name: ${order.orderNumber}</h3>
    <h3>created: ${order.productId}</h3>
    <h3>changed: ${order.customerId}</h3>
    <h3>changed: ${order.quantity}</h3>
    <h3>changed: ${order.created}</h3>
    <h3>changed: ${order.changed}</h3>
    <h3>changed: ${order.cancelled}</h3>
    <h3>changed: ${order.finished}</h3>
    <h4>________________________</h4>
</c:forEach>
</body>
</html>