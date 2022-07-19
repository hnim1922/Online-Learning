<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset='utf-8'>
        <meta http-equiv='X-UA-Compatible' content='IE=edge'>
        <title>Quiz</title>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <link rel='stylesheet' type='text/css' media='screen' href='quizreview.css'>

    </head>

    <body>
        <header>

        </script>
        <h1>Quiz ${requestScope.quizID}</h1>
    </header>
    <div class="review-progress">
        <h2>Review Progress</h2>
        <div class="review-card">
            <c:forEach var="question" items="${questionList}">
                <c:choose>
                    <c:when test="${question.value[0]!=null}">
                        <div id="question-${question.key.questionID}" class="review-box" style="background: green;">
                            <p>${question.key.questionID}</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div id="question-${question.key.questionID}" class="review-box">
                            <p>${question.key.questionID}</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>
    <div class="question-infomation">
        <input type="hidden" value="${radioList}"> 
        <c:set var="highscore"  value="0" scope="page"/>
        <c:forEach var="question" items="${questionList}">
            <div class="question-card">
                <p>${question.key.content}</p>
                <c:choose>
                    <c:when test="${question.value[0]!=null}">
                        <input type="radio" value="${question.value[0]}" checked="checked">${question.value[0]} <br>
                        <c:forEach var="answerList" items="${answerList}">
                            <c:if test="${answerList.questionID==question.key.questionID}">
                                <c:if test="${question.value[0]!=answerList.content}">
                                    <input type="radio" value="${answerList.content}" disabled="disabled">${answerList.content}</p>
                                </c:if>
                            </c:if>
                        </c:forEach>
                        <c:forEach var="correctList" items="${correctList}">       
                            <c:if test="${correctList.questionID==question.key.questionID}">
                                <c:choose>
                                    <c:when test="${question.value[0]==correctList.content}">
                                        <div class="correct-answer" > This is correct </div>
                                        <button id="myBtn" class="popup"
                                                onclick="document.getElementById('${question.key.questionID}').style.display = 'block'">
                                            Peak At Answer </button>
                                        <div id="${question.key.questionID}" class="modal">
                                            <c:if test="${correctList.questionID==question.key.questionID}">
                                                <div class="modal-content">
                                                    <span class="close"
                                                          onclick="document.getElementById('${question.key.questionID}').style.display = 'none'">&times;</span>
                                                    <p> The correct answer is ${correctList.content} </p>
                                                </div>
                                            </c:if>
                                        </div>
                                        <c:set var="highscore" value="${highscore + 1 }" scope="page"/>
                                    </c:when>
                                    <c:when test="${question.value[0] != correctList.content}">
                                        <div class="incorrect-confirm"> This is not correct </div>
                                        <button id="myBtn" class="popup"
                                                onclick="document.getElementById('${question.key.questionID}').style.display = 'block'">
                                            Peak At Answer </button>
                                        <div id="${question.key.questionID}" class="modal">
                                            <c:if test="${correctList.questionID==question.key.questionID}">
                                                <div class="modal-content">
                                                    <span class="close"
                                                          onclick="document.getElementById('${question.key.questionID}').style.display = 'none'">&times;</span>
                                                    <p> The correct answer is ${correctList.content} </p>
                                                </div>
                                            </c:if>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </c:if>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="answerList" items="${answerList}">
                            <c:if test="${answerList.questionID==question.key.questionID}"> 
                                <input type="radio" id="ans-${answerList.answerID}" name="${question.key.questionID}"disabled="disabled">
                                <label for="ans-${answerList.answerID}">${answerList.content}</label><br>
                            </c:if>
                        </c:forEach>
                        <p> You did not answer </p>
                        <button id="myBtn" class="popup"
                                onclick="document.getElementById('${question.key.questionID}').style.display = 'block'">
                            Peak At Answer </button>
                        <div id="${question.key.questionID}" class="modal">
                            <c:forEach var="correctList" items="${correctList}">
                                <c:if test="${correctList.questionID==question.key.questionID}">
                                    <div class="modal-content">
                                        <span class="close" onclick="document.getElementById('${question.key.questionID}').style.display = 'none'">&times;</span>
                                        <p> The correct answer is ${correctList.content}</p>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>
        Your score is :${highscore}/${questionList.size()}
        <form action="Submit" method="POST">
            <input type="hidden" name="score" value="${highscore}"/>
             <input type="hidden" name="quizID" value="${requestScope.quizID}">
            <footer  style="color:white;">           
                <input type="submit" value="Go back" > </a> 
            </footer>
        </form>
    </div>       
</body>

</html>