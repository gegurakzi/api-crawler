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
    <form action="/apis/create" method="post">
        <input type="text" name="url" placeholder="API url" />
        <input type="text" name="header" placeholder="API header" />
        <input type="submit" value="submit"/>
    </form>
</div>
</body>
</html>