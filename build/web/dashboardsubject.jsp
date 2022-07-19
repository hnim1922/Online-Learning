<%-- 
    Document   : subjectdashboard
    Created on : Jul 7, 2021, 10:29:48 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .dropbtn {
                background-color: cadetblue;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                border-radius: 10px;
            }

            .dropdowns {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 12px;
                text-decoration: none;
                text-align: center;
                display: block;
            }

            .dropdown-content a:hover {background-color: #ddd;}

            .dropdowns:hover .dropdown-content {display: block;}

            .dropdowns:hover .dropbtn {
                background-color: rgb(71, 117, 119);
                color: white;
            }

            .headline{
                color: black;
                font-size: 50px;
                font-weight: 900;
                text-align: center;
            }

            .chart{
                margin-left: auto; 
                margin-right: auto;
                border: black;
            }

            .stats{
                font-size: 30px;
                font-weight: 100;
                text-align: left;
                padding: 10px;
            }
        </style>
        <link rel="stylesheet" href="style.css">
        <script type="text/javascript">
            window.onload = function () {
                var chart = new CanvasJS.Chart("chartContainer", {

                    title: {
                        text: "Subject Registration Statistics : Year 2021"
                    },
                    data: [
                        {
                            type: "column",
                            dataPoints: [
            <c:forEach items="${requestScope.regislist}" var="reg">
                                {label: "${reg.month}", y: ${reg.countmonth}},
            </c:forEach>
                            ]
                        }
                    ]
                });
                chart.render();
            }
        </script>
        <script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    </head>
    <body>
        <h1 id="headline">DASHBOARD</h1>
        <!-- subject -->
        <div class="dropdowns">
            <button class="dropbtn">SUBJECT</button>
            <div class="dropdown-content">
                <c:forEach items="${requestScope.sublist}" var="sub">
                    <a href="DashboardSubject?sid=${sub.subjectID}">${sub.name}</a>
                </c:forEach>
            </div>
        </div>
        <!-- subject end -->

        <!-- regis -->
<!--        <div class="dropdowns">
            <button class="dropbtn">OPTION</button>
            <div class="dropdown-content">
                <c:forEach items="${requestScope.regislist}" var="reg">
                    <a href="DashboardMonth?rmonth=${reg.month}">Show by Month: ${reg.month}</a>
                </c:forEach>
            </div>
        </div>-->

        <br><br><br><br><br>
        <div class="chart" id="chartContainer" style="height: 300px; width: 70%"></div>
        <!-- regis end-->

        <br><br><br><br><br>
        <button class="dropbtn" form="dashboard">Back to Dashboard</button>
        <form method="POST" action="Dashboard" id="dashboard">
        </form>
        <br>
        
        <c:if test="${sessionScope.USER.roleID == 'ADM'}">
            <a href="adminview.jsp"> back to the main page</a>
        </c:if>
        <c:if test="${sessionScope.USER.roleID == 'MKT'}">
            <a href="makview.jsp"> back to the main page</a>
        </c:if>
        <c:if test="${sessionScope.USER.roleID == 'SAL'}">
            <a href="salview.jsp"> back to the main page</a>
        </c:if>
    </body>
</html>
