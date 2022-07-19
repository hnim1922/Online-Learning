<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Online Learning</title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
        </style>
        <link rel="stylesheet" href="style.css">

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

            function login() {
                location.href = "course_logined.html";
            }
        </script>
    </head>


<body>
    
    <header>
        <div class="logo">
            <h1>Online Learning</h1>
        </div>
        <div class="topnav">
            <a href="Homepage" class="active">Home</a>
            <a href="CourseList?page=1">Course</a>
            <a href="LoadBlogList?index=1">Blog</a>
            <a href="#contact">Contact</a>
        </div>
        <div class="search-bar">
            <input type="text" placeholder="Search..">
        </div>
        <div class="topbar-right">
            <button class="login-btn"
                onclick="document.getElementById('login-form').style.display='block'">Login</button>
            <button class="signup-btn" onclick="openSignupForm();">Signup</button>
        </div>

        <!-- Login box -->
        <div id="login-form" class="modal-login">
            <form action="Login" class="modal-content animate" method="POST">
                <img src="./images/ui-images/popup-illustration.svg" class="left-image">
                <div class="container">
                    <div class="txt">
                    <h1>Welcome to Online Learning</h1>
                    <label for="email"><b>Email</b></label><br>
                    <input type="email"  name="email" id="email" required>
                    <br><br>
                    <label for="psw"><b>Password</b></label><br>
                    <input type="password" name="psw" id="psw" required>
                    <font color="red"> ${requestScope.ERROR} </font>
                    <br><br>
                    </div>
                        <div class="btn">
                    <button>Login</button>
                    <button  onclick="document.getElementById('login-form').style.display='none'">Cancel</button>
                    <br><br>
                    <a class="reset"
                        onclick="document.getElementById('login-form').style.display='none';document.getElementById('reset-password-email-form').style.display='block'">Reset
                        Password</a>
                    <a class="change"
                        onclick="document.getElementById('login-form').style.display='none';document.getElementById('change-password-form').style.display='block'">Change
                        Password</a>
               
                </div>
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
                <img src="images/ui-images/popup-illustration.svg" alt="alt" class="left-image"/>
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
                                <label for ="fullname"><b>Full Name</b></label>
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
                                <input type="text" name="gender" required pattern="^M|^F" title="Please enter M(Male) and F(Female)"> 
                            </td>
                            <td>
                                <label for="phone-number"><b>Phone Number</b></label>
                                <br>
                                <input type="tel" name="phone-number" required pattern="[0-9]{10}" title="Please enter a 10 number phone number"> 
                            </td>
                        </tr>
                        <tr>
                            <td><button id="signup-button" onclick="">Sign Up</button></td>
                            <td>
                                <button id="cancel-button-signup"
                        onclick="document.getElementById('signup-form').style.display='none'">Cancel</button>
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
                    <img src="/images/ui-images/popup-illustration.svg" alt="alt" class="left-image" />
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
       <div class="homepage__browse">
                 <h3>Latest post</h3>
                <c:forEach items="${firstFive}" var="blog">
                    <div class="latest-post ">
                        <div class="blog ">
                            <img class="small-thumbnail" src="${blog.thumbnail}">
                            <div class="blog-title ">
                                <a href="BlogDetails?blogID=${blog.blogID}">
                                    <strong>${blog.title}</strong>
                                    <br>
                                    <sub>${blog.updatedDate}</sub>
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            <h3>Contact us</h3>
            <div class="contact-info">
                <div class="contact-button">
                    <img src="./images/ui-images/facebook.svg" alt="facebook-logo" />
                </div>
                <div class="contact-button">
                    <img src="./images/ui-images/mail.svg" alt="mail-logo" />
                </div>
            </div>
       </div>
        <div class="slider">
            <div class="mySlides fade">
                <img src="images/ui-images/popup-illustration.svg"/>
                          </div>
              
              <div class="mySlides fade">
                <img src="images/ui-images/popup-illustration.svg" />
              </div>
              
              <div class="mySlides fade">
                <img src="images/ui-images/popup-illustration.svg" >
              </div>
              <div style="text-align:center">
                <span class="dot"></span> 
                <span class="dot"></span> 
                <span class="dot"></span> 
              </div>
        </div>
              <script>
                var slideIndex = 0;
                showSlides();
                function showSlides() {
                  var i;
                  var slides = document.getElementsByClassName("mySlides");
                  var dots = document.getElementsByClassName("dot");
                  for (i = 0; i < slides.length; i++) {
                    slides[i].style.display = "none";  
                  }
                  slideIndex++;
                  if (slideIndex > slides.length) {slideIndex = 1}    
                  for (i = 0; i < dots.length; i++) {
                    dots[i].className = dots[i].className.replace(" active", "");
                  }
                  slides[slideIndex-1].style.display = "block";  
                  dots[slideIndex-1].className += " active";
                  setTimeout(showSlides, 2000); // Change image every 2 seconds
                }
                </script>
 <div class="course-list-container" style="width: 100%;">
     <h3 class="title">Featured Subject</h3>
    <div class="featured-subject">
        
        <c:forEach var="featuredSubjectList" items="${featuredSubjectList}">    
            <div class="subject-container" onclick= "location.href = 'CourseDetails?subjectID=${featuredSubjectList.subjectID}';" >
            <img src=${featuredSubjectList.thumbnail}" alt="course-thumbnail" class="course-thumbnail"/>
                <div class="course-information">
                    <h3><b>${featuredSubjectList.name}</b></h3>
                    <div class="subject-categories-card">${featuredSubjectList.categoryName}</div>
                    <p>${featuredSubjectList.information}</p>                   
                </div>
           
                     <a href="#start-course" class="login-btn" style="position: absolute; right: 0px; bottom: 20px; text-decoration: none;"> Go to Subject</a>
            </div>
            
        </c:forEach>
            
    </div>
    </div>

<<div class="hot-post" >
    <div class="post-container">
        <h1> Hot Post</h1>
        <c:forEach var="latestPost" items="${firstFive}">
        <div class="post1">
    
                    <img src="${latestPost.thumbnail}}" class="thumbnail"/>
                    <a href="BlogDetails?blogID=${latestPost.blogID}" class="post-title" style="text-decoration: none">${latestPost.title}</a>
               
    </div>
                     </c:forEach>
    </div>  



    </body>

        