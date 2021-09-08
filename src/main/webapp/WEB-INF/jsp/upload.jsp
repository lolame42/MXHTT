<%-- 
    Document   : upload
    Created on : Aug 25, 2021, 4:30:50 PM
    Author     : Tu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h1 class="text-center text-danger">Trang upload</h1>

<c:url value="/admin/uploads" var="action" />
<form:form method="post" action="${action}" modelAttribute="upload" enctype="multipart/form-data">
    <div class="form-group">
        <label for="file">Anh upload</label>
        <form:input type="file" id="file" path="file" cssClass="btn btn-danger"/>
    </div>
        <div class="form-group">
            <input type="submit" value="Them anh" />
        </div>
</form:form>