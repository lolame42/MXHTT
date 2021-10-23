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
<link rel="stylesheet" href="<c:url value="/css/home.css"/>"/>

<div class="main">
    <c:url value="/auction" var="action" />
    <div class="main">

        <div class="formregister">


            <form:form method="post" action="${action}" modelAttribute="auction" enctype="multipart/form-data">

                <c:if test="${err!=null}">
                    <h3>${err}</h3>
                </c:if>
                <div class="form-group">
                    <c:if test="${errcontent!=null}"><h2 class="alert-danger">${errcontent}</h2></c:if>
                    <c:if test="${errcontent==null}"><h2>Giới thiệu sản phẩm đấu giá</h2></c:if>
                    <form:textarea type="text" id="content" path="content" cssClass="form-control" placeholder="Giới thiệu về sản phẩm cần đấu giá"/>
                </div>
                <div class="form-group">
                    <c:if test="${errimage!=null}"><h2 class="alert-danger">${errimage}</h2></c:if>
                    <c:if test="${errimage==null}"><h2>Hình ảnh sản phẩm đấu giá</h2></c:if>
                    <form:input type="file" id="file" path="file" cssClass="form-control" placeholder="Ảnh của sản phẩm"/>
                </div>
                <div class="form-group">
                    <c:if test="${errstep!=null}"><h2 class="alert-danger">${errstep}</h2></c:if>
                    <c:if test="${errstep==null}"><h2>Bước nhảy có đơn vị (Ngàn VND) tối đa 100, là số nguyên và không âm</h2></c:if>
                    <form:input type="hashtag" id="stepstr" path="stepstr" cssClass="form-control" placeholder="Bước nhảy"/>
                </div>  



                <div class="form-group">
                    <input type="submit" value="Đăng" class="btn btn-primary"/>
                </div>  
            </form:form>
            <c:forEach var="allauction" items="${allauction}">
                <div class="status">
                    <a class="otb" href="<c:url value="/wall/${allauction.login.id}"/>"><img class="img-fluid" src="<c:url value="${allauction.login.image}" />" alt="${allauction.login.full_name}"/>  </a>
                    <h5>${allauction.login.full_name}</h5>
                    <div class="my-date">
                        <i>${allauction.date}</i>
                    </div>
                    <div>  <img class="img-fluid" src="<c:url value="${allauction.image}" />"/> </div>
                    <div class="card-body">
                        <h5>${allauction.content}</h5>

                    </div>
                    <div class="card-body">
                        <h5>Bước nhảy ${allauction.step}</h5>

                    </div>

                </div>
                <a class="otb nav-link" href="<c:url value="/auctionpart/${allauction.id}" />">Đấu Giá</a>
                </div>
            </c:forEach>
        
            <ul>
                <c:forEach begin="1" end="${Math.ceil(countauc/30)}" var = "page">
                    <li class="page-item"><a class="page-link" href=" <c:url value="/auction" />?page=${page}">${page}</a></li>

                </c:forEach>
            </ul>


            <script>
                window.onload = function () {
                    let dates = document.querySelectorAll(".my-date>i")
                    for (let i = 0; i < dates.length; i++)
                    {
                        let d = dates[i]
                        d.innerText = moment(d.innerText).fromNow()

                    }
                }

            </script>




        </div>
    </div>
</div>
