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
	<form>
		<textarea placeholder="Hãy viết những gì tuyệt vời nhất ....." ></textarea>
		<ul>
                    <li><a title="" data-toggle="tooltip" data-placement="bottom" data-original-title="Picture"><i class="far fa-image"></i></a></li>
		</ul>
		<button type="submit" class="btn btn-primary">Đăng</button>
	</form>	    
</div>