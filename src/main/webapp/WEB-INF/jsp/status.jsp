<%-- 
    Document   : comment
    Created on : Sep 23, 2021, 5:05:55 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="<c:url value="/css/status.css"/>"/>
<div class="mains">
    <div class="full">
    <div class="status">
        <div class="info">
            <a class="otb" href="<c:url value="/wall/${status.login.id}"/>"><img class="img-fluid" src="<c:url value="${status.login.image}" />" alt="${status.login.full_name}"/>  </a>
            <div class="tgcmt">
                <h5>${status.login.full_name}</h5>
                <p><i>${status.date}</i></p>
            </div>
        </div>
        <div class="card-body">
            <p>${status.content}</p>
            <c:if test="${status.hashtag!=null}">
                <h5>#${status.hashtag}</h5>
            </c:if>
        </div>
    </div>
    <div class="formregister">
        <c:url value="/status/${idstt}" var="action" />
        <form:form method="post" action="${action}" modelAttribute="newcmt" enctype="multipart/form-data">
            <hr/>
            <div class="nut">
                <c:if test= "${user.id!=allstatus.login.id}">
                    <input type="button" value="&#128077; Thích" onclick="addlike(${allstatus.idStatus},${user.id})"/>
                </c:if> 
                <a class="otbs nav-link" href="<c:url value="/status/${allstatus.idStatus}" />"><i class="far fa-comment"></i> Bình luận</a>
            </div>
            <hr />
            <div class="post">
                <a class="otb" href="<c:url value="/wall/${status.login.id}"/>"><img class="img-fluid" src="<c:url value="${status.login.image}" />" alt="${status.login.full_name}"/></a>
                <form:input type="text" id="content" path="content" cssClass="form-control" placeholder="Viết bình luận"/>
            </div>
            <c:if test = "${errMsg!=null}">
                <div> <h8 class="text-center alert-danger"><b>${errMsg}</b></h8> </div> 
            </c:if> 
        </form:form>
    </div>
    <c:forEach var="allcomment" items="${allcomment}">
        <div class="comments">
        <a class="otb" href="<c:url value="/wall/${allcomment.login.id}"/>"><img class="img-fluid" src="<c:url value="${allcomment.login.image}" />" alt="${allcomment.login.full_name}"/>  </a>
        <div class="comment"> 
            <h6>${allcomment.login.full_name}</h6>
            <div class="tgcmt">
                <p>${allcomment.date}</p>
            </div>
            <h7>${allcomment.content}</h7>
        </div>
        </div>
    </c:forEach>
    </div>
</div>
<script>
    window.onload = function () {
        let dates = document.querySelectorAll(".tgcmt>p")
        for (let i = 0; i < dates.length; i++)
        {
            let d = dates[i]
            d.innerText = moment(d.innerText).fromNow()
        }
    }
</script>

