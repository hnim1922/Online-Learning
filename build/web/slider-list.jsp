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
        <div class="open-btn"  onclick="document.getElementById('slider-add-form').style.display = 'block'">Add Slider</div>
        <form action="SearchSliderList">
            <div class="search-bar" >
                <input type="hidden" name="page" value="1">
                <input type="text" name="keyword" placeholder="Search..">
            </div>
        </form>
        <div id="slider-add-form" class="modal">
            <form action="AddSlider" method="POST" class="modal-content animate" >
                <table>
                    <tr>
                    <h1 class="information">Add Slider Form</h1>
                    </tr>
                    <tr>
                        <td> Title</td>
                        <td><input type="text" name="title"/>
                            <font color="red">${requestScope.INVALID.errorTitle}</font>
                        </td>
                    </tr>
                    <tr>
                        <td> Image:</td>
                        <td><input type="text" name="image"/>
                            <font color="red">${requestScope.INVALID.errorImage}</font>
                        </td>
                    </tr>
                    <tr>
                        <td> Back Link:</td>
                        <td><input type="text" name="backlink">
                             <font color="red">${requestScope.INVALID.errorBacklink}</font>
                        </td>
                    </tr>
                    <tr>
                        <td> Status:</td>
                        <td>
                            <select name="status" id="status">
                                <option value="True">Available</option>
                                <option value="False">Unavailable</option>

                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2"><input class="login-btn" type="submit" value="Add"></td>
                        <td>                        
                            <button class="login-btn" type="reset" onclick="document.getElementById('slider-add-form').style.display = 'none'">Cancel</button></td>
                    </tr>
                </table>
            </form>         
        </div>
        <div class="slider-list-container" style="width: 100%;">
            <div  class="slider-list">
                <c:forEach  var="slider" items="${sliderList}">
                    <div  class="${slider.status}" name="${slider.status}" id="slider-card">
                        <img src="${slider.image}" alt="course-thumbnail" class="slider-image" />
                        <div class="slider-information">
                            <h3><b>${slider.title}</b></h3>
                            <c:choose>
                                <c:when test="${slider.status eq true}">
                                    <div class="status-card">Available</div>
                                    <script>
                                        function checkUnvail()
                                        {
                                            unavail = document.getElementsByClassName(${slider.status});
                                            check = document.getElementById("check-status");
                                            check1 = document.getElementById("check-status1");
                                            for (i = 0; i < unavail.length; i++)
                                                if (check1.checked == true)
                                                {
                                                    unavail[i].style.display = "none";
                                                } else
                                                {
                                                    unavail[i].style.display = "block";
                                                }
                                        }
                                    </script>
                                </c:when>
                                <c:otherwise>
                                    <div class="status-card">Unavailable</div>
                                    <script>
                                        function checkAvail()
                                        {
                                            avail = document.getElementsByClassName(${slider.status});
                                            check1 = document.getElementById("check-status1");
                                            check = document.getElementById("check-status");
                                            for (i = 0; i < avail.length; i++)
                                                if (check.checked == true)
                                                {
                                                    avail[i].style.display = "none";
                                                } else
                                                {
                                                    avail[i].style.display = "block";
                                                }
                                        }
                                    </script>
                                </c:otherwise>
                            </c:choose> 
                            <div id="${slider.sliderID}" class="modal">
                                <form action="ChangeSlider?sliderID=${slider.sliderID}" method="POST"  class="modal-content animate">
                                    <table>
                                        <tr>
                                        <h1 class="information">Change Slider information form </h1>
                                        </tr>
                                        <tr>
                                            <td> Slider ID:</td>
                                            <td><input disabled="" type="text" value="${slider.sliderID}" readonly/>
                                                <input type="hidden" name="quizID" value="${slider.sliderID}"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> Title</td>
                                            <td><input type="text" name="title" value="${slider.title}"/>
                                                <font color="red">${requestScope.INVALID.errorTitle}</font>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> Image:</td>
                                            <td><input type="text" name="image" value="${slider.image}"/>
                                                <font color="red">${requestScope.INVALID.errorImage}</font>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> Back Link:</td>
                                            <td><input type="text" name="backlink"/>
                                        </tr>
                                        <tr>
                                            <td> Status:</td>
                                            <td>
                                                <select name="status" id="status">
                                                    <option value="True">Available</option>
                                                    <option value="False">Unavailable</option>

                                                </select>
                                            </td>
                                        </tr>
                                        <td colspan="2"><input class="login-btn" type="submit" value="Change"></td>
                                        <td>                        
                                            <button class="login-btn" type="reset" onclick="document.getElementById('${slider.sliderID}').style.display = 'none'">Cancel</button></td>
                                        </tr>
                                    </table>
                                </form>         
                            </div>
                        </div>
                        <div class="detail-btn" onclick="location.href = 'SliderDetail?sliderID=${slider.sliderID}'"> 
                            Details
                        </div>
                        <div  class="login-btn" onclick="document.getElementById('${slider.sliderID}').style.display = 'block'"> 
                            Edit
                        </div>

                    </div>
                </c:forEach>
            </div>
            <div class="page-route">
                <c:forEach var="i" begin="1" end="${Math.ceil(allSliderList.size()/3)}">
                    <a href="SliderList?page=${i}" <c:if test="${param.page == i}">class="on-page"</c:if>
                       >${i}</a>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
