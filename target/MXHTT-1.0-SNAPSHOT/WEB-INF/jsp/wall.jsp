<%-- 
    Document   : wall
    Created on : Sep 17, 2021, 1:58:25 PM
    Author     : DAVADO
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link rel="stylesheet" href="<c:url value="/css/header.css"/>"/>
<c:if test="${userwall!=null}">
      <img class="img-fluid" src="<c:url value="${userwall.image}" />"alt="${userwall.full_name}"/>  
    <h1>${userwall.full_name}</h1>
    <h5>${userwall.description}<h5>
</c:if>
<c:if test="${userwall==null}">
    <h1 class="text-center text-dark">Trở về <a>Trang chủ</a></h1>
</c:if>