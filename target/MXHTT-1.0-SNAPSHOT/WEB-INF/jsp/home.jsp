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
        <img class="img-fluid" src="<c:url value="${user.image}" />"alt="${user.full_name}"/>  
        <form:form method="post" action="${action}" modelAttribute="status" enctype="multipart/form-data">
          
            <div class="form-group">
                <form:textarea type="text" id="content" path="content" cssClass="form-control" placeholder="Giới thiệu bản thân"/>
            </div>
            <input type="text" value="Hashtag" data-role="tagsinput" />
            <div class="form-group">
                <input type="submit" value="Đăng" class="btn btn-primary"/>
            </div>  
        </form:form>
    </div>
</div>



</div>