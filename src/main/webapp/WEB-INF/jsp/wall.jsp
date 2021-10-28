<%-- 
    Document   : wall
    Created on : Sep 17, 2021, 1:58:25 PM
    Author     : DAVADO
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link rel="stylesheet" href="<c:url value="/css/wall.css"/>"/>
<c:if test="${userwall!=null}">
    <div class="infosa">
        <div class="info">
            <img class="img-fluid" src="<c:url value="${userwall.image}" />"alt="${userwall.full_name}"/>
            <h1>${userwall.full_name}</h1>
        </div>
        <c:if test="${userwall.user_name!=user.user_name}">
            <button class="tc"><b>Tố cáo</b></button>
        </c:if>
        <c:if test="${userwall.user_name==user.user_name}">
            <button class="cd"><b>Cài đặt thông tin<b/></button>
        </c:if> 
    </div>
    <div class="infosb">
        <div class="info1">
            <c:if test="${userwall.id==user.id}">
                <h4>Những phiên đấu giá của bạn</h4>
            </c:if> 
            <c:if test="${userwall.id!=user.id}">
                <h4>Những phiên đấu giá của ${userwall.full_name}</h4>
            </c:if>
            <c:forEach var="auctionwall" items="${auctionwall}">
                <img class="img-fluid1" src="<c:url value="${auctionwall.image}" />" alt="${auctionwall.content}"/>
                <div>${auctionwall.step}</div>
                <div class="my-date">
                    <i>${auctionwall.date}</i>
                </div>
                <a class="otb1 nav-link" href="<c:url value="/auctionpart/${auctionwall.id}" />">Đấu Giá</a>


            </c:forEach>
        </div>
        <div class="info2">

            <c:if test="${userwall.id==user.id}">
                <h4>Những bài đăng của bạn</h4>
            </c:if> 
            <c:if test="${userwall.id!=user.id}">
                <h4>Những bài đăng của ${userwall.full_name}</h4>
            </c:if>
            <c:forEach var="statuswall" items="${statuswall}">
                <div class="info2a">
                    <a class="otb" href="<c:url value="/wall/${statuswall.login.id}"/>"><img class="img-fluid1" src="<c:url value="${statuswall.login.image}" />" alt="${statuswall.login.full_name}"/>  </a>
                    <div class="my-date">
                        <h5>${statuswall.login.full_name}</h5>
                        <i>${statuswall.date}</i>
                    </div>

                    <c:if test= "${statuswall.login.id==user.id}">
                        <a class="otb nav-link" href="<c:url value="/setting/status/${statuswall.idStatus}" />" ><i class="far fa-comment"></i> Sửa</a>
                        <a class="otb nav-link" href="<c:url value="/delete/status/${statuswall.idStatus}" />" onclick="return confirm('Bạn có chắc muốn xóa bài đăng này ?');"><i class="far fa-comment"></i> Xóa</a>
                    </c:if>
                    <c:if test= "${statuswall.countlike!=0}">
                        <h5>${statuswall.countlike} like</h5> 
                    </c:if>
                </div>
                <a class="otb nav-link" href="<c:url value="/status/${allstatus.idStatus}" />"><i class="far fa-comment"></i> Bình luận</a>
                <div class="card-body">

                    <h5>${statuswall.content}</h5>
                    <c:if test="${statuswall.hashtag!=null}">
                        <h5>#${statuswall.hashtag}</h5>
                    </c:if>
                </div>
                <hr>


            </c:forEach>
        </div>
    </div>
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
    <c:forEach var="allcomment" items="${allcomment}">
        <div>
            <h1>${allcomment.content}</h1>
        </div>
    </c:forEach>
</c:if>
