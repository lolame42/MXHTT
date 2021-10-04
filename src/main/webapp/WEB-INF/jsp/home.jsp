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
    <c:url value="/home" var="action" />
    <c:url value="/auction" var="action1" />
    <div class="main">

        <div class="formregister">
            <c:if test= "${errMsg!=null}">
                <h2 class="alert-danger">${errMsg}</h2>
            </c:if>
            <c:if test="${statustest ==0}">
                <form:form method="post" action="${action}" modelAttribute="status" enctype="multipart/form-data" cssClass="ok">
                    <img class="img-fluid" src="<c:url value="${user.image}" />"alt="${user.full_name}"/>
                    <div class="form-group">
                        <form:textarea type="text" id="content" path="content" cssClass="form-control" placeholder="Giới thiệu bản thân"/>
                    </div>
                    <div class="form-group">
                        <form:input type="hashtag" id="hashtag" path="hashtag" cssClass="form-control" placeholder="Hashtag"/>
                    </div>  

                    <div class="form-group">
                        <input type="submit" value="Đăng" class="btn btn-primary"/>
                    </div>  
                </form:form>
                <c:forEach var="allstatus" items="${allstatus}">



                    <div class="status">

                        <a class="otb" href="<c:url value="/wall/${allstatus.login.id}"/>"><img class="img-fluid" src="<c:url value="${allstatus.login.image}" />" alt="${allstatus.login.full_name}"/>  </a>
                        <h5>${allstatus.login.full_name}</h5>
                        <div class="my-date">
                            <i>${allstatus.date}</i>
                        </div>

                        <div class="card-body">
                            <h5>${allstatus.content}</h5>
                            <c:if test="${allstatus.hashtag!=null}">
                                <h5>#${allstatus.hashtag}</h5>
                            </c:if>

                        </div>

                        <button ><i class="far fa-thumbs-up"></i></button>
                        <a class="otb" href="<c:url value="/status/${allstatus.idStatus}"/>"><i class="far fa-comment-alt"></i> </a>

                    </div>
                </c:forEach>
                <ul>
                    <c:forEach begin="1" end="${Math.ceil(countstt/2)}" var = "page">
                        <li class="page-item"><a class="page-link" href=" <c:url value="/home" />?idstt=0?page=${page}">1</a></li>

                    </c:forEach>
                </ul>

            </c:if>
            <c:if test="${statustest ==1}">
                <form:form method="post" action="${action1}" modelAttribute="status" enctype="multipart/form-data">
                    <img class="img-fluid" src="<c:url value="${user.image}" />"alt="${user.full_name}"/>
                    <div class="form-group">
                        <c:if test="${ErrContent!=null}"><h2 class="alert-danger">${ErrContent}</h2></c:if>
                        <c:if test="${ErrContent==null}"><h2>Giới thiệu sản phẩm đấu giá</h2></c:if>
                        <form:textarea type="text" id="content" path="content" cssClass="form-control" placeholder="Giới thiệu về sản phẩm cần đấu giá"/>
                    </div>
                    <div class="form-group">
                        <c:if test="${ErrFile!=null}"><h2 class="alert-danger">${ErrFile}</h2></c:if>
                        <c:if test="${ErrFile==null}"><h2>Hình ảnh sản phẩm đấu giá</h2></c:if>
                        <form:input type="file" id="file" path="file" cssClass="form-control" placeholder="Ảnh của sản phẩm"/>
                    </div>
                    <div class="form-group">
                        <c:if test="${ErrStep!=null}"><h2 class="alert-danger">${ErrStep}</h2></c:if>
                        <c:if test="${ErrStep==null}"><h2>Bước nhảy của phiên đấu giá(bội số của 10.000 và nhỏ hơn 100.001)</h2></c:if>
                        <form:input type="hashtag" id="step" path="step" cssClass="form-control" placeholder="Bước nhảy"/>
                    </div>  
                    <div class="form-group">
                        <c:if test="${ErrHour!=null}"><h2 class="alert-danger">${ErrHour}</h2></c:if>
                        <c:if test="${ErrHour==null}"><h2>Số giờ của phiên đấu giá(Sau khi hết giờ bạn có 3 tiếng để xác định người thắng)</h2></c:if>
                        <form:input type="hashtag" id="hour" path="hour" cssClass="form-control" placeholder="Số giờ tồn tại của phiên đấu giá này"/>
                    </div>  


                    <div class="form-group">
                        <input type="submit" value="Đăng" class="btn btn-primary"/>
                    </div>  
                </form:form>

                <c:forEach var="allstatus" items="${allstatus}">

                    <div class="status">

                        <a class="otb " href="<c:url value="/wall/${allstatus.login.id}"/>"><img class="img-fluid" src="<c:url value="${allstatus.login.image}" />" alt="${allstatus.login.full_name}"/>  </a>
                        <h5>${allstatus.login.full_name}</h5>
                        <div class="my-date">
                            <i>${allstatus.date}</i>
                        </div>
                        <div><img class="img-fluid" src="<c:url value="${allstatus.image}" />"alt="${allstatus.login.full_name}"/>  
                        </div>
                        <div class="card-body">
                            <h5>${allstatus.content}</h5>
                            <h5>Bước nhảy ${allstatus.step}</h5>
                        </div>
                        <a class="otb" href="<c:url value="/status/${allstatus.idStatus}"/>"><i class="far fa-comment-alt"></i> </a>
                    </div>

                </c:forEach>
                <ul>
                    <c:forEach begin="1" end="${Math.ceil(countstt/2)}" var = "page">
                        <li class="page-item"><a class="page-link" href=" <c:url value="/home" />?idstt=1?page=${page}">1</a></li>

                    </c:forEach>
                </ul>


            </c:if>





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

        </div>
    </div>
</div>