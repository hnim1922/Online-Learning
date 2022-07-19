<%-- 
    Document   : dashboardmonth
    Created on : Jul 8, 2021, 12:35:15 AM
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
                font-size: 30px;
                font-weight: 900;
                text-align: center;
            }

            .chart{
                margin-left: auto; 
                margin-right: auto;
                border: black;
            }

        </style>
        <link rel="stylesheet" href="style.css">
        <script type="text/javascript">
            window.onload = function () {
            var chart = new CanvasJS.Chart("chartContainer", {

            title: {
            <c:choose>
                <c:when test="${requestScope.req_month eq 1}">
            text: "Registration Statistics : January"
                </c:when>
                <c:when test="${requestScope.req_month eq 2}">
            text: "Registration Statistics : February"
                </c:when>
                <c:when test="${requestScope.req_month eq 3}">
            text: "Registration Statistics : March"
                </c:when>
                <c:when test="${requestScope.req_month eq 4}">
            text: "Registration Statistics : April"
                </c:when>
                <c:when test="${requestScope.req_month eq 5}">
            text: "Registration Statistics : May"
                </c:when>
                <c:when test="${requestScope.req_month eq 6}">
            text: "Registration Statistics : June"
                </c:when>
                <c:when test="${requestScope.req_month eq 7}">
            text: "Registration Statistics : July"
                </c:when>
                <c:when test="${requestScope.req_month eq 8}">
            text: "Registration Statistics : August"
                </c:when>
                <c:when test="${requestScope.req_month eq 9}">
            text: "Registration Statistics : September"
                </c:when>
                <c:when test="${requestScope.req_month eq 10}">
            text: "Registration Statistics : October"
                </c:when>
                <c:when test="${requestScope.req_month eq 11}">
            text: "Registration Statistics : November"
                </c:when>
                <c:when test="${requestScope.req_month eq 12}">
            text: "Registration Statistics : December"
                </c:when>
            </c:choose>
            },
                    data: [
                    {
                    type: "column",
                            dataPoints: [
            <c:forEach items="${requestScope.regislist}" var="reg">
                            {label: "${reg.date}", y: ${reg.countdate}},
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
        <h1 class="headline">DASHBOARD</h1>
        <c:choose>
            <c:when test="${requestScope.req_month eq 1}">
                <a class="headline">Below is the chart show information of January</a>   
            </c:when>
            <c:when test="${requestScope.req_month eq 2}">
                <a class="headline">Below is the chart show information of February</a>   
            </c:when>
            <c:when test="${requestScope.req_month eq 3}">
                <a class="headline">Below is the chart show information of March</a>   
            </c:when>
            <c:when test="${requestScope.req_month eq 4}">
                <a class="headline">Below is the chart show information of April</a>   
            </c:when>
            <c:when test="${requestScope.req_month eq 5}">
                <a class="headline">Below is the chart show information of May</a>   
            </c:when>
            <c:when test="${requestScope.req_month eq 6}">
                <a class="headline">Below is the chart show information of June</a>   
            </c:when>
            <c:when test="${requestScope.req_month eq 7}">
                <a class="headline">Below is the chart show information of July</a>   
            </c:when>
            <c:when test="${requestScope.req_month eq 8}">
                <a class="headline">Below is the chart show information of August</a>   
            </c:when>
            <c:when test="${requestScope.req_month eq 9}">
                <a class="headline">Below is the chart show information of September</a>   
            </c:when>
            <c:when test="${requestScope.req_month eq 10}">
                <a class="headline">Below is the chart show information of October</a>   
            </c:when>
            <c:when test="${requestScope.req_month eq 11}">
                <a class="headline">Below is the chart show information of November</a>   
            </c:when>
            <c:when test="${requestScope.req_month eq 12}">
                <a class="headline">Below is the chart show information of December</a>   
            </c:when>
        </c:choose>



        <br><br><br><br><br>
        <div class="chart" id="chartContainer" style="height: 300px; width: 70%"></div>


        <br><br><br><br><br>
        <button class="dropbtn" form="dashboard">Back to Dashboard</button>
        <form method="POST" action="Dashboard" id="dashboard">
        </form>
        
        
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
