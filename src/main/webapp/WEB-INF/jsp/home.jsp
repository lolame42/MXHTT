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
                <div class="form-group">
                    <form:textarea type="text" id="content" path="content" cssClass="form-control" placeholder="Giới thiệu bản thân"/>
                </div>
                <div class="form-group">
                    <form:input type="hashtag" id="hashtag" path="hashtag" cssClass="form-control" placeholder="Hashtag"/>
                </div>  

                <div class="form-group">
                    <input type="submit" value="Đăng" class="btn btn-primary"/>
                </div>  
            </form:form>

            <c:forEach var="allstatus" items="${allstatus}">
                <c:if test="${allstatus.step==null}">
                    <div class="status">

                        <a class="otb" href="<c:url value="/wall/${allstatus.login.id}"/>"><img class="img-fluid" src="<c:url value="${allstatus.login.image}" />" alt="${allstatus.login.full_name}"/>  </a>
                        <h5>${allstatus.login.full_name}</h5>
                        <div class="my-date">
                            <i>${allstatus.date}</i>
                        </div>

                        <div class="card-body">
                            <h5>${allstatus.content}</h5>
                            <c:if test="${allstatus.hashtag!=null}">
                                <h5>#${allstatus.hashtag}</h5>
                            </c:if>

                        </div>

                        <button ><i class="far fa-thumbs-up"></i></button>
                        <a class="otb" href="<c:url value="/status/${allstatus.idStatus}"/>"><i class="far fa-comment-alt"></i> </a>

                    </div>
                </c:if>
            </c:forEach>
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