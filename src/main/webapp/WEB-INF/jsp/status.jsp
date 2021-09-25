<%-- 
    Document   : comment
    Created on : Sep 23, 2021, 5:05:55 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="<c:url value="/css/status.css"/>"/>
<div class="status">

    <a class="otb" href="<c:url value="/wall/${status.login.id}"/>"><img class="img-fluid" src="<c:url value="${status.login.image}" />" alt="${status.login.full_name}"/>  </a>
    <div class="tgcmt">
        <h5>${status.login.full_name}</h5>
        <h6>${status.date}</h6>
    </div>

    <div class="card-body">
        <h5>${status.content}</h5>
        <c:if test="${status.hashtag!=null}">
            <h5>#${status.hashtag}</h5>
        </c:if>
    </div>
    <button ><i class="far fa-thumbs-up"></i></button>
    <button><i class="far fa-comment-alt"></i></button>
</div>
<div class="formregister">

    <c:url value="/status/${idstt}" var="action" />
    <form:form method="post" action="${action}" modelAttribute="newcmt" enctype="multipart/form-data">
        
        <h8 class="text-center text-dark"><b>Thêm bình luận</b></h8>
        <div class="form-group">
            <form:input type="text" id="content" path="content" cssClass="form-control" placeholder="Nội dung bình luận"/>
            <input type="submit" value="Đăng" class="btn btn-primary"/>
        </div>
        <c:if test = "${errMsg!=null}">
            <div> <h8 class="text-center alert-danger"><b>${errMsg}</b></h8> <div> 
        </c:if> 
    </form:form>
</div>
<c:forEach var="allcomment" items="${allcomment}">
                    <div class="main"> 
    <a class="otb" href="<c:url value="/wall/${allcomment.login.id}"/>"><img class="img-fluid" src="<c:url value="${allcomment.login.image}" />" alt="${allcomment.login.full_name}"/>  </a>
    <h6>${allcomment.login.full_name}</h6>
    <div class="tgcmt">

        <h5>${allcomment.content}</h5>
        <h6>${allcomment.date}</h6>

    </div>
                    </div>
</c:forEach>
<script>
    window.onload = function () {
        let dates = document.querySelectorAll(".tgcmt>h6")
        for (let i = 0; i < dates.length; i++)
        {
            let d = dates[i]
            d.innerText = moment(d.innerText).fromNow()

        }
    }

</script>

