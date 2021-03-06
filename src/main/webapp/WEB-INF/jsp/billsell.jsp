<%-- 
    Document   : billsell
    Created on : Oct 21, 2021, 11:10:53 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/billsell.css"/>"/>

<c:if test= "${err!=null}}">
    <td>${err}</th>  
</c:if>

<div class="container">
    <h1 class="text-center text-danger">Hóa Đơn</h1>
    <div class="row">
        <div class="mybill col-md-6 col-xs-12"> 
            <h2 class="text-center text-info">Hóa đơn bạn chờ thanh toán</h2>
            <table class="table">
                <tr>
                    <th>Mã đơn</th>
                    <th>Người mua</th>
                    <th>Số tiền</th>
                    <th>Trạng Thái<th>
                </tr>
                <c:forEach items="${allbillsell}" var="allbillsell">
                    <tr>
                        <td>${allbillsell.id}</th>
                        <td><a href="<c:url value="/wall/${allbillsell.loginpay.id}"/>">${allbillsell.loginpay.full_name}</a></th>
                        <td>${allbillsell.value}k</th>
                        <c:if test= "${allbillsell.type==0}">
                            <td>chưa thanh toán</th>  
                        </c:if>
                        <c:if test= "${allbillsell.type==1}">
                             <td><a href="<c:url value="/check/${allbillsell.id}"/>">Xác nhận<a></th>   
                        </c:if>
                        <c:if test= "${allbillsell.type==2}">
                            <td>thanh toán lại</th>  
                        </c:if>
                        <c:if test= "${allbillsell.type==3}">
                            <td>Hoàn thành (<a href="<c:url value="/check/${allbillsell.id}"/>">Xem<a>)</th>  
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="yourbill col-md-6 col-xs-12"> 
            <h2 class="text-center text-info">Hóa đơn chờ bạn thanh toán</h2>
            <table class="table">
                <tr>
                    <th>Mã đơn</th>
                    <th>Người bán</th>
                    <th>Số tiền</th>
                    <th>Trạng Thái<th>
                </tr>
                <tr>
                <c:forEach items="${allbillpay}" var="allbillpay">
                    <tr>
                        <td>${allbillpay.id}</th>
                        <td><a href="<c:url value="/wall/${allbillpay.loginsell.id}"/>">${allbillpay.loginsell.full_name}</a></th>
                        <td>${allbillpay.value}k</th>
                        <c:if test= "${allbillpay.type==0}">
                            <td><a href="<c:url value="/check/${allbillpay.id}"/>">Thanh toán<a></th>  
                        </c:if>
                        <c:if test= "${allbillpay.type==1}">
                            <td><a>Chờ xác nhận<a></th>  
                        </c:if>
                        <c:if test= "${allbillpay.type==3}">
                            <td>Hoàn thành (<a href="<c:url value="/check/${allbillpay.id}"/>">Xem<a>)</th>  
                        </c:if>
                        <c:if test= "${allbillpay.type==2}">
                             <td><a href="<c:url value="/check/${allbillpay.id}"/>">Thanh toán lại<a></th>  
                        </c:if>
                    </tr>
                </c:forEach>
                </tr>
            </table>
        </div>
    </div>
    <!<!-- FOOTER -->
    <tiles:insertAttribute name="footer"/>
</div>
