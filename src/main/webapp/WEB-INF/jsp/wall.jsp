<%-- 
    Document   : wall
    Created on : Sep 17, 2021, 1:58:25 PM
    Author     : DAVADO
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="<c:url value="/js/wall.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/css/wall.css"/>"/>

<c:if test="${userwall!=null}">
    <div class="infosa">
        <div class="info">
            <img class="img-fluid" src="<c:url value="${userwall.image}" />"alt="${userwall.full_name}"/>
            <h1>${userwall.full_name}</h1>
            <c:if test="${userwall.user_name!=user.user_name}">
                <div class="reports">
                    <a class="report nav-link" data-toggle="collapse" href="#noidung">Report</a>

                    <div id="noidung" class="reporta">
                        <c:if test="${check1==null}">
                            <a class="otb nav-link text-dark" href="<c:url value="/report/${userwall.id}/1" />"
                               onclick="return confirm('Bạn có chắc muốn tố cáo ${userwall.full_name} với lý do lời lẽ không chuẩn mực ?');"><i class="far fa-times-circle"></i> Lời lẽ không chuẩn mực</a>
                        </c:if>                  
                        <c:if test="${check2==null}">
                            <a class="otb nav-link text-dark" href="<c:url value="/report/${userwall.id}/2" />"
                               onclick="return confirm('Bạn có chắc muốn tố cáo ${userwall.full_name} với lý do không thanh toán ?');"><i class="far fa-times-circle"></i> Không thanh toán</a>
                        </c:if>

                    </div>
                </div>
            </c:if>
            <c:if test="${countrp!=null}">
                <h5>số lần bị tố cáo : ${countrp}</h5>
            </c:if>
        </div>
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
                <img class="img-fluidd" src="<c:url value="${auctionwall.image}" />" alt="${auctionwall.content}"/>
                <div><b>Bước nhảy:</b> ${auctionwall.step}</div>
                <div class="my-date">
                    <i> ${auctionwall.date}</i>
                </div>
                <a class="otb1 btn-primary nav-link" href="<c:url value="/auctionpart/${auctionwall.id}" />">Đấu Giá</a>
                <hr/>
            </c:forEach>
        </div>
        <div class="info2">
            <c:if test="${userwall.id==user.id}">
                <h4 class="text-center">Những bài đăng của bạn</h4>
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
                        <div class="setting">
                            <a class="nav-link" data-toggle="collapse" href="#noidung">&#9881;</a>
                            <div id="noidung" class="collapses">
                                <a class="otb nav-link text-dark" href="<c:url value="/setting/status/${statuswall.idStatus}" />"><b><i class="fas fa-pencil-alt"></i> Chỉnh sửa</b></a>
                                <a class="otb nav-link text-dark" href="<c:url value="/delete/status/${statuswall.idStatus}" />"><b><i class="fas fa-trash-alt" onclick="return confirm('Bạn có chắc muốn xóa bài đăng này ?');"></i> Xóa bài</b></a>
                            </div>
                        </div> 
                    </c:if>
                </div>
                <div class="card-body">
                    <h5>${statuswall.content}</h5>
                    <c:if test="${statuswall.hashtag!=null}">
                        <h5>#${statuswall.hashtag}</h5>
                    </c:if>
                </div>
                <div class="icons">
                    <c:if test= "${statuswall.countlike!=0}">
                        <h5>${statuswall.countlike} <i class="far fa-thumbs-up"></i></h5> 
                        </c:if>
                    <a class="otbb nav-link" href="<c:url value="/status/${statuswall.idStatus}" />">Bình luận <i class="far fa-comment"></i></a>
                        <c:if test= "${statuswall.countcmt!=0}">
                        <h5>${statuswall.countcmt} <b>Bình luận</b> <i class="far fa-comment-dots"></i></h5> 
                        </c:if>
                </div>
                <hr>
            </c:forEach>
        </div>
    </div>
    <c:forEach var="allcomment" items="${allcomment}">
        <div>
            <h1>${allcomment.content}</h1>
        </div>
    </c:forEach>
</c:if>
