<%-- 
    Document   : auctionstt
    Created on : Nov 1, 2021, 8:14:17 PM
    Author     : DAVADO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="<c:url value="/js/auction.js"/>"></script>

<h1 class="text-center text-danger">Thống kê phiên đấu giá theo trạng thái</h1>
<table class="table">
    <tr>
        <th>Trạng thái</th>
        <th>Số lượng phiên</th>
    </tr>
    <tr>
        <c:forEach items="${auctionstt}" var="c">
        <tr>
            <c:if test= "${c[0]==0}">
                <td>Chưa thanh toán</th>
                </c:if>
                <c:if test= "${c[0]==1}">
                <td>Chờ xác nhận</th>
                </c:if>
                <c:if test= "${c[0]==2}">
                <td>Yêu cầu thanh toán lại</th>
                </c:if>
                <c:if test= "${c[0]==3}">
                <td>Hoàn thành</th>
                </c:if>
            <td>${c[1]}</th>
        </tr>
        </c:forEach>
    <div>
        <canvas id="myChartAuctionStt"></canvas>
    </div>
    </tr>
</table>
<script>
    let auctionLabels = [], auctionInfo = [];
    <c:forEach  items="${auctionstt}" var="c">
    auctionLabels.push('${c[0]}')
    auctionInfo.push(${c[1]})
    </c:forEach>

    window.onload = function () {
        auctionstt("myChartAuctionStt", auctionLabels, auctionInfo)
    }
</script>