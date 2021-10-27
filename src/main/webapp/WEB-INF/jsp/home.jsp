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

<script src="<c:url value="/js/like.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/css/home.css"/>"/>

<div class="main">
    <c:url value="/home" var="action" />
    <div class="main">
        <div class="formregister">
            <c:if test= "${errMsg!=null}">
                <h2 class="alert-danger">${errMsg}</h2>
            </c:if>
            <form:form method="post" action="${action}" modelAttribute="status" enctype="multipart/form-data" cssClass="ok">
                <img class="img-fluid" src="<c:url value="${user.image}" />"alt="${user.full_name}"/>
                <h1>${user.full_name}</h1>
                <div class="box">
                    <c:if test= "${errcontent!=null}">
                        <h2 class="alert-danger">${errcontent}</h2>
                    </c:if>
                    <form:textarea type="text" id="content" path="content" cssClass="form-control" placeholder="Giới thiệu bản thân"/>
                </div>
                <div class="hashtag">
                    <c:if test= "${errhashtag!=null}">
                        <h2 class="alert-danger">${errhashtag}</h2>
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
                                <img class="img-fluid" src="<c:url value="${allstatus.login.image}" />" alt="${allstatus.login.full_name}"/></a>
                        </div>
                        <div class="name">
                            <h5>${allstatus.login.full_name}</h5>
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

                        <a class="otb nav-link" href="<c:url value="/status/${allstatus.idStatus}" />"><i class="far fa-comment"></i> Bình luận</a>
                        <c:if test= "${user.id!=allstatus.login.id}">
                            <c:if test= "${allstatus.check==0}">
                                <input id="${allstatus.idStatus}" class="hihi${allstatus.idStatus}" type="button" value="&#128077; Thích" onclick="addlike(${allstatus.idStatus},${user.id})"/>

                            </c:if> 
                        </c:if> 
                    </div>
                </div>
            </c:forEach>

            <ul>
                <c:forEach begin="1" end="${Math.ceil(countstt/30)}" var = "page">
                    <li class="page-item"><a class="page-link" href=" <c:url value="/home" />?page=${page}">${page}</a></li>

                </c:forEach>
            </ul>



            <script>
                window.onload = function () {
                    let dates = document.querySelectorAll(".my-date>p")
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