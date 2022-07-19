<%-- 
    Document   : adminsubview
    Created on : Jun 18, 2021, 9:51:51 PM
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

            .tab {
                overflow: hidden;
                border: 1px solid #ccc;
                background-color: #f1f1f1;
            }

            .modal_fix {
                display: none;
                position: fixed;
                z-index: 1;                
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;               
                background-color: rgb(0, 0, 0);                
                background-color: rgba(0, 0, 0, 0.4);
                padding-top: 60px;
            }

            .modal_fix table{
                padding: 20px;
            }
            /* Style the buttons inside the tab */
            .tab button {
                background-color: inherit;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 14px 16px;
                transition: 0.3s;
                font-size: 17px;
            }

            /* Change background color of buttons on hover */
            .tab button:hover {
                background-color: #ddd;
            }

            /* Create an active/current tablink class */
            .tab button.active {
                background-color: #ccc;
            }

            /* Style the tab content */
            .tabcontent {
                display: none;
                padding: 6px 12px;
                border: 1px solid #ccc;
                border-top: none;
            }

            /* Style the close button */
            .topright {
                float: right;
                cursor: pointer;
                font-size: 28px;
            }

            .topright:hover {color: red;}

            .button {
                font: bold 15px Arial;
                text-decoration: none;
                background-color: #EEEEEE;
                color: #333333;
                padding: 2px 6px 2px 6px;
                border-top: 1px solid #CCCCCC;
                border-right: 1px solid #333333;
                border-bottom: 1px solid #333333;
                border-left: 1px solid #CCCCCC;
            }
        </style>
        <link rel="stylesheet" href="style.css">

        <script>
            function openTab(evt, cityName) {
                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("tabcontent");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablinks");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" active", "");
                }
                document.getElementById(cityName).style.display = "block";
                evt.currentTarget.className += " active";
            }

            function autoClickdefaultOpen() {
                document.getElementById("defaultOpen").click();
            }
            function autoClickChange() {
                document.getElementById("change").click();
            }
            function autoClickAddPrice() {
                document.getElementById("pricebox").click();
            }
            function autoClickAddDimen() {
                document.getElementById("dimenbox").click();
            }
            function confirmDelete() {
                var r = confirm("Are you sure you want to delete this?")
                return r;
            }
        </script>
    </head>
    <body>
        <h1>Question Details</h1>
        <br>
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'Overview')" >Overview</button>
            <button class="tablinks" onclick="openTab(event, 'Price')">Answers</button>
        </div>

        <br>
        <!-- OVERVIEW BOX -->
        <div id="Overview" class="tabcontent">
            <span onclick="this.parentElement.style.display = 'none'" class="topright">&times</span>
            <c:if test="${!empty sessionScope.question}">
                <form  id="view" method="POST" >
                    <table>
                        <tr>
                            <td>Question ID:</td>
                            <td>${sessionScope.question.questionID}</td>
                        </tr>  
                        <tr> 
                            <td>Quiz ID </td>
                            <td>${sessionScope.question.quizID}</td>
                        </tr>  
                        <tr>
                            <td>Level:</td>
                            <td>${sessionScope.question.level}</td> 
                        </tr>
                        <tr>
                            <td>Lesson Name:</td>
                            <td>${sessionScope.question.lessonName}</td> 
                        </tr>
                        <tr>
                            <td>Subject Name:</td>
                            <td>${sessionScope.question.subjectname}</td> 
                        </tr>
                        <tr>
                            <td>Dimension Name:</td>
                            <td>${sessionScope.question.dimensionName}</td> 
                        </tr>
                        <tr>
                            <td>Content: </td>
                            <td>
                                <textarea readonly style="width: 250px; height: 100px">${sessionScope.question.content}</textarea> 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button form="x" class="login-btn" onfocus="autoClickChange()" id="change" autofocus
                                        onclick="document.getElementById('change-box').style.display = 'block'">Change</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </c:if>
            <c:if test="${empty sessionScope.question}">
                <h2>There is no information here!</h2>
            </c:if>
        </div>

        <div id="change-box" class="modal_fix">
            <form class="modal-content animate" action="EditQuestion">
                <h1 class="modal-content animate" >Edit Question</h1>    
                <input type="hidden" name="questionID" value="${sessionScope.question.questionID}"/>
                <table>
                    <tr>
                        <td>Content: </td>
                        <td>
                            <textarea required name="content" style="width: 250px; height: 100px">${sessionScope.question.content}</textarea> 
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><input class="login-btn" type="submit" value="submit"></td>
                        <td>                        
                            <button class="login-btn" type="reset" onclick="document.getElementById('change-box').style.display = 'none'">Cancel</button></td>
                    </tr>
                </table>
            </form>
        </div>
        <!-- END OVERVIEW BOX -->

        <!-- PRICE PACKAGE -->
        <div id="Price" class="tabcontent">
            <span onclick="this.parentElement.style.display = 'none'" class="topright">&times</span>
            <span>
                <button form="x" class="login-btn"
                        onclick="document.getElementById('add-pricebox').style.display = 'block'">Add new</button>
            </span>
            <br>
            <c:if test="${!empty sessionScope.answerList}">
                <table border="1">
                    <thead>
                        <tr>
                            <td>Answer ID</td>
                            <td>Question ID</td>
                            <td>Result</td>
                            <td>Content</td>
                            <td></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionScope.answerList}" var="dto">
                            <tr>
                                <td>${dto.answerID}</td>
                                <td>${dto.questionID}</td>
                                <c:if test="${dto.result == 0}">
                                    <td></td>
                                </c:if>
                                <c:if test="${dto.result == 1}">
                                    <td>Correct Answer</td>
                                </c:if>
                                <td>${dto.content}</td>
                                <td> 
                                    <button onclick="document.getElementById('edit-answer').style.display = 'block'; document.getElementById('answerID').value = '${dto.answerID}';
                                            ">edit</button>
                                </td>
                                <td>
                                    <a href="DeleteAnswer?answerID=${dto.answerID}" class="button" onclick="return confirm('Are you sure you want to delete?')">delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty sessionScope.answerList}">
                <h2>There is no information here!</h2>
            </c:if>
        </div>

        <div id="add-pricebox" class="modal_fix">
            <form action="AddNewAnswer" method="POST" class="modal-content animate">
                <input type="hidden" name="questionID" value="${sessionScope.question.questionID}"/>
                <table>
                    <tr>
                        <td> Content:</td>
                        <td>
                            <textarea required name="answerContent"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td> Result: </td>
                        <td>
                            <select name="result">
                                <option value="0">Not correct answer</option>
                                <option value="1">Correct answer</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><input class="login-btn" type="submit" value="Add"></td>
                        <td>                        
                            <button class="login-btn" type="reset" onclick="document.getElementById('add-pricebox').style.display = 'none'">Cancel</button></td>
                    </tr>
                </table>
            </form>         
        </div>


        <div id="edit-answer" class="modal_fix">
            <form action="EditAnswer" method="POST" class="modal-content animate">
                <input id="answerID" type="hidden" name="answerID"/>
                <input id="answerID" type="hidden" name="questionID" value="${sessionScope.question.questionID}"/>
                <table>
                    <tr>
                        <td> Content:</td>
                        <td>
                            <textarea name="answeringContent"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td> Status:</td>
                        <td> 
                            <select name="answerResult">
                                <option value="0">Not correct Answer</option>
                                <option value="1">Correct Answer</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><input class="login-btn" type="submit" value="Change"></td>
                        <td>                        
                            <button class="login-btn" type="reset" onclick="document.getElementById('edit-answer').style.display = 'none'">Cancel</button></td>
                    </tr>
                </table>
            </form>         
        </div>
        <!-- END PRICE PACKAGE -->

        <!-- SUBJECT DIMENSION -->
        <div id="Dimension" class="tabcontent">
            <span onclick="this.parentElement.style.display = 'none'" class="topright">&times</span>
            <span>
                <c:if test="${empty requestScope.INVALIDDIMEN}">
                    <button form="x" class="login-btn"
                            onclick="document.getElementById('add-dimenbox').style.display = 'block'">Add new</button>
                </c:if>
                <c:if test="${!empty requestScope.INVALIDDIMEN}">
                    <button form="x" onfocus="autoClickAddDimen()" id="dimenbox" autofocus class="login-btn"
                            onclick="document.getElementById('add-dimenbox').style.display = 'block'">Add new</button>
                </c:if>
            </span>
            <br>
            <c:if test="${!empty requestScope.DIMENLIST}">
                <table border="1">
                    <thead>
                        <tr>
                            <td>Dimension ID</td>
                            <td>Name</td>
                            <td>Type</td>
                            <td>Description</td>
                            <td></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.DIMENLIST}" var="dto">
                            <tr>
                                <td>${dto.dimensionID}</td>
                                <td>${dto.name}</td>
                                <td>${dto.type}</td>
                                <td>${dto.description}</td>
                                <td> 
                                    <c:url value="AdminViewDimen" var="AdminViewDimenLink">
                                        <c:param name="dimenid" value ="${dto.dimensionID}"/>
                                        <c:param name="id" value ="${requestScope.SUBJECT.subjectID}"/>
                                    </c:url>
                                    <a href="${AdminViewDimenLink}" class="button">edit</a>
                                </td>
                                <td>
                                    <c:url value="AdminDeleteDimen" var="AdminDeleteDimenLink">
                                        <c:param name="dimenid" value ="${dto.dimensionID}"/>
                                        <c:param name="id" value ="${requestScope.SUBJECT.subjectID}"/>
                                    </c:url>
                                    <a href="${AdminDeleteDimenLink}" onclick="return confirmDelete()" class="button">delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty requestScope.DIMENLIST}">
                <h2>There is none information here!</h2>
            </c:if>
        </div>

        <div id="add-dimenbox" class="modal_fix">
            <form action="AdminAddDimension" method="POST" class="modal-content animate">
                <input type="hidden" name="id" value="${requestScope.SUBJECT.subjectID}"/>
                <table>
                    <tr>
                        <td> Name:</td>
                        <td><input type="text" name="dimenname" value="${param.dimenname}"/>
                            <font color="red">${requestScope.INVALIDDIMEN.nameError}</font>
                        </td>                            
                    </tr>
                    <tr>
                        <td> Type: </td>
                        <td><input type="text" name="type" value="${param.type}"/>
                            <font color="red">${requestScope.INVALIDDIMEN.typeError}</font> 
                        </td>
                    </tr>
                    <tr>
                        <td> Description:</td>
                        <td><input type="text" name="desc" value="${param.desc}"/>
                            <font color="red">${requestScope.INVALIDDIMEN.descriptionError}</font>
                        </td>                            
                    </tr>
                    <tr>
                        <td colspan="2"><input class="login-btn" type="submit" value="Add"></td>
                        <td>                        
                            <button class="login-btn" type="reset" onclick="document.getElementById('add-dimenbox').style.display = 'none'">Cancel</button></td>
                    </tr>
                </table>
            </form>         
        </div>

        <c:if test="${!empty requestScope.requestDIMEN}">
            <div id="change-dimenbox">
                <form action="AdminChangeDimension" method="POST" class="modal-content animate">
                    <input type="hidden" name="id" value="${requestScope.SUBJECT.subjectID}"/>
                    <input type="hidden" name="dimenid" value="${requestScope.requestDIMEN.dimensionID}"/>
                    <table>
                        <tr>
                            <td> Name:</td>
                            <td><input type="text" name="updatedimenname" value="${requestDIMEN.name}"/>
                                <font color="red">${requestScope.INVALIDUPDATEDIMEN.nameError}</font>
                            </td>                            
                        </tr>
                        <tr>
                            <td> Type: </td>
                            <td><input type="text" name="updatedimentype" value="${requestDIMEN.type}"/>
                                <font color="red">${requestScope.INVALIDUPDATEDIMEN.typeError}</font> 
                            </td>
                        </tr>
                        <tr>
                            <td> Description:</td>
                            <td><input type="text" name="updatedimendesc" value="${requestDIMEN.description}"/>
                                <font color="red">${requestScope.INVALIDUPDATEDIMEN.descriptionError}</font>
                            </td>                            
                        </tr>
                        <tr>
                            <td colspan="2"><input class="login-btn" type="submit" value="Change"></td>
                            <td>                        
                                <button class="login-btn" type="reset" onclick="document.getElementById('change-dimenbox').style.display = 'none'">Cancel</button></td>
                        </tr>
                    </table>
                </form>         
            </div>
        </c:if>
        <!-- END SUBJECT DIMENSION -->


        <br>

        <div>
            <c:url value="QuestionList?index=1" var="AdminSubjectListLink"/>
            <a href="${AdminSubjectListLink}">Back to the Question List page</a> 
        </div>

    </body>
</html>
