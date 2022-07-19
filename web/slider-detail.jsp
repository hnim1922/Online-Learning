<%-- 
    Document   : slider-detail
    Created on : Jul 3, 2021, 9:17:35 PM
    Author     : acer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Slider Detail</title>
        <link rel="stylesheet" href="slider-detail.css">
    </head>
    <body>
        <div class="slider-details-container" style="width: 100%;">
            <div class="slider-image">
                <img src="${requestScope.slider.image}" alt="subject-thumbnail"/>
            </div>
            <h1 style="margin-left: 20px; margin-bottom: 20px;  font-size: 3em;">${requestScope.slider.title}</h1>
            <a class="backlink" href="${requestScope.slider.backlink}">  ${requestScope.slider.backlink} </a>
            <c:choose>
                <c:when test="${slider.status eq true}">
                    <div class="subject-categories-card" style="margin-left: 20px;">Available</div>
                </c:when>
                <c:otherwise>
                    <div class="subject-categories-card" style="margin-left: 20px;">Unavailable</div>
                </c:otherwise>
            </c:choose>
            <a class="login-btn"  href="SliderList?page=1" style="position: absolute; right: 0px; bottom: 20px; text-decoration: none;"> Go back </a>
        </div>
    </body>
</html>
