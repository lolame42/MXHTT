<%-- 
    Document   : header
    Created on : Aug 23, 2021, 2:12:30 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link rel="stylesheet" href="<c:url value="/css/header.css"/>"/>
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>"/>
<script src="<c:url value="/js/header.js"/>"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/home"/>"><i class="fas fa-american-sign-language-interpreting"></i></a>
            <c:url value="/find" var="find"/>
        <form action="${find}">

            <div class="input-group">
                <input type="text" class="form-control rounded" name="kw" placeholder="Nhập tên muốn tìm" aria-label="Search"
                       aria-describedby="search-addon" />
                <button type="submit" class="btn btn-outline-primary"><i class="fas fa-search"></i></button>
            </div>
        </form>      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">

                    <a class="nav-link" href="<c:url value="/home"/>"><i class="fas fa-home"></i> </a>
                </li
                <li class="nav-item">

                    <a class="nav-link" href="<c:url value="/auction"/>"><i class="fas fa-gavel"></i> </a>
                </li
                <li>
                    <div class="dropdown" >

                        <button onclick="hamDropdown()" class="nut_dropdown">&#128276;</button>
                        <div class="noidung_dropdown">

                            <c:forEach var="noti" items="${noti}">
                                <div>
                                    <img class="otb" src="<c:url value="${noti.avatar}" />"alt="${noti.name}"/> 
                                    <c:if test = "${noti.type==1}">
                                        <a href="<c:url value="/status/${noti.statusnoti.idStatus}"/>">${noti.name} đã đã bình luận bài viết của bạn</a>
                                    </c:if>
                                    <c:if test = "${noti.type==2}">
                                        <a href="<c:url value="/status/${noti.statusnoti.idStatus}"/>">${noti.name} đã thích bài viết của bạn</a>
                                    </c:if>

                                </div> 
                            </c:forEach>
                        </div>
                    </div>
                </li>
                <c:if test="${pageContext.request.userPrincipal.name==null}">
                    <li class="nav-black">
                        <a href="<c:url value="/"/>" class="nav-link" >Đăng Nhập</a>
                    </li>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name!=null}">

                    <li class="nav-black">
                        <img class="img-fluid" src="<c:url value="${user.image}" />"alt="${user.full_name}"/>  
                        <a href="<c:url value="/home"/>" class="nav-link text-danger"  >${user.full_name}</a>                  
                    </li>
                    <li class="nav-item active">
                        <a href="<c:url value="/logout"/>" class="nav-link text-dark"><i class="fas fa-sign-out-alt"></i> <b>Đăng Xuất</b></a>
                    </li>
                </c:if>
            </ul>   
        </div>
    </div>
</nav>