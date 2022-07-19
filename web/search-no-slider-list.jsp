<%-- 
    Document   : slider-list
    Created on : Jun 29, 2021, 3:06:25 AM
    Author     : acer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Slider List</title>
        <link rel="stylesheet" href="slider-list.css">


    </head>
    <body>
        <header>
            <h1>Slider List</h1>
        </header>
        <div class="status-checkbox">
            <h2>Status:</h2>
            <div class="check-status" >
                <input type="checkbox" id="check-status"  name="available" value="Avalable" onclick="checkAvail()"> Available
                <input type="checkbox" id="check-status1"   name="unavailable" value="Unavalable" onclick="checkUnvail()"> Unavailable
            </div>
        </div>

        <form action="SearchSliderList">
            <div class="search-bar" >
                <input type="hidden" name="page" value="1">
                <input type="text" name="keyword" placeholder="Search..">
            </div>
        </form>
        <div class="slider-list-container" style="width: 100%;">
            <div class="slider-list">
               <h1 style="text-align: center;">No Slider available!</h1>
            </div>
        </div>
    </body>
</html>
