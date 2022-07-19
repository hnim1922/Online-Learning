<%-- 
    Document   : adminlessonlist
    Created on : Jun 29, 2021, 8:47:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
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
    <body>
        <h1>LESSON LIST</h1>

        <c:if test="${!empty requestScope.LESSONSLIST}">

            <form action="AdminSearchLesson" method="POST">
                Search Lesson:  <input type="text" name="input"/>
                <input type="submit" value="search">
            </form>
            <br>
            <div class="filterbox">
                <form action="AdminFilterLesson" method="POST">                
                    Filter by: 
                    <select name="filter" id="filter">
                        <option value="all">All subject</option>
                        <c:forEach items="${requestScope.LISTofSUBJECTS}" var="sub">
                            <option value="${sub.subjectID}">${sub.subjectID} - ${sub.name} </option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="FILTER">
                </form> 
            </div>
            <br>
            <c:if test="${!empty requestScope.INVALID}">
                <button id="add" onclick="document.getElementById('add-box').style.display = 'block'" onfocus="autoClickAdd()" autofocus>Add new Lesson</button>
            </c:if>
            <c:if test="${empty requestScope.INVALID}">
                <button onclick="document.getElementById('add-box').style.display = 'block'">Add new Lesson</button>
            </c:if>
            <br>
            <br>
            <form action="AdminLessonList" method="POST">
                <input type="text" name="all" hidden value="all"/>
                <input type="submit" value="Show all lesson">
            </form>
            <br>

            <br>
            <table border="1">
                <thead>
                    <tr>
                        <td>Lesson ID</td>
                        <td>Name</td>
                        <td>Content</td>
                        <td>Subject</td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.LESSONSLIST}" var="dto">
                        <tr>

                            <td>${dto.lessonID}</td>
                            <td>${dto.lessonName}</td>
                            <td>${dto.content}</td>
                            <td>ID: ${dto.subject.subjectID} - ${dto.subject.name}</td>
                            <td>
                                <c:url value="AdminViewLesson" var="AdminViewLessonLink">
                                    <c:param name="lid" value ="${dto.lessonID}"/>
                                </c:url>
                                <a href="${AdminViewLessonLink}" onclick="document.getElementById('profile-box').style.display = 'block'">VIEW DETAILS</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty requestScope.LESSONSLIST}">
            <h2>There are none Lessons like that in the system!</h2>   
            <c:url value="AdminLessonList" var="AdminLessonListLink"/>
            <a href="${AdminLessonListLink}">Back to the Lesson List page</a>
        </c:if>

        <!-- ADD LESSON BOX -->
        <div id="add-box" class="modal">
            <form action="AdminAddLesson" method="POST" class="modal-content animate">
                <table>
                    <tr>
                        <td> Lesson Name:</td>
                        <td><input type="text" name="name" value="${param.name}"/>
                            <font color="red">${requestScope.INVALID.nameError}</font>
                        </td>                            
                    </tr>
                    <tr>
                        <td> Subject: </td>
                        <td>
                            <select name="subject" id="filter">
                                <c:forEach items="${requestScope.LISTofSUBJECTS}" var="sub">
                                    <option value="${sub.subjectID}">${sub.subjectID} - ${sub.name} </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> Lesson Content:</td>
                        <td>
                            <textarea name="content" value="${param.content}" rows="5" cols="40"></textarea>
                            <font color="red">${requestScope.INVALID.contentError}</font>
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
        <!-- END ADD LESSON BOX -->

        <br>

        <c:if test="${!empty requestScope.check}">
            <div class="pagination">
                <!-- For displaying Previous link except for the 1st page -->
                <c:if test="${currentPage != 1}">
                    <c:url value="AdminLessonList" var="AdminLessonListLink">
                        <c:param name="page" value ="${currentPage - 1}"/>
                    </c:url>
                    <a href="${AdminLessonListLink}">Previous page</a>
                </c:if>

                <!-- For displaying Page numbers. The when condition does not display a link for the current page -->

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <a class="activepage">${i}</a>
                        </c:when>
                        <c:otherwise>
                            <c:url value="AdminLessonList" var="AdminLessonListLink">
                                <c:param name="page" value ="${i}"/>
                            </c:url>
                            <a href="${AdminLessonListLink}"> ${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <!-- For displaying Next link -->
                <c:if test="${currentPage lt noOfPages}">
                    <c:url value="AdminLessonList" var="AdminLessonListLink">
                        <c:param name="page" value ="${currentPage + 1}"/>
                    </c:url>
                    <a href="${AdminLessonListLink}">Next page</a>
                </c:if>
            </div>
        </c:if>
        <a href="adminview.jsp"> back to the main page</a>
    </body>
</html>
