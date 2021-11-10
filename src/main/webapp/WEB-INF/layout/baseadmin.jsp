<%-- 
    Document   : baseadmin
    Created on : Oct 6, 2021, 11:29:11 AM
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
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-xs-12"> 
                    <!<!-- HEADER -->
                    <tiles:insertAttribute name="left"/>
                </div>           
                <div class="col-md-8 col-xs-12">
                    <!<!-- CONTENT -->
                    <tiles:insertAttribute name="content"/>
                </div>
            </div>
        </div>
    </body>
    <footer class="text-center"><!<!-- FOOTER -->
            <tiles:insertAttribute name="footer"/>
    </footer>
</html>
