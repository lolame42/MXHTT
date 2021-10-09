
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/login.css"/>"/>

<c:url value="/" var="action"/>
<c:if test="${param.error!=null}">
    <div class="alert alert-danger">
        Đã có lỗi xảy ra
    </div>
</c:if>
<c:if test="${param.accessDenied!=null}">
    <div class="alert alert-danger">
        Bạn không có quyền truy cập
    </div>
</c:if>

<div class="main">
    <div class="image">
        <img src="<c:url value="/images/logo.jpg"/>" alt="logo"/>        
    </div>
    <div class="formlogin">
        <h3 class="text-center text-dark"><b>MẠNG XÃ HỘI TỪ THIỆN</b></h3>
        <form method="post" action="${action}">
            <div class="form-group">
                <label for="user_name"></label>
                <input type="text" id="user_name" name="user_name" class="form-control" placeholder="&#128272; Tên tài khoản"/>      
            </div>
            <div class="form-group">
                <label for="user_password"></label>
                <input type="password" id="user_password" name="user_password" class="form-control" placeholder="&#128273; Mật khẩu"/>      
            </div>
            <div class="form-group">
                <input type="submit" class="login text-white" value="Đăng Nhập"/>

            </div>
        </form>
            <hr />
            <a href="<c:url value="/register"/>" class="nav-link text-dark text-center">Bạn chưa có tài khoản? &#128073; <b>Đăng ký ngay!!!</b></a>
         
    </div>
</div>
        