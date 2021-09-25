<%-- 
    Document   : setting
    Created on : Sep 22, 2021, 3:42:06 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Thay đổi thông tin cá nhân</h1>
<c:url value="/setting" var="action" />
<c:if test="${thongbao != null}">
    <h1>${thongbao}</h1>
</c:if>
<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data">
   
    <div class="form-group">
        <form:input type="text" id="full_name" path="full_name" cssClass="form-control" placeholder="${user.full_name}"/>
    </div>
    <div class="form-group">
        <input type="submit" value="Cập nhật" class="btn btn-primary"/>
    </div> 

</form:form>