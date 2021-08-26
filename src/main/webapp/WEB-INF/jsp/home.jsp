<%-- 
    Document   : home
    Created on : Aug 23, 2021, 5:54:48 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<ul>
    <h1 class="text-center text-danger">Tìm Tài Khoản</h1>
    <c:url value="/find" var="find"/>
    <form action="${find}">
        <div class="row">
            <div class="col-md-10">
                <input class="form-control" type="text" name="kw" placeholder="Nhập Tên Muốn Tìm"/>                
            </div>
            <div class="col-md-2">
                <input type="submit" value="Search" class="btn btn-primary"/>
            </div>
        </div>
    </form>       
</ul>
