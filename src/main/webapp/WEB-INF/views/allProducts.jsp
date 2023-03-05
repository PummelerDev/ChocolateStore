<%--
  Created by IntelliJ IDEA.
  User: Я
  Date: 27.02.2023
  Time: 00:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>all products</title>
</head>
<body>
<c:forEach items="${products}" var="product">
    <h3>id: ${product.id}</h3>
    <h3>kind: ${product.kind}</h3>
    <h3>topping: ${product.topping}</h3>
    <h3>manufacturerId: ${product.manufacturerId}</h3>
    <h3>name: ${product.name}</h3>
    <h3>description: ${product.description}</h3>
    <h3>weight: ${product.weight}</h3>
    <h3>price: ${product.price}</h3>
    <h3>created: ${product.created}</h3>
    <h3>changed: ${product.changed}</h3>
    <h4>________________________</h4>
</c:forEach>

</body>
</html>