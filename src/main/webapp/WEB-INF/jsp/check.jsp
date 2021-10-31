<%-- 
    Document   : check
    Created on : Oct 27, 2021, 9:48:25 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="main">
    <c:url value="/check/${bill.id}" var="action" />
    <c:if test= "${err!=null}">
        <h2 class="alert-danger">${err}</h2>
    </c:if>
    <c:if test= "${err==null}">
        <h4 class="text-danger text-center">Thanh toán số tiền ${bill.value}k VND cho số điện thoại ${bill.loginsell.phone} và nhập mã giao dịch để xác nhận đã thanh toán cho hóa đơn này</h4>
    </c:if>

    <c:if test= "${post!=null}">
        <h2 class="alert-danger">${post}</h2>
    </c:if>
    <form:form method="post" action="${action}" modelAttribute="newbill" enctype="multipart/form-data" cssClass="ok">

        <form:input type="text" id="codemomo" path="codemomo" cssClass="form-control" placeholder="mã giao dịch momo"/>

        <div class="form-group">
            <input type="submit" value="Xác nhận" class="btn btn-primary"/>
        </div>  
    </form:form>
</div>

