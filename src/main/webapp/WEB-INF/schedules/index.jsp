<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file = "../includes/header.jsp" %>
<div class="container container-main">
    <h2>Schedules</h2>
    <div class="container">
        <a href="schedules/create">create</a>
    </div>
    <div class="container">
        <h3>Active Schedules</h3>
        <c:choose>
            <c:when test="${activeSchedules == null or fn:length(activeSchedules) == 0}">
                <p>No active schedule exists.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>schedule ID</th>
                        <th>API name</th>
                        <th>API group</th>
                        <th>API url</th>
                        <th>file path</th>
                        <th>cron expression</th>
                        <th>action</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${activeSchedules}" var="schedule">
                            <tr>
                                <td>${schedule.id}</td>
                                <td>${schedule.apiModelDto.name}</td>
                                <td>${schedule.apiModelDto.apiGroup}</td>
                                <td>${schedule.apiModelDto.url}</td>
                                <td>${schedule.reference}</td>
                                <td>${schedule.cronExpression}</td>
                                <td><a href="schedules/${schedule.id}/stop">stop schedule</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <h3>Inactive Schedules</h3>
        <c:choose>
            <c:when test="${inactiveSchedules == null or fn:length(inactiveSchedules) == 0}">
                <p>No inactive schedule exists.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>schedule ID</th>
                        <th>API name</th>
                        <th>API group</th>
                        <th>API url</th>
                        <th>file path</th>
                        <th>cron expression</th>
                        <th>action</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${inactiveSchedules}" var="schedule">
                            <tr>
                                <td>${schedule.id}</td>
                                <td>${schedule.apiModelDto.name}</td>
                                <td>${schedule.apiModelDto.apiGroup}</td>
                                <td>${schedule.apiModelDto.url}</td>
                                <td>${schedule.reference}</td>
                                <td>${schedule.cronExpression}</td>
                                <td><a href="schedules/${schedule.id}/run">run schedule</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@ include file = "../includes/footer.jsp" %>