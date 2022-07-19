<%-- 
    Document   : cusview
    Created on : Jun 3, 2021, 11:47:44 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Online Learning</title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');

            #logout{
                margin-right: 30px;
                background-color: rgb(206, 25, 61);
                border: none;
                border-radius: 10px;
                width: 150px;
                height: 50px;
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.5);
                color: white;
                font-size: 18px;
                font-weight: 500;
                transition: 0.3s;

                text-align: center;
                margin: auto;
                display: flex;
                display: grid;
                padding: 0 16px;
            }

            #profile{
                color: black;
                display: flex;
                text-decoration: none;
                align-items: center;
                padding: 0 16px;
                font-size: 20px;
                transition: 0.3s;

                display: flex;
                justify-content: space-around;
                border: none;
            }

            #profile{
                background-color: white;
                color: black;
            }

            #profile:hover{
                background-color: #ddd;
                color: black;
            }

            table{
                align-items: center;
                padding: 16px;
            }

            .information{
                background-color: #fefefe;
                border-radius: 20px;
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
                margin: 5% auto 15% auto;
                border: 1px solid #888;
                width: max-content;
                padding: 0 16px;
            }
            
            #button{
                padding: 0 16px;
                align-items: center;
            }
            

        </style>
        <link rel="stylesheet" href="style.css">

        <script>
            function autoClickProfile() {
                document.getElementById("profile").click();
            }
            function autoClickChange() {
                document.getElementById("change").click();
            }
        </script>
    </head>

    <body>
        <header>
            <div class="logo">
                <h1>Online Learning</h1>
            </div>
            <div class="topnav">
            <a href="Homepage">Home</a>
            <a href="CourseList?page=1">Course</a>
                <a class="active" href="LoadBlogList?index=1">Blog</a>
                <a href="#contact">Contact</a>
            </div>
            <div class="search-bar">
                <input type="text" placeholder="Search..">
            </div>
            <div class="topnav">
                <c:if test="${!empty requestScope.INVALID}">
                    <button  id="profile" onfocus="autoClickProfile()" autofocus
                             onclick="document.getElementById('profile-form').style.display = 'block';
                                     document.getElementById('change').focus()" >${sessionScope.USER.fullname}</button>
                </c:if>
                <c:if test="${empty requestScope.INVALID}">
                    <button  id="profile"
                             onclick="document.getElementById('profile-form').style.display = 'block'" >${sessionScope.USER.fullname}</button>
                </c:if>
            </div>
            <br>
        </header>

        <!-- profile box -->
        <div id="profile-form" class="modal">
            <div class="modal-content animate">
                <form method="POST">
                    <h1 class="information">Your information</h1>
                    <table>
                        <tr>
                            <td>User ID</td>
                            <td>${sessionScope.USER.userID}</td>
                        </tr>
                        <tr>
                            <td>Role ID</td>
                            <td>${sessionScope.USER.roleID}</td> 
                        </tr>    
                        <tr> 
                            <td>Email</td>
                            <td>${sessionScope.USER.email}</td>
                        </tr>  
                        <tr>
                            <td>Fullname</td>
                            <td>${sessionScope.USER.fullname}</td> 
                        </tr>
                        <tr>
                            <td>Gender</td>
                            <td>${sessionScope.USER.gender}</td> 
                        </tr>
                        <tr>
                            <td>Phone</td>
                            <td>${sessionScope.USER.phone}</td>
                        </tr>
                        <tr>
                            <td id="button">
                                <button form="x" class="login-btn" 
                                        onclick="document.getElementById('profile-form').style.display = 'none'">Cancel</button>
                            </td>
                            <td id="button">
                                <c:if test="${!empty requestScope.INVALID}">
                                    <button form="x" class="login-btn" id="change" onfocus="autoClickChange()" autofocus id="button"
                                            onclick="document.getElementById('change-form').style.display = 'block'
                                                    document.getElementById('profile-form').style.display = 'none'"   >Change Info</button>
                                </c:if>
                                <c:if test="${empty requestScope.INVALID}">
                                    <button form="x" class="login-btn" id="button"
                                            onclick="document.getElementById('change-form').style.display = 'block'
                                                    document.getElementById('profile-form').style.display = 'none'"   >Change Info</button>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </form>
                <form method="POST" action="Logout">
                    <button class="information" id="logout">Logout</button>
                </form>
            </div>
        </div>
        <!-- end profile box -->

        <!-- CHANGE BOX -->
        <div id="change-form" class="modal">
            <form action="ChangeCus" method="POST" class="modal-content animate">
                <table>
                    <tr>
                    <h1 class="information">Change information form</h1>
                    </tr>
                    <tr>
                        <td> user ID:</td>
                        <td><input disabled="" type="text" value="${sessionScope.USER.userID}" readonly/>
                            <input type="hidden" name="userID" value="${sessionScope.USER.userID}"/>
                            <font color="red">${requestScope.INVALID.userIDError}</font>
                        </td>
                    </tr>
                    <tr>
                        <td> role ID:</td>
                        <td><input disabled="" type="text" value="${sessionScope.USER.roleID}" readonly/>
                            <input type="hidden" name="roleID" value="${sessionScope.USER.roleID}"/>
                            <font color="red">${requestScope.INVALID.roleIDError}</font>
                        </td>
                    </tr>
                    <tr>
                        <td> email:</td>
                        <td><input disabled="" type="text" value="${sessionScope.USER.email}" readonly/>
                            <input type="hidden" name="email" value="${sessionScope.USER.email}"/>
                            <font color="red">${requestScope.INVALID.emailError}</font>
                        </td>
                    </tr>
                    <tr>
                        <td> fullname:</td>
                        <td><input type="text" name="fullname" value="${sessionScope.USER.fullname}"/>
                            <font color="red">${requestScope.INVALID.fullnameError}</font></td>
                    </tr>
                    <tr>
                        <td> gender:</td>
                        <td> 
                            <select name="gender" id="gender">
                                <option value="M">M</option>
                                <option value="F">F</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> phone:</td>
                        <td><input type="number" name="phone" value="${sessionScope.USER.phone}"/>
                            <font color="red">${requestScope.INVALID.phoneError}</font></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input class="login-btn" type="submit" value="Change"></td>
                        <c:if test="${empty requestScope.INVALID}">
                                <td>                        
                                    <button class="login-btn" type="reset" onclick="document.getElementById('change-form').style.display = 'none'">Cancel</button></td>
                                </c:if>
                    </tr>
                </table>
            </form>         
        </div>
        <!-- END CHANGE BOX -->

        <div class="course-list">
            <div class="course-card studied">
                <a href="NewServlet">
                    <img src="/images/course-thumbnail.jpg" alt="course-thumbnail" class="course-thumbnail">
                    <div class="course-information">
                        <h3><b>Course Name</b></h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod ducimus iusto vitae, soluta,
                            repellendus repudiandae error necessitatibus, veniam magni sed quisquam ullam. Saepe,
                            dignissimos
                            nisi doloremque odit dolor ex consequatur!</p>
                    </div>
                    <a href="#start-course" class="start-course-btn">Start Course</a>
                    <div class="progress-bar">
                        <div class="progress-complete" id="prgess-complete">50%</div>
                    </div>
                </a>
            </div>
            <div class="course-card not-study">
                <a href="#course-detail">
                    <img src="/images/course-thumbnail.jpg" alt="course-thumbnail" class="course-thumbnail">
                    <div class="course-information">
                        <h3><b>Course Name</b></h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod ducimus iusto vitae, soluta,
                            repellendus repudiandae error necessitatibus, veniam magni sed quisquam ullam. Saepe,
                            dignissimos
                            nisi doloremque odit dolor ex consequatur!</p>
                    </div>
                    <a class="start-course-btn" href="#start-course">Start Course</a>
                    <div class="progress-bar">
                        <div class="progress-complete" id="prgess-complete">50%</div>
                    </div>
                </a>
            </div>
        </div>

    </body>

</html>
