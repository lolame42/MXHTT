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
    <c:if test="${userwall.user_name!=user.user_name}">
        <button>Tố cáo</button>
    </c:if>
    <c:if test="${userwall.user_name==user.user_name}">
        <button>Cài đặt thông tin</button>
    </c:if>
    <h1>${userwall.full_name}</h1>
    <h5>${userwall.description}<h5>
            <c:forEach var="statuswall" items="${statuswall}">
                <div>

                    <a class="otb" href="<c:url value="/wall/${statuswall.login.id}"/>"><img class="img-fluid" src="<c:url value="${statuswall.login.image}" />" alt="${statuswall.login.full_name}"/>  </a>
                    <div class="my-date">
                        <h5>${statuswall.login.full_name}</h5>
                        <i>${statuswall.date}</i>
                    </div>

                    <div class="card-body">
                        <h5>${statuswall.content}</h5>
                        <c:if test="${statuswall.hashtag!=null}">
                            <h5>#${statuswall.hashtag}</h5>

                        </c:if>

                    </div>
                    <button>Thích</button>
                    <button>Bình Luận</button>

                </div>
            </c:forEach>
            <script>
                window.onload = function () {
                    let dates = document.querySelectorAll(".my-date>i")
                    for (let i = 0; i < dates.length; i++)
                    {
                        let d = dates[i]
                        d.innerText = moment(d.innerText).fromNow()

                    }
                }

            </script>
            <c:forEach var="allcomment" items="${allcomment}">
                <div>

                    <h1>${allcomment.content}</h1>

                </div>
            </c:forEach>
        </c:if>
