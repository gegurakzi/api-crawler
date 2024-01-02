<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file = "../includes/header.jsp" %>
<div class="container">
    <h2>Overview</h2>
    <div class="container">
        <h3>Schedule History</h3>
    </div>
    <c:choose>
        <c:when test="${histories == null or fn:length(histories) == 0}">
            <div class="container">
                <p>No histories found.</p>
            </div>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                <tr>
                    <th>timestamp</th>
                    <th>API name</th>
                    <th>API url</th>
                    <th>file directory</th>
                    <th>cron expression</th>
                    <th>state</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${histories}" var="history">
                    <tr>
                        <td>${history.timestamp}</td>
                        <td>${history.apiName}</td>
                        <td>${history.apiUrl}</td>
                        <td>${history.reference}</td>
                        <td>${history.cronExpression}</td>
                        <td>${history.state}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>