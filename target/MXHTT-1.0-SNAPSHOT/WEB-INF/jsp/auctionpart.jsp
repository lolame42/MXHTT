<%-- 
    Document   : auctionpart
    Created on : Oct 14, 2021, 2:32:00 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="<c:url value="/js/like.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/css/status.css"/>"/>

<div class="mains">
    <div class="full">
        <div class="status">
            <div class="info">
                <a class="otb" href="<c:url value="/wall/${auction.login.id}"/>"><img class="img-fluid" src="<c:url value="${auction.login.image}" />" alt="${auction.login.full_name}"/>  </a>
                <p>${auction.content}</p>
                <div>  <img class="img-fluid" src="<c:url value="${auction.image}" />"/> </div>
                <div> Giá Mua Ngay : ${auction.step*20} ngàn VND</div>
                <div> Bước nhảy : ${auction.step} ngàn VND</div>   
                <div> Giá hiện tại : ${top}  ngàn VND</div>

                <div class="tgcmt">
                    <h5>${auction.login.full_name}</h5>
                    <p><i>${auction.date}</i></p>
                </div>
            </div>  
            <c:if test= "${my==null}">
                <c:if test= "${an!=null}">
                    <h2 class="alert-danger">${an}</h2>
                </c:if>
                <c:if test= "${check!=null}">
                    <h2 class="alert-danger">${check}</h2>

                </c:if>
                <c:if test= "${an==null}">
                    <div>
                        <c:url value="/auctionpart/${auction.id}" var="action" />
                        <form:form method="post" action="${action}" modelAttribute="newsell" enctype="multipart/form-data" cssClass="ok">                   
                            <div class="box">
                                <c:if test= "${errValue!=null}">
                                    <h2 class="alert-danger">${errValue}</h2>
                                </c:if>

                            </div>  
                            <c:if test= "${check==null}">
                                <h5>Giá tiền đấu giá lớn hơn đỉnh, không lớn hơn tối đa và chia hết cho bước nhảy</h5>
                                <form:input type="step" id="step" path="step" cssClass="form-control" placeholder="Nhập số tiền đấu giá"/><h5>ngàn VNĐ</h5>
                                <div class="form-group">
                                    <input type="submit" value="Đấu giá" class="btn btn-primary"/>
                                </div> 
                            </c:if>


                        </form:form>
                    </div>
                </c:if>


            </c:if>
            <c:if test= "${my!=null}">
               
                <a class="nav-link"  href="<c:url value="/billsell/${auction.id}"/>" >Dừng phiên đấu giá này </a>
                <c:forEach var="allsell" items="${allsell}">
                    <a href="<c:url value="/wall/${allsell.loginsell.id}"/>"><h5>${allsell.loginsell.full_name}</h5></a>
                    <h5>${allsell.value}</h5>
                </c:forEach>
            </c:if>


        </div>

    </div>

</div>
<script>
    window.onload = function () {
        let dates = document.querySelectorAll(".tgcmt>p")
        for (let i = 0; i < dates.length; i++)
        {
            let d = dates[i]
            d.innerText = moment(d.innerText).fromNow()
        }
    }
</script>

