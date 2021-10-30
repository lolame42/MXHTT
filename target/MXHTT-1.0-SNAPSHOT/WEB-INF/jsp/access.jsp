<%-- 
    Document   : access
    Created on : Oct 30, 2021, 1:23:34 PM
    Author     : DAVADO
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test= "${bill.type==3}">
    <h1>Phiên thanh toán đã hoàn thành</h1>
</c:if>
<c:if test= "${bill.type==1}">
    <h1>Kiểm tra mã code và số tiền trong momo nếu chính xác thì bấm xác nhận</h1>
</c:if>
<div>Mã hóa đơn: ${bill.id} </div>
<div>Người thanh toán: ${bill.loginpay.full_name}</div>
<div>Người nhận: ${bill.loginsell.full_name}</div>
<div>Số điện thoại người thanh toán: ${bill.loginpay.phone} </div>
<div>Số điện thoại người nhận: ${bill.loginsell.phone} </div>
<div>Số tiền thanh toán: ${bill.value}k VND</div>
<div>Mã giao dịch phiên thanh toán bằng momo: ${bill.codemomo}</div>
<c:if test= "${bill.type==1}">
    <a href="<c:url value="/access/${bill.id}"/>">Xác nhận</a>
    <div>                          </div>
    <a href="<c:url value="/request/${bill.id}"/>">Yêu cầu thanh toán lại</a>
</c:if>
