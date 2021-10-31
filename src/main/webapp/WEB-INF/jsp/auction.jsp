<%-- 
    Document   : auction
    Created on : Oct 1, 2021, 1:13:05 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link rel="stylesheet" href="<c:url value="/css/auction.css"/>"/>

<c:url value="/auction" var="action" />
<div class="main">
    <div class="formregister">
        <form:form method="post" action="${action}" modelAttribute="auction" enctype="multipart/form-data">
            <c:if test="${err!=null}">
                <h3>${err}</h3>
            </c:if>
            <div class="form-group">
                <c:if test="${errcontent!=null}"><h3 class="alert-danger">${errcontent}</h3></c:if>
                <c:if test="${errcontent==null}"><h3>Giới thiệu sản phẩm đấu giá</h3></c:if>
                <form:textarea type="text" id="content" path="content" cssClass="form-control" placeholder="Giới thiệu về sản phẩm cần đấu giá"/>
            </div>
            <div class="form-group">
                <c:if test="${errimage!=null}"><h3 class="alert-danger">${errimage}</h3></c:if>
                <c:if test="${errimage==null}"><h3>Hình ảnh sản phẩm đấu giá</h3></c:if>
                <form:input type="file" id="file" path="file" cssClass="form-control" placeholder="Ảnh của sản phẩm"/>
            </div>
            <div class="form-group">
                <c:if test="${errstep!=null}"><h3 class="alert-danger">${errstep}</h3></c:if>
                <c:if test="${errstep==null}"><h3>Bước nhảy có đơn vị (Ngàn VND) tối đa 100, là số nguyên và không âm</h3></c:if>
                <form:input type="hashtag" id="stepstr" path="stepstr" cssClass="form-control" placeholder="Bước nhảy"/>
            </div>  
            <div class="form-group">
                <input type="submit" value="Đăng" class="btn btn-primary"/>
            </div>  
        </form:form>
    </div>
        <c:forEach var="allauction" items="${allauction}">
            <div class="status">
                <div class="img"><img class="img-fluid" src="<c:url value="${allauction.image}" />"/></div>
                <div class="text">
                    <h5><b>Người đăng:</b> ${allauction.login.full_name}</h5>
                    <div class="my-date">
                        <h5><b>Thời gian bắt đầu:</b></h5>
                        <i>${allauction.date}</i>
                    </div>
                        <h5><b>Giới thiệu:</b> ${allauction.content}</h5>
                        <h5><b>Bước nhảy:</b> ${allauction.step}</h5>
                    <a class="otb1 nav-link" href="<c:url value="/auctionpart/${allauction.id}" />">Đấu Giá</a>
                </div>     
            </div>
        </c:forEach>       
        <ul class="ul">
            <c:forEach begin="1" end="${Math.ceil(countauc/30)}" var = "page">
                <li class="page-item"><a class="page-link" href=" <c:url value="/auction" />?page=${page}">${page}</a></li>
            </c:forEach>
        </ul>
        <script>
            window.onload = function () {
                let dates = document.querySelectorAll(".my-date>i")
                for (let i = 0; i < dates.length; i++) {
                    let d = dates[i]
                    d.innerText = moment(d.innerText).fromNow()
                }
            }
        </script>
</div>
