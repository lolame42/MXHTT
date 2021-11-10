<%-- 
    Document   : auctiontime
    Created on : Nov 4, 2021, 2:19:16 PM
    Author     : DAVADO
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/css/auctiontime.css"/>"/>

<h1 class="text-center text-danger">Thống kê những phiên đấu giá chưa kết thúc theo tháng</h1>
<script src="<c:url value="/js/auction.js"/>"></script>
<form action="" class="formm">
    <div class="form-group">
        <label><b>Từ thời điểm:</b></label>
        <input type="date" name="fromDate"  class="form-control">
    </div>
    <div class="form-group">
        <label><b>Đến thời điểm:</b></label>
        <input type="date" name="toDate" class="form-control">
    </div>
</form> 
<div class="btn">
    <input type="submit" value="Tìm" class="btn btn-success">
</div>
<h5 class="text-center text-info">biểu đồ 5 mốc thời gian có số lượng đấu giá cao nhất</h5>
<div>
    <canvas id="myChartAuctiontime"></canvas>
</div>
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
<script>
    let auctiontimeLabels = [], auctiontimeInfo = [];
    <c:forEach  items="${auctiontime}" var="c" begin="0" end="4" >
    auctiontimeLabels.push('${c[0]}/${c[1]}')
    auctiontimeInfo.push(${c[2]})
    </c:forEach>

    window.onload = function () {
        AuctionTime("myChartAuctiontime", auctiontimeLabels, auctiontimeInfo)
    }
</script>
