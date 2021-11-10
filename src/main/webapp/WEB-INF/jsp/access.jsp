<%-- 
    Document   : access
    Created on : Oct 30, 2021, 1:23:34 PM
    Author     : DAVADO
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/access.css"/>"/>

<c:if test= "${bill.type==3}">
    <h1 class="text-danger text-center">Phiên thanh toán đã hoàn thành</h1>
</c:if>
<c:if test= "${bill.type==1}">
    <h1 class="text-danger text-center">Kiểm tra mã code và số tiền trong momo nếu chính xác thì bấm xác nhận</h1>
</c:if>
<div class="bill">
    <div><b>Mã hóa đơn:</b> ${bill.id} </div>
    <div><b>Người thanh toán:</b> ${bill.loginpay.full_name}</div>
    <div><b>Người nhận:</b> ${bill.loginsell.full_name}</div>
    <div><b>Số điện thoại người thanh toán:</b> ${bill.loginpay.phone} </div>
    <div><b>Số điện thoại người nhận:</b> ${bill.loginsell.phone} </div>
    <div><b>Số tiền thanh toán:</b> ${bill.value}k VND</div>
    <div><b>Mã giao dịch phiên thanh toán momo:</b> ${bill.codemomo}</div>
    <c:if test= "${bill.type==1}">
        <div class="button">
            <a class="btn btn-primary" href="<c:url value="/access/${bill.id}"/>">Xác nhận</a>
            <a class="btn btn-primary" href="<c:url value="/request/${bill.id}"/>">Yêu cầu thanh toán lại</a>
        </div>
    </c:if>
</div>
