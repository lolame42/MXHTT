<%-- 
    Document   : baiviettheohashtag
    Created on : Oct 6, 2021, 11:39:43 AM
    Author     : DAVADO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-danger">Thống kê bài đăng theo hashtag</h1>
<script src="<c:url value="/js/auction.js"/>"></script>
<h3 class="text-center">Biểu đồ 5 hashtag được sử dụng nhiều nhất</h3>
<form action="">
    <div class="form-group">
        <input type="text" class="form-control rounded bg-light" name="kw" placeholder="Nhập hashtag muốn tìm" aria-label="Search"
               aria-describedby="search-addon" />

    </div>
    <div class="form-group">
        <label>Từ thời điểm</label>
        <input type="date" name="fromDate" class="form-control">
    </div>
    <div class="form-group">
        <label>Đến thời điểm</label>
        <input type="date" name="toDate" class="form-control">
    </div>
    <input type="submit" value="Tìm" class="btn btn-success">

</form> 
<div>
    <canvas id="myChartStatushashtag"></canvas>
</div>
<table class="table">
    <tr>
        <th>Tên hashtag</th>
        <th>Số lượng bài viết</th>

    </tr>
    <tr>
        <c:forEach items="${stthashtag}" var="c">
        <tr>
            <td>#${c[0]}</th>
            <td>${c[1]}</th>

        </tr>
    </c:forEach>
</tr>
</table>
<script>
    let statusLabels = [], statusInfo = [];
    <c:forEach  items="${stthashtag}" var="c" begin="0" end="5">
    statusLabels.push('${c[0]}')
    statusInfo.push(${c[1]})
    </c:forEach>

    window.onload = function () {
        StatusChart("myChartStatushashtag", statusLabels, statusInfo)
    }
</script>

