<%-- 
    Document   : header
    Created on : Aug 23, 2021, 2:12:30 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">

    <ul class="navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="/">Trang Chủ</a>
        </li>
        <c:forEach var="cat" items="${logins}">
            <li class="nav-item">
                <a class="nav-link" href="#">${cat.id}</a>
            </li>

        </c:forEach>
       

    </ul>
</nav>