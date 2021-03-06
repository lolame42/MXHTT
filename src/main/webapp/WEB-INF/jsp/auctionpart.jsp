<%-- 
    Document   : auctionpart
    Created on : Oct 14, 2021, 2:32:00 PM
    Author     : DAVADO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="<c:url value="/js/auctionpart.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/css/autionpart.css"/>"/>

<div class="mains">
        <div class="status">
            <div class="info">
                <div class="info1">
                    <a class="otb" href="<c:url value="/wall/${auction.login.id}"/>"><img class="img-fluid" 
                            src="<c:url value="${auction.login.image}" />" alt="${auction.login.full_name}"/>
                    </a>
                    <div class="tgcmt">
                        <h5>${auction.login.full_name}</h5>
                        <p><i>${auction.date}</i></p>
                    </div>
                </div>
                    <hr/>
                <div class="info2">
                    <p>${auction.content}</p>
                    <div> <img class="img-fluida" src="<c:url value="${auction.image}" />"/> </div>
                    <h4><b>Giá Mua Ngay :</b> ${auction.step*20} ngàn VND</h4>
                    <h4><b>Bước nhảy :</b> ${auction.step} ngàn VND</h4>   
                    <h4><b>Giá hiện tại :</b> ${top}  ngàn VND</h4>
                </div>
            </div>
            <div class="div">
                <c:if test= "${my==null}">
                    <c:if test= "${an!=null}">
                        <h2 class="text-danger text-center">${an}</h2>
                    </c:if>
                    <c:if test= "${check!=null}">
                        <h2 class="text-danger text-center">${check}</h2>
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
                                    <h5 class="text-danger">Giá tiền đấu giá lớn hơn đỉnh, không lớn hơn tối đa và chia hết cho bước nhảy</h5>
                                    <div class="nut">
                                        <form:input type="step" id="step" path="step" cssClass="form-control" placeholder="Nhập số tiền đấu giá (ngàn VNĐ)"/>
                                        <input type="submit" value="Đấu giá" class="btn btn-primary"/> 
                                    </div>
                                </c:if>
                            </form:form>
                        </div>
                    </c:if>
                </c:if>
                <div class="auction">
                    <c:if test= "${my!=null}">
                        <c:forEach var="allsell" items="${allsell}">
                            <div class="cmt">
                                <a href="<c:url value="/wall/${allsell.loginsell.id}"/>"><h5>${allsell.loginsell.full_name}</h5></a>
                                <h5>đã đấu giá ${allsell.value}00 VND</h5>
                            </div>
                        </c:forEach>
                        <a class="btt nav-link text-white"  href="<c:url value="/billsell/${auction.id}"/>" 
                           onclick="return confirm('Bạn có chắc muốn dừng phiên đấu giá này ?');" >Dừng phiên đấu giá này </a>
                    </c:if> 
                </div>
            </div>
        </div>
</div>


