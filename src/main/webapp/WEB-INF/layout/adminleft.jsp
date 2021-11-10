<%-- 
    Document   : adminleft
    Created on : Oct 6, 2021, 11:35:19 AM
    Author     : DAVADO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/css/adminleft.css"/>"/>

<nav class="navbar">
    <ul class="navbar-nav">
        <li class="text-white text-center"><h2>ADMIN</h2></li>
        <hr/>
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value="/admin/userreport"/>">Thống kê tài khoản bị report</a>
        </li>
        <hr/>
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value="/admin/stthashtag"/>">Thống kê bài đăng theo hashtag</a>
        </li>
        <hr/>
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value="/admin/statustime"/>">Thống kê bài đăng theo thời gian</a>
        </li>
        <hr/>
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value="/admin/auctionstt"/>">Thống kê các phiên đấu giá theo tỉ lệ đã thanh toán</a>
        </li>
        <hr/>
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value="/admin/auctiontime"/>">Thống kê các phiên đấu giá chưa kết thúc theo thời gian</a>
        </li>
        <hr/>
    </ul>

</nav>