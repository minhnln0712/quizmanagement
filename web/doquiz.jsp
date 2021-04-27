<%-- 
    Document   : doquiz
    Created on : Feb 21, 2021, 6:02:38 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/css/header.css"%></style>
<style><%@include file="/WEB-INF/css/paging.css"%></style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Do Quiz Page</title>
    </head>
    <body>
        <c:set var="QLIST" value="${requestScope.QLIST}"/>
        <c:set var="RQLIST" value="${sessionScope.RQLIST}"/>
        <c:set var="MAP" value="${requestScope.MAP}"/>
        <c:set var="QUIZPOS" value="${requestScope.QUIZPOS}"/>
        <c:set var="QUIZTIME" value="${sessionScope.QUIZTIME}"/>
        <c:set var="NUMOFQUESTION" value="${sessionScope.NUMOFQUESTION}"/>
        <div class="container-fulid" style="font-weight: 600">
            <div class="row">
                <div class="position-static">
                    <div class="topnav" id="myTopnav">
                        <c:if test="${sessionScope.ROLE.equals('Admin')}">
                            <a href="DisplayQuestion" class="active">Home</a>
                            <a href="CreatePage">Create Question</a>
                        </c:if>
                        <c:if test="${sessionScope.ROLE.equals('Student')}">
                            <a href="DisplayQuiz">Home</a>
                        </c:if>
                        <a href="DisplayHistory">History</a>
                        <div class="logintopnav">
                            <h3>Hello,<font color="#66ff33">${NAME}</font></h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fulid">
            <form action="SubmitQuiz">
                <input type="submit" id="Submit" value="Submit" class="btn btn-dark"/>
            </form>
            <div style="height: 75px">
                <p style="font-size: 40px;text-align: center;font-weight: 900;" id="demo"></p>
            </div>
            <script>
                // Set the date we're counting down to
                var countDownDate = new Date('<c:out value="${QUIZTIME}"/>').getTime();

                // Update the count down every 1 second
                var x = setInterval(function () {

                    // Get today's date and time
                    var now = new Date().getTime();

                    // Find the distance between now and the count down date
                    var distance = countDownDate - now;

                    // Time calculations for days, hours, minutes and seconds
                    //                var days = Math.floor(distance / (1000 * 60 * 60 * 24));
                    //                var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                    // Output the result in an element with id="demo"
                    document.getElementById("demo").innerHTML = minutes + "m " + seconds + "s ";

                    // If the count down is over, write some text 
                    if (distance < 0) {
                        clearInterval(x);
                        document.getElementById("demo").innerHTML = "EXPIRED";
                        document.getElementById("Submit").click();
                    }
                }, 1000);
            </script>
            <div style="width: 100%;margin-left: 1%">
                <c:forEach var="qlist" items="${QLIST}" >
                    <c:forEach var="rqlist" items="${RQLIST}" >
                        <c:if test="${qlist.questionID == rqlist.questionID}">
                            <c:if test="${rqlist.randomID == QUIZPOS}">
                                <div style="font-family: 'Lato', sans-serif; font-size: 20px; font-weight: 300; line-height: 40px; margin: 0 0 58px;">
                                    Question: ${QUIZPOS} / ${NUMOFQUESTION}<br/>
                                    ${qlist.questionContent}
                                </div>
                                <div id="answer">
                                    Answer:<br/>
                                    <form action="ChangeQuestionInQuiz" style="font-family: 'Lato', sans-serif; font-size: 20px; font-weight: 300; line-height: 40px; margin: 0 0 58px;">
                                        <c:forEach var="map" items="${MAP}">
                                            <c:if test="${map.key == qlist.questionID}">
                                                <c:forEach var="value" items="${map.value}" varStatus="counter">
                                                    <c:if test="${value.answerID != rqlist.choseAnswerID}">
                                                        <input type="checkbox" name="chkQuestion${QUIZPOS}" value="${value.answerID}" />${counter.count}. ${value.answerContent}<br/>
                                                    </c:if>
                                                    <c:if test="${value.answerID == rqlist.choseAnswerID}">
                                                        <input type="checkbox" name="chkQuestion${QUIZPOS}" value="${value.answerID}" checked="true"/>${counter.count}. ${value.answerContent}<br/>            
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                        <input type="submit" value="Select"  class="btn btn-dark"/>
                                        <input type="hidden" name="txtQuestionPosNext" value="${QUIZPOS + 1}" />
                                        <input type="hidden" name="txtQuestionPosNow" value="${QUIZPOS}" />
                                    </form>
                                </div>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </div>
        </div>
        <div class="container-fulid">
            <c:forEach begin="1" end="${NUMOFQUESTION}" varStatus="counterr">
                <a href="ChangeQuestionInQuiz?txtQuestionPosNext=${counterr.count}" class="paging">${counterr.count}</a>
            </c:forEach>
        </div>
    </body>
</html>
