<%-- 
    Document   : adminsublist
    Created on : Jun 17, 2021, 5:18:34 PM
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
        <h1>SUBJECT LIST</h1>
        <c:if test="${!empty requestScope.SUBJECTSLIST}">

            <form action="AdminSearchSubject" method="POST">
                Search subject:  <input type="text" name="input"/>
                <input type="submit" value="search">
            </form>
            <br>
            <div class="filterbox">
                <form action="AdminFilterSubject" method="POST">                
                    Filter by: 
                    <select name="status" id="filterstatus">
                        <option value="all">All status</option>
                        <option value="true">Published</option>
                        <option value="false">Unpublished</option>
                    </select>
                    <select name="category" id="filtercategory">
                        <option value="all">All Category</option>
                        <c:forEach items="${requestScope.CATESLIST}" var="cate">
                            <option value="${cate.categoryID}">${cate.categoryID} - ${cate.categoryName}</option>
                        </c:forEach>                                  
                    </select>
                    <input type="submit" value="filter">
                </form> 
            </div>
            <br>
            <c:if test="${!empty requestScope.INVALID}">
                <button id="add" onclick="document.getElementById('add-box').style.display = 'block'" onfocus="autoClickAdd()" autofocus>Add new Subject</button>
            </c:if>
            <c:if test="${empty requestScope.INVALID}">
                <button onclick="document.getElementById('add-box').style.display = 'block'">Add new Subject</button>
            </c:if>
            <br>
            <br>
            <form action="AdminSubjectList" method="POST">
                <input type="text" name="all" hidden value="all"/>
                <input type="submit" value="Show all subjects">
            </form>
            <br>

            <br>
            <table border="1">
                <thead>
                    <tr>
                        <td>ID</td>
                        <td>Name</td>
                        <td>Number of Lessons</td>
                        <td>Category</td>
                        <td>Status</td>
                        <td>Owner</td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.SUBJECTSLIST}" var="dto">
                        <tr>
                            
                            <td>${dto.subjectID}</td>
                            <td>${dto.name}</td>
                            <td>${dto.totalLessons}</td>
                            <td>${dto.category.categoryName}</td>
                            <td>publish: ${dto.status}</td>
                            <td> UID: ${dto.user.userID} - ${dto.user.fullname}</td>
                            <td>
                                <c:url value="AdminViewSubject" var="AdminViewSubjectLink">
                                    <c:param name="id" value ="${dto.subjectID}"/>
                                </c:url>
                                <a href="${AdminViewSubjectLink}" onclick="document.getElementById('profile-box').style.display = 'block'">VIEW DETAILS</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty requestScope.SUBJECTSLIST}">
            <h2>There are none Subjects like that in the system!</h2>   
            <c:url value="AdminSubjectList" var="AdminSubjectListLink"/>
            <a href="${AdminSubjectListLink}">Back to the Subject List page</a>
        </c:if>

        <!-- ADD SUBJECT BOX -->
        <div id="add-box" class="modal">
            <form action="AdminAddSubject" method="POST" class="modal-content animate">
                <table>
                    <tr>
                        <td> Name:</td>
                        <td><input type="text" name="name" value="${param.name}"/>
                            <font color="red">${requestScope.INVALID.nameError}</font>
                        </td>                            
                    </tr>
                    <tr>
                        <td> Owner: </td>
                        <td>
                            <select name="owner">
                                <c:forEach items="${requestScope.EXPSLIST}" var="exp">
                                    <option value="${exp.userID}">${exp.userID} - ${exp.fullname}</option>
                                </c:forEach>                                
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> Thumbnail:</td>
                        <td><input type="text" name="thumbnail" value="${param.thumbnail}"/>
                            <font color="red">${requestScope.INVALID.thumbnailimageError}</font>
                        </td>
                    </tr>
                    <tr>
                        <td> Category: </td>
                        <td>
                            <select name="category">
                                <c:forEach items="${requestScope.CATESLIST}" var="cate">
                                    <option value="${cate.categoryID}">${cate.categoryID} - ${cate.categoryName}</option>
                                </c:forEach>                                  
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> Status:</td>
                        <td> 
                            <select name="status">
                                <option value="true">Published</option>
                                <option value="false">Unpublished</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> Feature flag:</td>
                        <td> 
                            <select name="featureflag">
                                <option value="true">Feature flag : true</option>
                                <option value="false">Feature flag : false</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> Description:</td>
                        <td>
                            <textarea name="information" value="${param.information}" rows="5" cols="40"></textarea>
                            <font color="red">${requestScope.INVALID.informationError}</font>
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
        <!-- END ADD SUBJECT BOX -->

        <c:if test="${!empty requestScope.check}">
            <div class="pagination">
                <!-- For displaying Previous link except for the 1st page -->
                <c:if test="${currentPage != 1}">
                    <c:url value="AdminSubjectList" var="AdminSubjectListLink">
                        <c:param name="page" value ="${currentPage - 1}"/>
                    </c:url>
                    <a href="${AdminSubjectListLink}">Previous page</a>
                </c:if>

                <!-- For displaying Page numbers. The when condition does not display a link for the current page -->

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <a class="activepage">${i}</a>
                        </c:when>
                        <c:otherwise>
                            <c:url value="AdminSubjectList" var="AdminSubjectListLink">
                                <c:param name="page" value ="${i}"/>
                            </c:url>
                            <a href="${AdminSubjectListLink}"> ${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <!-- For displaying Next link -->
                <c:if test="${currentPage lt noOfPages}">
                    <c:url value="AdminSubjectList" var="AdminSubjectListLink">
                        <c:param name="page" value ="${currentPage + 1}"/>
                    </c:url>
                    <a href="${AdminSubjectListLink}">Next page</a>
                </c:if>
            </div>
        </c:if>
        <a href="adminview.jsp"> back to the main page</a>
    </body>
</html>
