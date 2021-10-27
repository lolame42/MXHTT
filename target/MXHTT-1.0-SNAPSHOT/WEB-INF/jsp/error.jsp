<%-- 
    Document   : error
    Created on : Oct 25, 2021, 6:24:44 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/error.css"/>"/>

<div class="text">
    <img class="img" src="<c:url value="/images/logo1.png"/>"/>
    <p class="text-info"><b>Trang không hiển thị</b></p>
    <p>Tài nguyên bạn yêu cầu đã bị xóa hoặc ẩn đi</p>
    <a class="otb nav-link" href="<c:url value="/home" />">Về trang chủ</a>
</div>