<%-- 
    Document   : upload
    Created on : Aug 25, 2021, 4:30:50 PM
    Author     : Tu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="b" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="<c:url value="/css/register.css"/>"/>

<c:url value="/register" var="action" />
<div class="main">
    <div class="image">
        <img src="<c:url value="/images/logo.jpg"/>" alt="logo"/>        
    </div>
    <div class="formregister">
        <form:form method="post" action="${action}" modelAttribute="login" enctype="multipart/form-data">
            <h3 class="text-center text-dark"><b>Đăng Ký Tài Khoản</b></h3>
            <div class="form-group">
                <form:input type="text" id="full_name" path="full_name" cssClass="form-control" placeholder="Họ và tên"/>
                <form:errors path="full_name" cssClass="alert" element="div"/>
            </div>
            <div class="form-group">
                <form:input type="text" id="user_name" path="user_name" cssClass="form-control" placeholder="Tên tài khoản"/>
                <form:errors path="user_name" cssClass="alert" element="div"/>
                <b:if test= "${erruser!=null}">
                    <p class="alert-danger">${erruser}</p>
                </b:if>
            </div>
            <div class="form-group">
                <form:input type="password" id="user_password" path="user_password" cssClass="form-control" placeholder="Mật khẩu tài khoản"/>
                <form:errors path="user_password" cssClass="alert" element="div"/>
            </div>
            <div class="form-group">
                <form:input type="text" id="email" path="email" cssClass="form-control" placeholder="Nhập gmail"/>
                <form:errors path="email" cssClass="alert" element="div"/>
                <b:if test= "${erremail!=null}">
                    <p class="alert-danger">${erremail}</p>
                </b:if>
            </div>
            <div class="form-group">
                <form:input type="text" id="phone" path="phone" cssClass="form-control" placeholder="Nhập số điện thoại"/>
                <form:errors path="phone" cssClass="alert" element="div"/>
                <b:if test= "${errphone!=null}">
                    <p class="alert-danger">${errphone}</p>
                </b:if>
            </div>
            <div class="form-group">
                <form:input type="file" id="file" path="file" cssClass="form-control" />
                <form:errors path="file" cssClass="alert" element="div"/>
            </div>
            
            <div class="form-group">
                <input type="submit" value="Đăng Ký" class="btn btn-primary"/>
            </div>  
        </form:form>
    </div>
</div>