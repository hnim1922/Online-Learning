<%-- 
    Document   : adminlessonview
    Created on : Jul 1, 2021, 4:57:08 PM
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

            button {
                display: block;
                color: black;
                padding: 14px 28px;
                font-size: 16px;
                cursor: pointer;
                text-align: center;
            }

            button:hover {
                background-color: #ddd;
                color: black;
            }

            table{
                margin-left: auto; 
                margin-right: auto;
                font-size: 20px;
            }

            input{
                font-size: 20px;
            }
            textarea{
                font-size: 20px;
            }
            select{
                font-size: 20px;
            }

            .headline{
                margin-left: auto; 
                margin-right: auto;
                color: black;
                font-size: 50px;
                font-weight: 900;
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
        <form id="change" action="AdminChangeLesson" method="POST">
            <input hidden type="text" name="lid" value="${requestScope.REQUESTEDlesson.lessonID}">
            <table>
                <tr>
                    <td></td>
                    <td class="headline">Lesson Details</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Lesson ID : </td>
                    <td><input type="text" name="lsid" value="${requestScope.REQUESTEDlesson.lessonID}" readonly></td>
                </tr>
                <tr>
                    <td>Lesson Name : </td>
                    <td><input name="lname" type="text" value="${requestScope.REQUESTEDlesson.lessonName}">
                        <font color="red">${requestScope.INVALID.nameError}</font>
                    </td>
                </tr>
                <tr>
                    <td>Lesson content : </td>
                    <td><textarea name="lcontent" rows="5" cols="30">${requestScope.REQUESTEDlesson.content}</textarea>
                        <font color="red">${requestScope.INVALID.contentError}</font>
                    </td>
                </tr>
                <tr>
                    <td> Subject: </td>
                    <td>
                        <select name="lsubject" id="filter">
                            <c:forEach items="${requestScope.LISTofSUBJECTS}" var="sub">
                                <c:if test="${sub.subjectID eq requestScope.REQUESTEDlesson.subject.subjectID}">
                                    <option value="${sub.subjectID}" selected>${sub.subjectID} - ${sub.name} </option>  
                                </c:if>
                                <c:if test="${sub.subjectID ne requestScope.REQUESTEDlesson.subject.subjectID}">
                                    <option value="${sub.subjectID}">${sub.subjectID} - ${sub.name} </option>  
                                </c:if>`
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td><button form="change">CHANGE</button></td>
                    <td></td>
                    <td><button form="back">BACK</button></td>
                </tr>
            </table>
        </form>
        <form id="back" action="AdminLessonList" method="POST">
            <input hidden type="text" name="lid" value="${requestScope.REQUESTEDlesson.lessonID}">
        </form>

    </body>
</html>
