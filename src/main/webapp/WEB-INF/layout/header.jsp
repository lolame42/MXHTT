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

<nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/home"/>"><img src="<c:url value="/images/logo.jpg"/>"/></a>
            <c:url value="/find" var="find"/>
        <form action="${find}">
            <div class="input-group">
                <input type="text" class="form-control rounded bg-light" name="kw" placeholder="Nhập tên muốn tìm" aria-label="Search"
                       aria-describedby="search-addon" />
                <button type="submit" class="btn btn-outline-primary"><i class="fas fa-search"></i></button>
            </div>
        </form>      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="icon nav-link" href="<c:url value="/home"/>"><i class="fas fa-home"></i> </a>
                </li
                <li class="nav-item">
                    <a class="icon nav-link" href="<c:url value="/auction"/>"><i class="fas fa-gavel"></i> </a>
                </li
                <li class="icon nav-item">
                    <div class="dropdown" >
                        <button onclick="hamDropdown()" class="nut_dropdown">&#128276;</button>
                        <div class="noidung_dropdown">
                            <h2><b>Thông báo</b></h2>
                            <c:forEach var="noti" items="${noti}">
                                <div class="notification">
                                    <div class="img">
                                        <img class="otb" src="<c:url value="${noti.avatar}" />"alt="${noti.name}"/>
                                    </div>
                                    <div class="text">
                                        <c:if test = "${noti.type==1}">
                                             <c:if test = "${noti.statusnoti.idStatus!=null}">
                                                <a href="<c:url value="/status/${noti.statusnoti.idStatus}"/>" class="nav-link text-dark"><b>${noti.name}</b> đã bình luận bài viết của bạn</a>
                                                <hr />
                                            </c:if> 
                                            <c:if test = "${noti.statusnoti.idStatus==null}">
                                                <a href="<c:url value="/status/0"/>" class="nav-link text-dark"><b>${noti.name}</b> đã bình luận bài viết của bạn</a>
                                                <hr />
                                            </c:if>
                                        </c:if>
                                        <c:if test = "${noti.type==2}">
                                            <c:if test = "${noti.statusnoti.idStatus!=null}">
                                                <a href="<c:url value="/status/${noti.statusnoti.idStatus}"/>" class="nav-link text-dark"><b>${noti.name}</b> đã thích bài viết của bạn</a>
                                                <hr />
                                            </c:if> 
                                            <c:if test = "${noti.statusnoti.idStatus==null}">
                                                <a href="<c:url value="/status/0"/>" class="nav-link text-dark"><b>${noti.name}</b>đã thích bài viết của bạn</a>
                                                <hr />
                                            </c:if>
                                        </c:if> 
                                        <c:if test = "${noti.type==3}">
                                            <c:if test = "${noti.auctionnoti.id==null}">
                                                <a href="<c:url value="/auctionpart/0"/>" class="nav-link text-dark"><b>${noti.name}</b> đã đấu giá sản phẩm của bạn</a>
                                                <hr />
                                            </c:if> 
                                            <c:if test = "${noti.auctionnoti.id!=null}">
                                                <a href="<c:url value="/auctionpart/${noti.auctionnoti.id}"/>" class="nav-link text-dark"><b>${noti.name}</b> đã đấu giá sản phẩm của bạn</a>
                                                <hr />
                                            </c:if>
                                        </c:if>   
                                        <c:if test = "${noti.type==4}">
                                            <a href="<c:url value="/billsell/0"/>" class="nav-link text-dark"><b>${noti.name}</b> đã bán sản phẩm cho bạn</a>
                                            <hr />
                                        </c:if> 
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="icon nav-link" href="<c:url value="/billsell/0"/>"><i class="fas fa-shopping-cart"></i></a>
                </li>
                <c:if test="${pageContext.request.userPrincipal.name==null}">
                    <li class="nav-black">
                        <a href="<c:url value="/"/>" class="nav-link" >Đăng Nhập</a>
                    </li>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name!=null}">
                    <li class="nav-black">
                        <a href="<c:url value="/wall/${user.id}"/>" class="nav-link text-dark"><img class="img-fluid" src="<c:url value="${user.image}" />"alt="${user.full_name}"/>  </a>
                        <a href="<c:url value="/wall/${user.id}"/>" class="nav-link text-dark"><h4>${user.full_name}</h4></a>                  
                    </li>
                    <li class="nav-item active">
                        <a href="<c:url value="/logout"/>" class="logout nav-link text-dark"><i class="fas fa-sign-out-alt"></i> Đăng Xuất</a>
                    </li>
                </c:if>
            </ul>   
        </div>
    </div>
</nav>