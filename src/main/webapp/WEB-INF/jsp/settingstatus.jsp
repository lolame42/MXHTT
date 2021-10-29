<%-- 
    Document   : settingstatus
    Created on : Oct 28, 2021, 5:11:13 PM
    Author     : DAVADO
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/settingstatus.css"/>"/>
<div class="main">
    <h1 class="text-center">Sửa bài đăng</h1>
    <c:url value="/setting/status/${status.idStatus}" var="action" />
    <c:if test= "${err!=null}">
        <h2 class="alert-danger">${err}</h2>
    </c:if>
    <form:form method="post" action="${action}" modelAttribute="newstatus" enctype="multipart/form-data" cssClass="ok">
        <c:if test= "${errcontent!=null}">
            <h2 class="alert-danger">${errcontent}</h2>
        </c:if>
        <div class="hashtag">
            <label for="content">Nội dung cũ :${status.content}</label>
            <h5>Nội dung mới :</h5>
            <form:textarea type="text" id="content" path="content" cssClass="form-control" placeholder="${status.content}"/>
        </div> 
        <c:if test= "${errhashtag!=null}">
            <h2 class="alert-danger">${errhashtag   }</h2>
        </c:if>
            <label for="hashtag">Hashtag cũ : #${status.hashtag}</label>
        <div class="hashtag">
            <h5>Hashtag mới :</h5>
            <form:input type="text" id="hashtag" path="hashtag" cssClass="form-control" placeholder="#${status.hashtag}"/>
        </div> 
        <div class="form-group">
            <input type="submit" value="Cập Nhật" class="btn btn-primary"/>
        </div>  
    </form:form>
</div>
