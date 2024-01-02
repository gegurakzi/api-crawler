<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file = "../includes/header.jsp" %>
<div class="container">
    <h2>APIs</h2>
    <div class="container">
        <a href="apis/create">create</a>
    </div>
    <div class="container">
        <c:choose>
            <c:when test="${apis == null or fn:length(apis) == 0}">
                <p>No API exists.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>API id</th>
                            <th>name</th>
                            <th>URL</th>
                            <th>header</th>
                            <th>action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${apis}" var="api">
                            <tr>
                                <td>${api.id}</td>
                                <td>${api.name}</td>
                                <td>${api.url}</td>
                                <td>${api.header}</td>
                                <td><a href="apis/${api.id}">update</a>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@ include file = "../includes/footer.jsp" %>