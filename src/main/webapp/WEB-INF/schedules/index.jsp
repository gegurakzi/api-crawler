<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file = "../includes/header.jsp" %>
<div>
    <h2>Schedules</h2>
    <div class="container">
        <a href="schedules/create">create</a>
    </div>
    <div class="container">
        <h4>Active Schedules</h4>
        <table>
            <thead>
                <tr>
                    <th>schedule ID</th>
                    <th>API url</th>
                    <th>cron expression</th>
                    <th>action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${activeSchedules}" var="schedule">
                    <tr>
                        <td>${schedule.id}</td>
                        <td>${schedule.apiModelDto.url}</td>
                        <td>${schedule.cronExpression}</td>
                        <td><a href="schedules/${schedule.id}/stop">stop schedule</a></td>
                    </tr>

                </c:forEach>
            </tbody>
        </table>
        <h4>Inactive Schedules</h4>
        <table>
            <thead>
            <tr>
                <th>schedule ID</th>
                <th>API url</th>
                <th>cron expression</th>
                <th>action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${inactiveSchedules}" var="schedule">
                <tr>
                    <td>${schedule.id}</td>
                    <td>${schedule.apiModelDto.url}</td>
                    <td>${schedule.cronExpression}</td>
                    <td><a href="schedules/${schedule.id}/run">run schedule</a></td>
                </tr>

            </c:forEach>
            </tbody>
        </table>

    </div>
</div>
<%@ include file = "../includes/footer.jsp" %>