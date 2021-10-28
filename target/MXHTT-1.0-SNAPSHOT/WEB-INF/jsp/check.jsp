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
    <form:form method="post" action="${action}" modelAttribute="newcheck" enctype="multipart/form-data" cssClass="ok">

        <form:input type="text" id="codestr" path="codestr" cssClass="form-control" placeholder="mã giao dịch momo"/>

        <div class="form-group">
            <input type="submit" value="Đăng" class="btn btn-primary"/>
        </div>  
    </form:form>
</div>

