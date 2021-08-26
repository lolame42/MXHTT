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
        <label for="user_name">Tên tài khoản</label>
        <form:input type="text" id="user_name" path="user_name" cssClass="btn btn-danger"/>
        <form:errors path="user_name" cssClass="alert alert-danger" element="div"/>
   </div>
   <div class="form-group">
        <label for="user_password">Mật khẩu tài khoản</label>
        <form:input type="text" id="user_password" path="user_password" cssClass="btn btn-danger"/>
        <form:errors path="user_password" cssClass="alert alert-danger" element="div"/>
   </div>
   <div class="form-group">
        <label for="phone">Điện thoại</label>
        <form:input type="phone" id="phone" path="phone" cssClass="btn btn-danger"/>
        <form:errors path="phone" cssClass="alert alert-danger" element="div"/>
   </div>
    <div class="form-group">
        <label for="file">Anh upload</label>
        <form:input type="file" id="file" path="file" cssClass="btn btn-danger"/>
    </div>
        <div class="form-group">
            <input type="submit" value="Them anh" />
        </div>  
</form:form>