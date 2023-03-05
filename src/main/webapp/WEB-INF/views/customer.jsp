<%--
  Created by IntelliJ IDEA.
  User: Я
  Date: 26.02.2023
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>customer</title>
</head>
<body>
    <h3>id: ${customer.id} </h3>
    <h3>firstName: ${customer.firstName} </h3>
    <h3>lastName: ${customer.lastName} </h3>
    <h3>address: ${customer.address} </h3>
    <h3>phone: ${customer.phone} </h3>
    <h3>email: ${customer.email} </h3>
    <h3>purchaseAmount: ${customer.purchaseAmount} </h3>
    <h3>login: ${customer.login} </h3>
    <h3>password: ${customer.password} </h3>
    <h3>created: ${customer.created} </h3>
    <h3>changed: ${customer.changed} </h3>
    <h3>isDeleted: ${customer.deleted} </h3>
</body>
</html>
