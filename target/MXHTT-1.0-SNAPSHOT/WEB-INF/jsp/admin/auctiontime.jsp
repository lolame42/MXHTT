<%-- 
    Document   : auctiontime
    Created on : Nov 4, 2021, 2:19:16 PM
    Author     : DAVADO
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-danger">Thống kê những phiên đấu giá chưa kết thúc theo tháng</h1>
<script src="<c:url value="/js/auction.js"/>"></script>
<table class="table">
    <tr>
        <th>Thời gian</th>
        <th>Số lượng</th>

    </tr>
    <tr>
        <c:forEach items="${auctiontime}" var="c">
        <tr>
            <td>${c[0]}/${c[1]}</th>
            <td>${c[2]}</th>

        </tr>
    </c:forEach>
</tr>
</table>
