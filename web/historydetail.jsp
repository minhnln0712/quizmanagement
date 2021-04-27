<%-- 
    Document   : historydetail
    Created on : Feb 26, 2021, 9:07:38 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/css/header.css"%></style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Detail Page</title>
    </head>
    <body>
        <div class="container-fulid" style="font-weight: 600">
            <div class="row">
                <div class="position-static">
                    <div class="topnav" id="myTopnav">
                        <c:if test="${sessionScope.ROLE.equals('Admin')}">
                            <a href="DisplayQuestion">Home</a>
                            <a href="CreatePage">Create Question</a>
                        </c:if>
                        <c:if test="${sessionScope.ROLE.equals('Student')}">
                            <a href="DisplayQuiz">Home</a>
                        </c:if>
                        <a href="DisplayHistory" class="active">History</a>
                        <div class="logintopnav">
                            <h3>Hello,<font color="#66ff33">${NAME}</font></h3>
                            <a href="LogOutServlet" >Logout</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fulid"><c:set var="MAP" value="${requestScope.MAP}"/>
            <c:set var="HDLIST" value="${requestScope.HDLIST}"/>
            <c:set var="QLIST" value="${requestScope.QLIST}"/>
            <div style="padding-top: 5%;">
                <c:forEach var="hdlist" items="${HDLIST}" varStatus="counter">
                    <c:forEach var="qlist" items="${QLIST}">
                        <div style=" background-color: #d1d1e0;font-family: 'Lato', sans-serif; font-size: 20px; font-weight: 300; line-height: 40px; margin: 0 0 58px;height: auto;">
                            <c:if test="${hdlist.questionID == qlist.questionID}">
                                Question ${counter.count} : ${qlist.questionContent} <br/>
                                Answer:<br/>
                                <c:forEach var="map" items="${MAP}">
                                    <c:if test="${map.key == hdlist.questionID}">
                                        <c:forEach var="value" items="${map.value}" varStatus="counter1">
                                            <div style="background-color: #33ff33">
                                                <c:if test="${hdlist.answerChoseID == value.answerID && value.status == '1'}">
                                                    <input type="checkbox" name="" value="${value.answerID}" checked="true" disabled="true"/>${counter1.count}. ${value.answerContent}<br/>            
                                                </c:if>
                                            </div>
                                            <div style="background-color: #ff6666">
                                                <c:if test="${hdlist.answerChoseID == value.answerID && value.status == '0'}">
                                                    <input type="checkbox" name="" value="${value.answerID}" checked="true" disabled="true"/>${counter1.count}. ${value.answerContent}<br/>            
                                                </c:if>
                                            </div>
                                            <div>
                                                <c:if test="${hdlist.answerChoseID != value.answerID && value.status == '1'}">
                                                    <input type="checkbox" name="" value="${value.answerID}" disabled="true"/>${counter1.count}. ${value.answerContent}<br/>
                                                </c:if>
                                            </div>
                                            <div>
                                                <c:if test="${hdlist.answerChoseID != value.answerID && value.status == '0'}">
                                                    <input type="checkbox" name="" value="${value.answerID}" disabled="true"/>${counter1.count}. ${value.answerContent}<br/>
                                                </c:if>
                                            </div>
                                            <br/>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </div>
                    </c:forEach>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
