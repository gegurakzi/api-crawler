<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file = "../includes/header.jsp" %>
<div class="container">
    <h1>Schedules</h1>
    <c:choose>
        <c:when test="${apis == null or fn:length(apis) == 0}">
            <div class="container">
                <p>No API exists. Please create API model before schedule jobs.</p>
            </div>
        </c:when>
        <c:otherwise>
            <div class="container">
                <div class="container">
                    <form action="/schedules/create" method="post">
                        <input type="text" name="apiModelId" placeholder="api id">
                        <select name="apiModelGroup">
                            <option value="">select api group</option>
                            <c:forEach items="${groups}" var="group">
                                <option value="${group}">${group}</option>
                            </c:forEach>
                        </select>
                        <input type="text" name="reference" placeholder="file path">
                        <input type="text" name="cronExpression" placeholder="cron expression">
                        <input type="submit" value="submit">
                    </form>
                </div>
                <table>
                    <tr>
                        <th>API id</th>
                        <th>API name</th>
                        <th>API group</th>
                        <th>API URL</th>
                        <th>API header</th>
                    </tr>
                    <c:forEach items="${apis}" var="api">
                        <tr>
                            <td>${api.id}</td>
                            <td>${api.name}</td>
                            <td>${api.apiGroup}</td>
                            <td>${api.url}</td>
                            <td>${api.header}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file = "../includes/footer.jsp" %>