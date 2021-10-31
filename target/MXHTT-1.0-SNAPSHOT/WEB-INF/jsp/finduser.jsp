<%-- 
    Document   : finduser
    Created on : Aug 23, 2021, 4:26:43 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/finduser.css"/>"/>
<ul>
    <h1 class="text-center text-dark">Những tài khoản gần giống tên bạn tìm</h1>
    <div class="row">
        <c:forEach var="userfind" items="${userfind}">
            <div class="card col-md-12" style="width:400px">
                <div class="card-body">
                    <c:if test="${userfind.image!=null}">
                        <div class="info">
                            <a class="otb" href="<c:url value="/wall/${userfind.id}"/>">
                                <img class="img-fluid" src="<c:url value="${userfind.image}" />"alt="${userfind.full_name}"/></a>
                            <a class="otb" href="<c:url value="/wall/${userfind.id}"/>">
                                <div class="card-body">
                                    <h4 class="card-title">${userfind.full_name}</h4>
                                </div>
                            </a>
                        </div>
                    </c:if>
                    <c:if test="${userfind.image==null}">
                        <div class="info">
                            <a class="otb" href="<c:url value="/wall/${userfind.id}"/>">
                                <img class="img-fluid" src="<c:url value="images/logo.png" />"alt="${userfind.full_name}"/></a>

                            <div class="card-body">
                                <h4 class="card-title">${userfind.full_name}</h4>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </c:forEach>
        <h1>${kw}</h1>
    </div>
</ul>