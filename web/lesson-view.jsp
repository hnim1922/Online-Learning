<%-- Document : course-list Created on : Jun 7, 2021, 7:32:34 PM Author : ihtng --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
        <link rel="stylesheet" href="course-list-style.css">

        <script>
            function openLoginForm() {
                document.getElementById("login-form").style.display = "block";
            }

            function openSignupForm() {
                document.getElementById("signup-form").style.display = "block";
            }

            function openResetPasswordForm() {
                document.getElementById("reset-password-form").style.display = "block";
            }

            function openChangePasswordForm() {
                document.getElementById("change-password-form").style.display = "block";
            }
            function openCourseRegister() {
                document.getElementById("course-register").style.display = "block";
            }

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
                <a class="active" href="CourseList?page=1">Course</a>
                <a href="LoadBlogList?index=1">Blog</a>
                <a href="#contact">Contact</a>
            </div>
            <form action="SearchCourseList">
                <div class="search-bar" >
                    <input type="hidden" name="page" value="1">
                    <input type="text" name="keyword" placeholder="Search..">
                </div>
            </form>
            <c:if test="${empty sessionScope.USER}">
                <div class="topbar-right">
                    <button class="login-btn"
                            onclick="document.getElementById('login-form').style.display = 'block'">Login</button>
                    <button class="signup-btn" onclick="openSignupForm();">Signup</button>
                </div>
            </c:if>
            <c:if test="${!empty sessionScope.USER}">
                <div class="topnav" style="margin-right: 20px;">
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
            </c:if>

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
                            <a href="MyCourse?page=1" id="button" class="login-btn" style="margin: auto;">My Courses</a>
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

            <!-- Login box -->
            <div id="login-form" class="modal">
                <form action="Login" class="modal-content animate" method="POST">
                    <img src="./images/ui-images/popup-illustration.svg" class="left-image">
                    <div class="container">
                        <div class="txt">
                            <h1>Welcome to Online Learning</h1>
                            <label for="email"><b>Email</b></label><br>
                            <input type="email" name="email" id="email" required>
                            <br><br>
                            <label for="psw"><b>Password</b></label><br>
                            <input type="password" name="psw" id="psw" required>
                            <br><br>
                        </div>
                        <div class="btn">
                            <button>Login</button>
                            <button
                                onclick="document.getElementById('login-form').style.display = 'none'">Cancel</button>
                            <br><br>
                            <a class="reset"
                               onclick="document.getElementById('login-form').style.display = 'none';document.getElementById('reset-password-email-form').style.display = 'block'">Reset
                                Password</a>
                            <a class="change"
                               onclick="document.getElementById('login-form').style.display = 'none';document.getElementById('change-password-form').style.display = 'block'">Change
                                Password</a>

                        </div>
                    </div>
                </form>
            </div>

            <!--Course register-->
            <div id="course-register" class="modal">
                <form action="CourseRegister?subjectID=${requestScope.subjectID}" class="modal-content animate" method="POST">
                    <div class="container">
                        <div class="txt">
                            <h1>${requestScope.subjectTitle}</h1>
                            <img class="course-register-thumbnail" src="${requestScope.subjectThumbnail}" alt="subject-thumbnail", style="width: 500px; height: 200px;"/>
                            <br><br>
                            <select name="salingPrice" style="
                                    width: 100px;
                                    height: 60px;
                                    font-size: 20px;
                                    border-radius: 10px;
                                    ">
                                <c:forEach items="${requestScope.pricepackageList}" var="pricepackage">
                                    <option value="${pricepackage.pricePackageID}">${pricepackage.salePrice}</option>
                                </c:forEach>
                            </select>
                            <br><br>
                            <div style="display: flex; justify-content: center;">
                                <button class="submit-button" style="width: max-content; white-space: nowrap;">Register Course</button>
                                <button class="cancel-button"
                                        onclick="document.getElementById('course-register').style.display = 'none'">Cancel</button>
                            </div>
                        </div>
                        <br><br>
                    </div>
                </form>
            </div>
            <!-- Change Password -->
            <div class="modal" id="change-password-form">
                <form action="ChangePassword" class="modal-content animate" method="POST">
                    <img src="images/ui-images/popup-illustration.svg" alt="alt" class="left-image" />
                    <div class="container">
                        <h1 style="color: #00618A;">Change Password</h1>
                        <label for="email"><b>Email</b></label>
                        <br>
                        <input type="email" name="email" required>
                        <br><br>
                        <label for="psw"><b>Old Password</b></label>
                        <br>
                        <input type="password" name="psw" required>
                        <br><br>
                        <label for="new-psw"><b>New Password</b></label>
                        <br>
                        <input type="password" name="new-psw" required>
                        <br><br>
                        <label for="new-psw"><b>Retype New Password</b></label>
                        <br>
                        <input type="password" name="re-psw" required>
                        <br><br>
                        <button class="submit-button">Submit</button>
                        <button class="cancel-button"
                                onclick="document.getElementById('change-password-form').style.display = 'none'">Cancel</button>
                    </div>
                </form>
            </div>
            <!-- SignUp box -->
            <div class="modal" id="signup-form">
                <form action="SignUp" class="modal-content animate" method="POST">
                    <img src="images/ui-images/popup-illustration.svg" alt="alt" class="left-image" />
                    <div class="container">
                        <h1 style="color: #00618A;">Sign Up For Free</h1>
                        <table>
                            <tr>
                                <td>
                                    <label for="email"><b>Email Address</b></label>
                                    <br>
                                    <input type="email" name="email" required>
                                </td>
                                <td>
                                    <label for="fullname"><b>Full Name</b></label>
                                    <br>
                                    <input type="text" name="fullname" required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="new-password"><b>Password</b></label>
                                    <br>
                                    <input type="password" name="new-password" required>
                                </td>
                                <td>
                                    <label for="new-password"><b>Re-enter Password</b></label>
                                    <br>
                                    <input type="password" name="re-enter-password" required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="gender"><b>Gender</b></label>
                                    <br>
                                    <input type="text" name="gender" required pattern="^M|^F"
                                           title="Please enter M(Male) and F(Female)">
                                </td>
                                <td>
                                    <label for="phone-number"><b>Phone Number</b></label>
                                    <br>
                                    <input type="tel" name="phone-number" required pattern="[0-9]{10}"
                                           title="Please enter a 10 number phone number">
                                </td>
                            </tr>
                            <tr>
                                <td><button id="signup-button" onclick="">Sign Up</button></td>
                                <td>
                                    <button id="cancel-button-signup"
                                            onclick="document.getElementById('signup-form').style.display = 'none'">Cancel</button>
                                </td>
                            </tr>
                        </table>
                    </div>
                </form>
            </div>

            <!-- Reset Password box -->
            <!<!-- Email input -->
            <div class="modal" id="reset-password-email-form">
                <form action="SendEmail" class="modal-content animate" method="POST">
                    <img src="images/ui-images/popup-illustration.svg" alt="alt" class="left-image" />
                    <div class="container">
                        <h1 style="color: #00618A;">Reset Password</h1>
                        <label for="email"><b>Email</b></label>
                        <input type="email" name="email" required>
                        <br><br>
                        <button class="submit-button">Submit</button>
                        <button class="cancel-button"
                                onclick="document.getElementById('reset-password-email-form').style.display = 'none'">Cancel</button>
                    </div>
                </form>
            </div>
            <!-- Password input -->
            <div class="modal" id="reset-password-password-form">
                <form action="ResetPassword" class="modal-content animate" method="POST">
                    <img src="images/ui-images/popup-illustration.svg" alt="alt" class="left-image" />
                    <div class="container">
                        <h1 style="color: #00618A;">Reset Password</h1>
                        <label for="email"><b>New Password</b></label>
                        <input type="password" name="new-psw" required>
                        <br><br>
                        <label for="email"><b>Retype Password</b></label>
                        <input type="password" name="re-new-psw" required>
                        <br><br>
                        <button class="submit-button">Submit</button>
                        <button class="cancel-button"
                                onclick="document.getElementById('reset-password-password-form').style.display = 'none'">Cancel</button>
                    </div>
                </form>
            </div>
        </header>
        <main>
            <div class="sider-course-list">
                <h3>Lesson View of ${subjectName}</h3>

                <c:forEach var="lesson" items="${lessons}">
                    <div class="${requestScope.lessonID == lesson.lessonID ? 'present-lesson' : 'featured-subject-card'}" onclick="location.href = 'LessonView?subjectID=${subjectID}&lessonID=${lesson.lessonID}&courseID=${courseID}';">
                        <div class="featured-course-title">
                            <h4>${lesson.lessonName}</h4>
                        </div>
                        <div class="featured-subject-btn">
                            <img src="images/ui-images/chevron-right-solid.svg" alt="arrow-right"/>
                        </div>
                    </div>
                </c:forEach>

                <h3>Quiz View of ${subjectName}</h3>
                <c:if test="${quizzes.size() > 0}">
                    <c:forEach var="quiz" items="${quizzes}">
                        <div class="featured-subject-card" onclick="location.href ='Quizhandle?quizID=${quiz.quizID}'">
                            <div class="featured-course-title">
                                <h4>Quiz ${quiz.quizID}</h4>
                                <c:if test="${gethighscore>0}">
                                    <c:out value="${gethighscore}"/>
                                </c:if>
                            </div>
                            <div class="featured-subject-btn">
                                <img src="images/ui-images/chevron-right-solid.svg" alt="arrow-right"/>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${quizzes.size() == 0}">
                    <h4>No quiz for this lesson!</h4>
                </c:if>
            </div>
            <c:if test="${lessonID!=0}">
                <div class="course-details-container" style="width: 100%;">
                    <h1 style="margin-left: 20px; margin-bottom: 20px;  font-size: 3em;"><c:out value="${requestScope.lessonContent.lessonName}"/></h1>
                    <p class="subject-info">${requestScope.lessonContent.content}<p>
                        <c:if test="${lessonStatus != true}">
                            <a href="UpdateCourseStatus?courseID=${courseID}&lessonID=${requestScope.lessonID}&subjectID=${subjectID}" class="login-btn"
                               style="position: absolute; right: 0px; bottom: 20px;">Mark as done</a>
                        </c:if>
                        <c:if test="${lessonStatus == true}">
                        <p style="position: relative; float: right; margin-right: 20px;">You have finished this lesson.</p>
                    </c:if>
                </div>
            </c:if >
            <c:if test="${lessonID==0}">
                <h1>Choose a lesson and get started!</h1>
            </c:if>
        </main>
    </body>

</html>