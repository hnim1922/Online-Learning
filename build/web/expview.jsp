<%-- 
    Document   : expview
    Created on : Jun 4, 2021, 2:24:19 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <h1>EXPERT PAGES</h1>
        <div class="topbar-right">
            <c:if test="${!empty requestScope.INVALID}">
                <button class="login-btn" id="profile" onfocus="autoClickProfile()" autofocus
                        onclick="document.getElementById('profile-form').style.display = 'block';
                                document.getElementById('change').focus()" >User Profile</button>
            </c:if>
            <c:if test="${empty requestScope.INVALID}">
                <button class="login-btn" 
                        onclick="document.getElementById('profile-form').style.display = 'block'" >User Profile</button>
            </c:if>
            <!-- profile box -->
            <div id="profile-form" class="modal">
                <div class="modal-content animate" >
                    <form method="POST">
                        <h1 class="modal-content animate" >Your information</h1>
                        <table border="1">
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
                                <td>
                                    <button form="x" class="login-btn" 
                                            onclick="document.getElementById('profile-form').style.display = 'none'">Cancel</button>
                                </td>
                                <td>
                                    <c:if test="${!empty requestScope.INVALID}">
                                        <button form="x" class="login-btn" id="change" onfocus="autoClickChange()" autofocus
                                                onclick="document.getElementById('change-form').style.display = 'block'
                                                        document.getElementById('profile-form').style.display = 'none'"   >Change Info</button>
                                    </c:if>
                                    <c:if test="${empty requestScope.INVALID}">
                                        <button form="x" class="login-btn"
                                                onclick="document.getElementById('change-form').style.display = 'block'
                                                        document.getElementById('profile-form').style.display = 'none'"   >Change Info</button>
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <form method="POST" action="Logout">
                        <button class="modal-content animate" id="logout">Logout</button>
                    </form>
                </div>
            </div>
            <!-- end profile box -->

            <!-- CHANGE BOX -->
            <div id="change-form" class="modal">
                <form action="ChangeExp" method="POST" class="modal-content animate">
                    <table>
                        <tr>
                        <h1 class="modal-content animate">Change information form</h1>
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
            <div class="pagination">
                <a class="login-btn" href="LessonList?page=1">Lesson List</a>
                <a class="login-btn" href="QuizzesList?page=1">Quizzes List</a>
                <a class="login-btn" href="QuestionList?index=1">Questions List</a>
            </div>
        </div>
    </body>
</html>
