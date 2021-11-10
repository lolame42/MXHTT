<%-- 
    Document   : base
    Created on : Aug 23, 2021, 2:09:49 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>
            <tiles:insertAttribute name="title"/>
        </title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    </head>
    <body>
        <div class="container">

            <!<!-- HEADER -->
            <tiles:insertAttribute name="header"/>
            <!<!-- CONTENT -->
            <tiles:insertAttribute name="content"/>
            <!<!-- FOOTER -->
            <tiles:insertAttribute name="footer"/>
        </div>
    </body>
</html>
