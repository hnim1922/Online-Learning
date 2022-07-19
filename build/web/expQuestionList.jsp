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
        <link rel="stylesheet" href="style.css">

        <script>
            function autoClickAdd() {
                document.getElementById("add").click();
            }

            function filterSubject() {
                var dimensions = document.getElementsByClassName('filterDimensionTH');
                var lessons = document.getElementsByClassName('filterLessonTH');
                var subjects = document.getElementsByClassName('filterSubjectTH');
                for (var i = 0; i < dimensions.length; i++) {
                    //                    dimensions[i].style.visibility = "hidden";
                    dimensions[i].style.display = "none";
                }

                for (var i = 0; i < lessons.length; i++) {
                    //                    lessons[i].style.visibility = "hidden";
                    lessons[i].style.display = "none";
                }

                for (var i = 0; i < subjects.length; i++) {
                    //                    subjects[i].style.visibility = "visible";
                    subjects[i].style.display = "block";
                }
            }

            function filterDimension() {
                var dimensions = document.getElementsByClassName('filterDimensionTH');
                var lessons = document.getElementsByClassName('filterLessonTH');
                var subjects = document.getElementsByClassName('filterSubjectTH');
                for (var i = 0; i < subjects.length; i++) {
                    //                    subjects[i].style.visibility = "hidden";
                    subjects[i].style.display = "none";
                }

                for (var i = 0; i < lessons.length; i++) {
                    //                    lessons[i].style.visibility = "hidden";
                    lessons[i].style.display = "none";
                }

                for (var i = 0; i < dimensions.length; i++) {
                    //                    dimensions[i].style.visibility = "visible";
                    dimensions[i].style.display = "block";
                }
            }

            function filterLesson() {
                var dimensions = document.getElementsByClassName('filterDimensionTH');
                var lessons = document.getElementsByClassName('filterLessonTH');
                var subjects = document.getElementsByClassName('filterSubjectTH');
                for (var i = 0; i < dimensions.length; i++) {
                    //                    dimensions[i].style.visibility = "hidden";
                    dimensions[i].style.display = "none";
                }

                for (var i = 0; i < subjects.length; i++) {
                    //                    subjects[i].style.visibility = "hidden";
                    subjects[i].style.display = "none";
                }

                for (var i = 0; i < lessons.length; i++) {
                    //                    lessons[i].style.visibility = "visible";
                    lessons[i].style.display = "block";
                }
            }
        </script>
    </head>

    <body>
        <h1>QUESTION LIST</h1>
        <c:if test="${!empty sessionScope.questionList}">

            <form action="SearchQuestion?index=1" method="POST">
                Search question: <input type="text" name="txtSearch" />
                <input type="submit" value="search">
            </form>
            <br>
            <div class="filterbox">
                Filter by:
                <button onclick="filterSubject()">Subject</button>
                <button onclick="filterDimension()">Dimension</button>
                <button onclick="filterLesson()">Lesson</button>
            </div>
            <br>
            <c:if test="${!empty requestScope.INVALID}">
                <button id="add" onclick="document.getElementById('add-box').style.display = 'block'" onfocus="autoClickAdd()" autofocus>Add new question</button>
            </c:if>
            <c:if test="${empty requestScope.INVALID}">
                <button onclick="document.getElementById('add-box').style.display = 'block'">Add new Subject</button>
            </c:if>
            <br>
            <br>
            <button onclick="location.href = 'QuestionList?index=1'">Show all subject</button>
            <br>

            <br>
            <table border="1">
                <thead>
                    <tr>
                        <td>Question ID</td>
                        <td>Quiz ID</td>
                        <td>Content</td>
                        <td>Level</td>
                        <td class="filterLessonTH">Lesson Name</td>
                        <td class="filterSubjectTH">Subject Name</td>
                        <td class="filterDimensionTH">Dimension Name</td>
                        <td></td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.questionList}" var="dto">
                        <tr>

                            <td>${dto.questionID}</td>
                            <td>${dto.quizID}</td>
                            <td>${dto.content}</td>
                            <td>${dto.level}</td>
                            <td class="filterLessonTH">${dto.lessonName}</td>
                            <td class="filterSubjectTH">${dto.subjectname}</td>
                            <td class="filterDimensionTH">${dto.dimensionName}</td>
                            <td>
                                <a href="QuestionDetails?questionID=${dto.questionID}">View Details</a>
                            </td>
                            <td>
                                <a onclick="return confirm('Are you sure you want to delete?')" href="DeleteQuestion?questionID=${dto.questionID}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty sessionScope.questionList}">
            <h2>There are none Subjects like that in the system!</h2>
            <c:url value="AdminSubjectList" var="AdminSubjectListLink" />
            <a href="QuestionList?index=1">Back to the Question List page</a>
        </c:if>

        <!-- ADD SUBJECT BOX -->
        <div id="add-box" class="modal">
            <form action="AddQuestion" method="POST" class="modal-content animate">
                <table>
                    <tr>
                        <td> Content:</td>
                        <td><input type="text" name="content" required/>
                        </td>
                    </tr>
                    <tr>
                        <td> QuizID: </td>
                        <td>
                            <select name="quizID">
                                <c:forEach items="${sessionScope.quizList}" var="quiz">
                                    <option value="${quiz.quizID}">${quiz.quizID}</option>
                                </c:forEach>                                
                            </select>
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
        <div class="pagination" style="margin-top: 10px">
            <c:forEach begin="1" end="${sessionScope.endPage}" var="i">
                <a <c:if test="${param.index == i}">class="active"</c:if> href="QuestionList?index=${i}">${i}</a>
            </c:forEach>
        </div>
        <a href="expview.jsp"> back to the main page</a>
    </body>

</html>