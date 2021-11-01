<%-- 
    Document   : home
    Created on : Aug 23, 2021, 5:54:48 PM
    Author     : DAVADO
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="<c:url value="/js/home.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/css/home.css"/>"/>

<div class="main">
    <c:url value="/home" var="action" />
    <div class="main">
        <div class="formregister">
            <c:if test= "${errMsg!=null}">
                <h2 class="alert-danger">${errMsg}</h2>
            </c:if>
            <form:form method="post" action="${action}" modelAttribute="status" enctype="multipart/form-data" cssClass="ok">
                <div class="infostt">
                    <img class="img-fluid" src="<c:url value="${user.image}" />"alt="${user.full_name}"/>
                    <h3>${user.full_name}</h3>
                </div>
                <div class="box">
                    <c:if test= "${errcontent!=null}">
                        <h2 class="alert-danger">${errcontent}</h2>
                    </c:if>
                    <form:textarea type="text" id="content" path="content" cssClass="form-control" placeholder="Giới thiệu bản thân"/>
                </div>
                <div class="hashtag">
                    <c:if test= "${errhashtag!=null}">
                        <h2>${errhashtag}</h2>
                    </c:if>
                    <form:input type="hashtag" id="hashtag" path="hashtag" cssClass="form-control" placeholder="Hashtag"/>
                </div>  
                <div class="form-group">
                    <input type="submit" value="Đăng" class="btn btn-primary"/>
                </div>  
            </form:form>
            <c:forEach var="allstatus" items="${allstatus}">
                <div class="status">
                    <div class="info">
                        <div>
                            <a class="otb" href="<c:url value="/wall/${allstatus.login.id}"/>">
                                <img class="img-fluid" src="<c:url value="${allstatus.login.image}" />" alt="${allstatus.login.full_name}"/>
                            </a>
                        </div>
                        <div class="name">
                            <h5>${allstatus.login.full_name}</h5>
                            <c:if test= "${allstatus.login.id==user.id}">
                                <div class="setting" onclick="">
                                    <a  class="btn text-white" data-toggle="collapse" href="#noidung"><i class="fas fa-cog"></i></a>
                                    <div id="noidung" class="collapses">
                                        <a class="otb nav-link text-dark" href="<c:url value="/setting/status/${allstatus.idStatus}" />">
                                            <b><i class="fas fa-pencil-alt"></i> Chỉnh sửa</b></a>
                                        <a class="delete otb nav-link text-dark" href="<c:url value="/delete/status/${allstatus.idStatus}"/>"
                                           onclick="return confirm('Bạn có chắc muốn dừng phiên đấu giá này ?');"><b><i class="fas fa-trash-alt"></i> Xóa bài</b></a>
                                    </div>
                                </div>                               
                            </c:if>
                            <div class="my-date">
                                <p>${allstatus.date}</p>
                            </div>
                        </div>
                    </div>                 
                    <div class="card-body">
                        <p>${allstatus.content}</p>
                        <c:if test="${allstatus.hashtag!=null}">
                            <p class="text-info">#${allstatus.hashtag}</p>
                        </c:if>
                    </div>
                    <div class="nut">    
                        <c:if test= "${allstatus.countlike!=0}">
                            <h5 class="nuber">${allstatus.countlike} <i class="far fa-thumbs-up"></i></h5> 
                        </c:if>                        
                        <a class="otb nav-link" href="<c:url value="/status/${allstatus.idStatus}" />">Bình luận <i class="far fa-comment"></i></a>
                        <c:if test= "${user.id!=allstatus.login.id}">
                            <c:if test= "${allstatus.check==0}">
                                <input id="${allstatus.idStatus}" class="otb hihi${allstatus.idStatus} " type="button" value="Thích &#128077;" onclick="addlike(${allstatus.idStatus},${user.id})"/>
                            </c:if> 
                        </c:if>
                        <c:if test= "${allstatus.countcmt!=0}">
                            <h5 class="nuber">${allstatus.countcmt} Bình luận <i class="far fa-comment-dots"></i></h5> 
                        </c:if>
                    </div>
                </div>
            </c:forEach>
            <ul>
                <c:forEach begin="1" end="${Math.ceil(countstt/30)}" var = "page">
                    <li class="page-item"><a class="page-link" href=" <c:url value="/home" />?page=${page}">${page}</a></li>
                    </c:forEach>
            </ul>         
        </div>
    </div>
</div>