<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file = "../includes/header.jsp" %>
<div class="container">
    <h2>APIs</h2>
    <div class="container">
        <form action="/apis/create" method="post">
            <div class="container">
                <h4>URL</h4>
                <input type="text" name="url" placeholder="API url" />
            </div>
            <div class="container">
                <h4>Header</h4>
                <textarea name="header" placeholder="Comma separated key-value (e.g. key1,value1,key2,value2,...)" rows="4" cols="60"></textarea>
            </div>
            <div class="container">
                <input type="submit" value="submit"/>
            </div>
        </form>
    </div>
</div>
<%@ include file = "../includes/footer.jsp" %>