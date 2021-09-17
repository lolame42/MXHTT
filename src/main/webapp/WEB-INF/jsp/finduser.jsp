<%-- 
    Document   : finduser
    Created on : Aug 23, 2021, 4:26:43 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
    <h1 class="text-center text-danger">Những tài khoản gần giống tên bạn tìm</h1>

    <div class="row">
        <c:forEach var="userfind" items="${userfind}">
            <div class="card col-md-12 bg-info" style="width:400px">
                <div class="card-body">
                    <c:if test="${userfind.image!=null}">
                        <img class="img-fluid" src="<c:url value="${userfind.image}" />"alt="${userfind.full_name}"/>  
                        <div class="card-body">
                            <h4 class="card-title">${userfind.full_name}</h4>
                            <a href="#" class="btn btn-primary">Xem thông tin</a>
                        </div>
                    </c:if>
                    <c:if test="${userfind.image==null}">
                        <img class="img-fluid" src="<c:url value="images/logo.png" />"alt="${userfind.full_name}"/>     
                        <div class="card-body">
                            <h4 class="card-title">${userfind.full_name}</h4>
                            <a href="#" class="btn btn-primary">Xem thông tin</a>
                        </div>
                    </c:if>



                </div>

            </div>
        </c:forEach>
        <h1>${kw}</h1>
    </div>



</ul>