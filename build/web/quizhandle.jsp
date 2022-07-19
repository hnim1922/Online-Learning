<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset='utf-8'>
        <meta http-equiv='X-UA-Compatible' content='IE=edge'>
        <title>Quiz</title>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <link rel='stylesheet' type='text/css' media='screen' href='quizhandle.css'>

    </head>

    <body>
        <header>
            <h1>Quiz ${requestScope.quizID}</h1>
        </header>

        <div class="review-progress">
            <h2>Review Progress</h2>
            <div class="review-card">
                <c:forEach var="questionList" items="${questionList}">
                    <div id="question-${questionList.questionID}" class="review-box">
                        <p>${questionList.questionID}</p>
                    </div>
                </c:forEach>
            </div>
            <footer>
                <div style="font-size: larger" onload="startTimer();">Time left: <span id="timer"></span></div>
                <script>
                    document.getElementById('timer').innerHTML =
                            00 + ":" + 10;
                    startTimer();
                    function startTimer() {
                        var presentTime = document.getElementById('timer').innerHTML;
                        var timeArray = presentTime.split(/[:]+/);
                        var m = timeArray[0];
                        var s = checkSecond((timeArray[1] - 1));
                        if (s == 59) {
                            m = m - 1;
                        }
                        ;
                        if (m < 0) {
                            return;
                        }
                        ;
                        document.getElementById('timer').innerHTML =
                                m + ":" + s;
                        console.log(m);
                        setTimeout(startTimer, 1000);
                        if (m == 0 && s == 0)
                        {
                            document.getElementById('MyModal').style.display = 'block';
                        }
                    }
                    function checkSecond(sec) {
                        if (sec < 10 && sec >= 0) {
                            sec = "0" + sec;
                        }
                        ; // add zero in front of numbers < 10
                        if (sec < 0) {
                            sec = "59";
                        }
                        ;
                        return sec;
                    }
                </script>
            </footer>
        </div>    
        <form action="QuizReview" method="POST">
            <div class="question-infomation">
                <input type="hidden" name="quizID" value="${requestScope.quizID}">
                <c:forEach var="questionList" items="${questionList}">
                    <div class="question-card">
                        <p> ${questionList.content}</p>
                        <c:forEach var="answerList" items="${answerList}">
                            <c:if test="${answerList.questionID==questionList.questionID}"> 
                                <input type="radio" id="ans-${answerList.answerID}" name="${questionList.questionID}" value="${answerList.content},${questionList.questionID}"
                                       onclick="document.getElementById('question-${questionList.questionID}').style.background = 'green';">
                                <label for="ans-${answerList.answerID}">${answerList.content}</label><br>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:forEach>
                <div id="MyModal" class="modal">
                    <div class="modal-content">
                        <h1> Time Out </h1>
                       <input type="submit" name="quizSubmit" id="quizSubmit" value="Submit">
                    </div>
                </div>
                <footer  style="color:white;">
                    <input type="submit" name="quizSubmit" id="quizSubmit" value="Submit">
                </footer>
            </div>
        </form>
    </body>

</html>