<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>APIs</title>
</head>
<body>
<%@ include file = "../includes/header.jsp" %>
<h1>APIs</h1>
<div>
    <a href="apis/create">create</a>
</div>
<div>
    <c:forEach items="${apis}" var="api">
        <ul>
            <li>${api.id}</li>
            <li>${api.url}</li>
            <li>${api.header}</li>
        </ul>
    </c:forEach>
</div>
</body>
</html>