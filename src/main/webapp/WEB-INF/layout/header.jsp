<%-- 
    Document   : header
    Created on : Aug 23, 2021, 2:12:30 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link rel="stylesheet" href="<c:url value="/css/header.css"/>"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/home"/>"><i class="fas fa-american-sign-language-interpreting"></i></a>
            <c:url value="/find" var="find"/>
        <form action="${find}">
            <div class="row">
                <div class="col-md-10">
                    <input class="form-control" type="text" name="kw" placeholder="Nhập Tên Muốn Tìm"/>                
                </div>
                <div class="col-md-2">
                    <input type="submit" value="Search" class="btn btn-primary"/>
                </div>
            </div>
        </form>      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-home active">
                    <a href="<c:url value="/home"/>" class="nav-link"><i class="fas fa-home"></i></a>
                </li>
                <li class="nav-au active">
                    <a href="<c:url value="/"/>" class="nav-link"><i class="fas fa-gavel"></i></a>
                </li>
                <li class="nav-bell active">
                    <a href="<c:url value="/"/>" class="nav-link"><i class="fas fa-bell"></i></a>
                </li>
                <c:if test="${pageContext.request.userPrincipal.name==null}">
                    <li class="nav-black">
                        <a href="<c:url value="/"/>" class="nav-link" >Đăng Nhập</a>
                    </li>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name!=null}">
                    <li class="nav-black">
                        <a href="<c:url value="/home"/>" class="nav-link text-danger">${pageContext.request.userPrincipal.name}</a>
                    </li>
                    <li class="nav-item active">
                        <a href="<c:url value="/logout"/>" class="nav-link"><i class="fas fa-sign-out-alt"> Đăng Xuất</i></a>
                    </li>
                </c:if>
            </ul>   
        </div>
    </div>
</nav>