<%-- 
    Document   : dashboard
    Created on : Jul 7, 2021, 9:23:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800&display=swap" rel="stylesheet">
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

            .btn {
                font-family: 'Open Sans';
                font-size: 1em;
                display: flex;
                justify-content: center;
                align-items: center;
                border: none;
                background: #fff;
                color: #262626;
                border-radius: 10px;
                height: 50px;
                font-weight: 600;
                min-width: 100px;
                padding: 0px 20px;
                transition: 0.2s ease;
                cursor: pointer;
                outline: 0;
            }

            .btn:hover {
                color: #FFF;
                background: cadetblue;
            }

            .btn-small {
                min-width: 50px;
            }

            .text-field {
                padding: 10px;
                height: 50px;
                width: 200px;
                border: 1px solid #f0f0f0;
                border-radius: 10px;
                margin: 0px 10px 0 0;
            }

            .title {
                font-size: 1.3em;
            }

            .subtitle {
                font-size: 1.1em;
            }

            /* Calendar */
            /*
                        .container {
                            flex-direction: column;
                            justify-content: center;
                            align-items: center;
                        }
            
                        .calendar-assets {
                            display: block;
                            align-items: left;
                        }*/
            /*
                        #currentDate{
                            font-size:20px;
                        }
            
                        .field {
                            flex-direction: column;
                            align-items: left;
                            justify-content: center;
                            margin: 20px 0;
                        }
            
                        .field label {
                            margin: 2px;
                        }
            
                        .form-input {
                            display: flex;
                            width: 100%;
                            align-items: center;
                            justify-content: center;
                        }*/

            .container {
                display: inline-block;
                flex-direction: column;
                justify-content: center;
                align-items: center;
            }

            .field {
                flex-direction: column;
                align-items: left;
                justify-content: center;
                margin: 20px 0;
            }

            .form-input btn{
                display: inline-block;
                align-items: center;
                justify-content: center;
            }

            .form-input button{
                display: inline-block;
            }

        </style>
        <link rel="stylesheet" href="style.css">
        <script type="text/javascript">
            window.onload = function () {
                var chart = new CanvasJS.Chart("chartContainer", {

                    title: {
                        text: "User Registration Statistics : Year 2021"
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

            function setDate(form) {
                let newDate = new Date(form.date.value);
                date = new Date(newDate.getFullYear(), newDate.getMonth(), newDate.getDate() + 1);
                generateCalendar();
                return false;
            }

        </script>
        <script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    </head>
    <body>
        <h1 id="headline">DASHBOARD</h1>
        <!-- subject -->
        <br><br><br>
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
        <div class="dropdowns">
            <button class="dropbtn">OPTION</button>
            <div class="dropdown-content">
                <c:forEach items="${requestScope.regislist}" var="reg">
                    <a href="DashboardMonth?rmonth=${reg.month}">Show by Month: ${reg.month}</a>
                </c:forEach>
            </div>
        </div>

        <div class="container">
            <div class="calendar-assets">
                <h1 id="currentDate"></h1>
                <div class="field">
                    <form class="form-input" id="date-search" onsubmit="DashboardDate" action="DashboardDate">
                        <input type="date" class="text-field" name="date" id="date" required>
                        <button type="submit" class="btn btn-small" title="Pesquisar"><i class="fas fa-search"></i></button>
                    </form>
                </div>
            </div>
        </div>

        <br><br><br><br><br>
        <div class="chart" id="chartContainer" style="height: 300px; width: 70%"></div>
        <!-- regis end-->

        <br>
        <div class="stats">
            <a>There are ${requestScope.count0 + requestScope.count1} registration(s) has been made this year.</a>
            <a>- ${requestScope.count1} order(s) has been done.</a>
            <a>- ${requestScope.count0} order(s) still pending.</a>
            <br>
            <a>Our system record ${requestScope.usercount} Customer(s) at this moment.</a>
            <a>- ${requestScope.usercount_check} Customer(s) has made their registration to start learning.</a>
        </div>

        <br><br><br><br><br>

        <c:if test="${sessionScope.USER.roleID == 'ADM'}">
            <a href="adminview.jsp"> back to the main page</a>
        </c:if>
        <c:if test="${sessionScope.USER.roleID == 'MKT'}">
            <a href="makview.jsp"> back to the main page</a>
        </c:if>
        <c:if test="${sessionScope.USER.roleID == 'SAL'}">
            <a href="salview.jsp"> back to the main page</a>
        </c:if>
        <!--        <a href="adminview.jsp"> back to the main page</a>-->


        <br>
        <script src="https://kit.fontawesome.com/812e771e48.js" crossorigin="anonymous"></script>
    </body>
</html>
