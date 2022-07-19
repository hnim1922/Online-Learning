<%-- 
    Document   : reset-password-password-input
    Created on : Jun 6, 2021, 9:49:36 AM
    Author     : ihtng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Online Learning</title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
        </style>
        <link rel="stylesheet" href="style.css">

    </head>
    <body>
        <header>
            <div class="logo">
                <h1>Online Learning</h1>
            </div>
            <div class="topnav">
                <a href="Homepage">Home</a>
                <a href="CourseList?page=1">Course</a>
                <a href="LoadBlogList?index=1">Blog</a>
                <a href="#contact">Contact</a>
            </div>
            <div class="search-bar">
                <input type="text" placeholder="Search..">
            </div>
            <div class="topbar-right">
                <button class="login-btn"
                        onclick="document.getElementById('login-form').style.display = 'block'">Login</button>
                <button class="signup-btn" onclick="openSignupForm();">Signup</button>
            </div>

            <!-- Login box -->
            <div id="login-form" class="modal">
                <form action="" class="modal-content animate" method="POST">
                    <div class="container">
                        <h1>Welcome to Online Learning</h1>
                        <label for="email"><b>Email</b></label>
                        <input type="email" placeholder="Enter Email" name="email" id="email" required>
                        <br><br>
                        <label for="psw"><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="psw" id="psw" required>
                        <br><br>
                        <button type="" onclick="login()">Login</button>
                        <button
                            onclick="document.getElementById('login-form').style.display = 'none'; document.getElementById('reset-password-email-form').style.display = 'block'">Reset
                            Password</button>
                        <button
                            onclick="document.getElementById('login-form').style.display = 'none'; document.getElementById('change-password-form').style.display = 'block'">Change
                            Password</button>
                        <button onclick="document.getElementById('login-form').style.display = 'none'">Cancel</button>
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

            <!-- Signup box -->
            <div class="modal" id="signup-form">
                <form action="" class="modal-content animate" method="POST">
                    <div class="container">
                        <h1>Signup</h1>
                        <label for="fullname"><b>Fullname</b></label>
                        <input type="text" placeholder="Enter Fullname" name="fname" required>
                        <br><br>
                        <label for="gender"><b>Gender</b></label>
                        <input type="radio" id="male" name="gender" value="male">
                        <label for="male">Male</label>
                        <input type="radio" id="female" name="gender" value="female">
                        <label for="female">Female</label>
                        <br><br>
                        <label for="email"><b>Email</b></label>
                        <input type="email" placeholder="Enter Email" name="email" required>
                        <br><br>
                        <label for="phone"><b>Phone number</b></label>
                        <input type="tel" placeholder="Enter Phone number" name="pnumber" required>
                        <br><br>
                        <label for="psw"><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="psw" required>
                        <br><br>
                        <label for="re-psw"><b>Retype Password</b></label>
                        <input type="re-password" placeholder="Enter Password again" name="re-psw" required>
                        <br><br>
                        <button type="submit">Signup</button>
                        <button onclick="document.getElementById('signup-form').style.display = 'none'">Cancel</button>
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
            <div class="modal" id="reset-password-password-form" style="display: block;">
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
                                onclick="window.location.href='Homepage'">Cancel</button>
                    </div>
                </form>
            </div>
        </header>
    </body>
</html>
