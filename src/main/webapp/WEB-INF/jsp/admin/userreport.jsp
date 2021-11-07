
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-danger">Thống kê tài khoản bị tố cáo</h1>
<table class="table">
    <tr>
        <th>Tên tài khoản</th>
        <th>Số lượng tố cáo</th>
        <th>Giải quyết</th>

    </tr>
    <tr>
        <c:forEach items="${userreport}" var="c">
        <tr>
            <td><a class="otb" href="<c:url value="/wall/${c[0].id}"/>"> ${c[0].full_name}</a></th>
            <td>${c[1]}</th>
            <td><a class="delete otb nav-link text-dark" href="<c:url value="/admin/lock/${c[0].id}"/>"
                   onclick="return confirm('Bạn có chắc muốn xóa tài khoản này ?');"><b><i class="fas fa-trash-alt"></i> Xóa tài khoản</b></a>
                </th>

        </tr>
    </c:forEach>
</tr>
</table>
