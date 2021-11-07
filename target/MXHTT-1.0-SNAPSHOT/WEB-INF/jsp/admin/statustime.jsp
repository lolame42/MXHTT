

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-danger">Thống kê bài đăng theo thời gian</h1>
<script src="<c:url value="/js/auction.js"/>"></script>
<form action="">
    <div class="form-group">
        <label>Từ thời điểm</label>
        <input type="date" name="fromDate"  class="form-control">
    </div>
    <div class="form-group">
        <label>Đến thời điểm</label>
        <input type="date" name="toDate" class="form-control">
    </div>
    <div>
        <input type="submit" value="Tìm" class="btn btn-success">
    </div>
</form> 
<h5 class="text-center text-info">biểu đồ 5 mốc thời gian có số lượng đăng cao nhất</h5>
<div>
    <canvas id="myChartStatustime"></canvas>
</div>
<table class="table">
    <tr>
        <th>Thời gian</th>
        <th>Số lượng bài viết</th>

    </tr>
    <tr>
        <c:forEach items="${statustime}" var="c">
        <tr>
            <td>${c[0]}/${c[1]}</th>
            <td>${c[2]}</th>

        </tr>
    </c:forEach>
</tr>
</table>

<script>
    let statustimeLabels = [], statustimeInfo = [];
    <c:forEach  items="${statustime}" var="c" begin="0" end="4" >
    statustimeLabels.push('${c[0]}/${c[1]}')
    statustimeInfo.push(${c[2]})
    </c:forEach>

    window.onload = function () {
        StatusTime("myChartStatustime", statustimeLabels, statustimeInfo)
    }
</script>
