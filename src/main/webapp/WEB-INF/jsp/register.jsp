<%-- 
    Document   : upload
    Created on : Aug 25, 2021, 4:30:50 PM
    Author     : Tu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h1 class="text-center text-danger">Trang upload</h1>

<c:url value="/register" var="action" />

<form:form method="post" action="${action}" modelAttribute="login" enctype="multipart/form-data">

    <div class="form-group">
        <label for="full_name">Họ và tên</label>
        <form:input type="text" id="full_name" path="full_name" cssClass="form-control"/>
        <form:errors path="full_name" cssClass="alert alert-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="user_name">Tên tài khoản</label>
        <form:input type="text" id="user_name" path="user_name" cssClass="form-control"/>
        <form:errors path="user_name" cssClass="alert alert-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="user_password">Mật khẩu tài khoản</label>
        <form:input type="password" id="user_password" path="user_password" cssClass="form-control"/>
        <form:errors path="user_password" cssClass="alert alert-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="email">Gmail</label>
        <form:input type="text" id="email" path="email" cssClass="form-control"/>
        <form:errors path="email" cssClass="alert alert-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="phone">Số điện thoại</label>
        <form:input type="text" id="phone" path="phone" cssClass="form-control"/>
        <form:errors path="phone" cssClass="alert alert-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="file">Ảnh đại diện</label>
        <form:input type="file" id="file" path="file" cssClass="form-control"/>
        <form:errors path="file" cssClass="alert alert-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="description">Mô tả</label>
        <form:textarea type="text" id="description" path="description" cssClass="form-control"/>
    </div>
    <div class="form-group">
        <input type="submit" value="Đăng Ký" class="btn btn-primary"/>
    </div>  
</form:form>