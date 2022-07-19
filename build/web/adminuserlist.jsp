<%-- 
    Document   : adminuserlist
    Created on : Jun 8, 2021, 10:12:14 PM
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
            @import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');

            #sortbox{
                display: inline-block;
            }

            .filterbox{
                display: inline;
            }

            .pagination {
                display: inline-block;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
            }

            .pagination a.activepage {
                background-color: #4CAF50;
                color: white;
                border-radius: 5px;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
                border-radius: 5px;
            }
        </style>
        <link rel="stylesheet" href="style.css">
        <script>
            function autoClickAdd() {
                document.getElementById("add").click();
            }
        </script>
    </head>
    <body>
        <h1>USERS LIST</h1>
        <c:if test="${!empty requestScope.USERSLIST}">

            <form action="AdminSearch" method="POST">
                Search user:  <input type="text" name="input"/>
                <select name="filter">
                    <option value="fullname">fullname</option>
                    <option value="email">email</option>
                    <option value="mobile">mobile</option>                    
                </select>
                <input type="submit" value="search">
            </form>    
            <br>

            <div class="filterbox">
                <form action="AdminFilter" method="POST">                
                    Filter by: 
                    <select name="gender" id="filtergender">
                        <option value="all">Both gender</option>
                        <option value="F">Female</option>
                        <option value="M">Male</option>
                    </select>
                    <select name="roleID" id="filterroleID">
                        <option value="all">All roles</option>
                        <option value="ADM">ADM</option>
                        <option value="CUS">CUS</option>
                        <option value="SAL">SAL</option>
                        <option value="MKT">MKT</option>
                        <option value="EXP">EXP</option>                                   
                    </select>
                    <input type="submit" value="filter">
                </form> 
            </div>
            <br>

            <form action="AdminSort" method="POST" id="sortbox">                
                Show Sorted list by: 
                <select name="filtersort">
                    <option value="userID">userID</option>
                    <option value="fullname">fullname</option>
                    <option value="roleID">role</option>
                    <option value="gender">gender</option>
                    <option value="email">email</option>
                    <option value="mobile">mobile</option>              
                </select>
                <input type="submit" value="filter">
            </form>  

            <br>

            <br>
            <c:if test="${!empty requestScope.INVALID}">
                <button id="add" onclick="document.getElementById('add-box').style.display = 'block'" onfocus="autoClickAdd()" autofocus>Add new User</button>
            </c:if>
            <c:if test="${empty requestScope.INVALID}">
                <button onclick="document.getElementById('add-box').style.display = 'block'">Add new User</button>
            </c:if>
            <br>
            <br>

            <table border="1">
                <thead>
                    <tr>
                        <td>User ID</td>
                        <td>Role ID</td>
                        <td>Setting ID</td>
                        <td>Email</td>
                        <td>Gender</td>
                        <td>Full name</td>
                        <td>Phone</td>
                        <td>Hash</td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.USERSLIST}" var="dto">
                        <tr>
                            <td>${dto.userID}</td>
                            <td>${dto.roleID}</td>
                            <td>${dto.settingID}</td>
                            <td>${dto.email}</td>
                            <td>${dto.gender}</td>
                            <td>${dto.fullname}</td>
                            <td>${dto.phone}</td>
                            <td>${dto.hash}</td>                            
                            <!--                            <td><button form="view-user" onclick="document.getElementById('view').style.display = 'block'">view</button></td>-->
                            <td>
                                <c:url value="AdminView" var="AdminViewLink">
                                    <c:param name="id" value ="${dto.userID}"/>
                                </c:url>
                                <a href="${AdminViewLink}" onclick="document.getElementById('profile-box').style.display = 'block'">VIEW</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- PROFILE BOX -->
            <c:if test="${!empty requestScope.requestUSER}">
                <form  id="view" method="POST" class="modal-content animate">
                    <h1 class="modal-content animate" >User information</h1>
                    <table border="1" >
                        <tr>
                            <td>User ID</td>
                            <td>${requestScope.requestUSER.userID}</td>
                        </tr>
                        <tr>
                            <td>Role ID</td>
                            <td>${requestScope.requestUSER.roleID}</td> 
                        </tr>    
                        <tr> 
                            <td>Email</td>
                            <td>${requestScope.requestUSER.email}</td>
                        </tr>  
                        <tr>
                            <td>Fullname</td>
                            <td>${requestScope.requestUSER.fullname}</td> 
                        </tr>
                        <tr>
                            <td>Gender</td>
                            <td>${requestScope.requestUSER.gender}</td> 
                        </tr>
                        <tr>
                            <td>Phone</td>
                            <td>${requestScope.requestUSER.phone}</td>
                        </tr>
                        <tr>
                            <td>
                                <button form="x" class="login-btn" autofocus 
                                        onclick="document.getElementById('view').style.display = 'none'">Cancel</button>
                            </td>
                            <td>
                                <c:if test="${empty requestScope.INVALID}">
                                    <button form="x" class="login-btn"
                                            onclick="document.getElementById('view').style.display = 'none'
                                                    document.getElementById('change-box').style.display = 'block'"   >Change Info</button>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </form>
            </c:if>
            <!-- END PROFILE BOX -->

            <!-- CHANGE BOX -->            
            <div id="change-box" class="modal">
                <form method="POST" class="modal-content animate" action="AdminChange">
                    <h1 class="modal-content animate" >User information</h1>
                    <table border="1" >
                        <tr>
                            <td>User ID</td>
                            <td>${requestScope.requestUSER.userID}</td>                        
                        <input type="hidden" id="userID" name="userID" value="${requestScope.requestUSER.userID}">
                        </tr>
                        <tr>
                            <td>Role ID</td>
                            <td>
                                <select name="roleID">
                                    <option value="CUS">CUS</option>
                                    <option value="SAL">SAL</option>
                                    <option value="MKT">MKT</option>
                                    <option value="EXP">EXP</option>                                   
                                </select>
                            </td>
                        </tr>    
                        <tr> 
                            <td>Email</td>
                            <td>${requestScope.requestUSER.email}</td>
                        </tr>  
                        <tr>
                            <td>Fullname</td>
                            <td>${requestScope.requestUSER.fullname}</td> 
                        </tr>
                        <tr>
                            <td>Gender</td>
                            <td>${requestScope.requestUSER.gender}</td> 
                        </tr>
                        <tr>
                            <td>Phone</td>
                            <td>${requestScope.requestUSER.phone}</td>
                        </tr>
                        <tr>
                            <td>
                                <button form="x" class="login-btn" autofocus 
                                        onclick="document.getElementById('change-box').style.display = 'none'">Cancel</button>
                            </td>
                            <td>
                                <c:if test="${empty requestScope.INVALID}">
                                    <button class="login-btn"
                                            document.getElementById('change-box').style.display = 'none'"   >Change</button>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <!-- END CHANGE BOX -->

            <!-- ADD BOX -->

            <div id="add-box" class="modal">
                <form action="AdminAdd" method="POST" class="modal-content animate">
                    <table>
                        <tr>
                            <td> role ID:</td>
                            <td>
                                <select name="roleID">
                                    <option value="CUS">CUS</option>
                                    <option value="SAL">SAL</option>
                                    <option value="MKT">MKT</option>
                                    <option value="EXP">EXP</option>                                   
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td> email:</td>
                            <td><input type="email" name="email" value="${param.email}"/>
                                <font color="red">${requestScope.INVALID.emailError}</font>
                            </td>                            
                        </tr>
                        <tr>
                            <td> password:</td>
                            <td><input type="password" name="password"/>
                                <font color="red">${requestScope.INVALID.passwordError}</font>
                            </td>
                        </tr>
                        <tr>
                            <td> re-enter password:</td>
                            <td><input type="password" name="re_password"/>
                                <font color="red">${requestScope.INVALID.re_passwordError}</font>
                            </td>
                        </tr>
                        <tr>
                            <td> fullname:</td>
                            <td><input type="text" name="fullname" value="${param.fullname}"/>
                                <font color="red">${requestScope.INVALID.fullnameError}</font>
                            </td>
                        </tr>
                        <tr>
                            <td> gender:</td>
                            <td> 
                                <select name="gender">
                                    <option value="M">M</option>
                                    <option value="F">F</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td> phone:</td>
                            <td><input type="number" name="phone" value="${param.phone}"/>
                                <font color="red">${requestScope.INVALID.phoneError}</font>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><input class="login-btn" type="submit" value="Add"></td>
                            <td>                        
                                <button class="login-btn" type="reset" onclick="document.getElementById('add-box').style.display = 'none'">Cancel</button></td>
                        </tr>
                    </table>
                </form>         
            </div>
            <!-- END ADD BOX -->

        </form>
    </c:if>
    <c:if test="${empty requestScope.USERSLIST}">
        <h2>There are none Users like that in the system!</h2>            

        <c:url value="AdminUserList" var="AdminUserListLink"/>
        <a href="${AdminUserListLink}">Back to the User List page</a>
    </c:if>       
    <br>
    <c:if test="${!empty requestScope.check}">
        <div class="pagination">
            <!-- For displaying Previous link except for the 1st page -->
            <c:if test="${currentPage != 1}">
                <c:url value="AdminUserList" var="AdminUserListLink">
                    <c:param name="page" value ="${currentPage - 1}"/>
                </c:url>
                <a href="${AdminUserListLink}">Previous page</a>
            </c:if>

            <!-- For displaying Page numbers. The when condition does not display a link for the current page -->

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <a class="activepage">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <c:url value="AdminUserList" var="AdminUserListLink">
                            <c:param name="page" value ="${i}"/>
                        </c:url>
                        <a href="${AdminUserListLink}"> ${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <!-- For displaying Next link -->
            <c:if test="${currentPage lt noOfPages}">
                <c:url value="AdminUserList" var="AdminUserListLink">
                    <c:param name="page" value ="${currentPage + 1}"/>
                </c:url>
                <a href="${AdminUserListLink}">Next page</a>
            </c:if>
        </div>
    </c:if>
    <a href="adminview.jsp"> back to the main page</a>
</body>
</html>
