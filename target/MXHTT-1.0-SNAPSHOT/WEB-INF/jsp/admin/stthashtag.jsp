<%-- 
    Document   : baiviettheohashtag
    Created on : Oct 6, 2021, 11:39:43 AM
    Author     : DAVADO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-danger">Thống kê bài đăng theo hashtag</h1>
<table class="table">
    <tr>
        <th>Tên hashtag</th>
        <th>Số lượng bài viết</th>

    </tr>
    <tr>
        <c:forEach items="${stthashtag}" var="c">
        <tr>
            <td>#${c[0]}</th>
            <td>${c[1]}</th>

        </tr>
    </c:forEach>
</tr>
</table>
