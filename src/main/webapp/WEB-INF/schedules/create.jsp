<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file = "../includes/header.jsp" %>
<div>
    <h1>Schedules</h1>
    <div class="container">
        <form action="/schedules/create" method="post">
            <input type="text" name="apiModelId" placeholder="api id">
            <input type="text" name="reference" placeholder="save type">
            <input type="text" name="cronExpression" placeholder="cron expression">
            <input type="submit" value="submit">
        </form>
    </div>
    <table>
        <tr>
            <th>API ID</th>
            <th>API URL</th>
            <th>API header</th>
        </tr>
        <c:forEach items="${apis}" var="api">
            <tr>
                <td>${api.id}</td>
                <td>${api.url}</td>
                <td>${api.header}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@ include file = "../includes/footer.jsp" %>