<%-- 
    Document   : setting
    Created on : Sep 22, 2021, 3:42:06 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Thay đổi thông tin cá nhân</h1>
<label>Tên của bạn :</label><label>${user.full_name}</label>
<a href="#">Thay đổi tên người dùng</a>