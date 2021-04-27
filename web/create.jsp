<%-- 
    Document   : create
    Created on : Feb 19, 2021, 6:43:28 PM
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
        <title>Create Page</title>
    </head>
    <body>
        <div class="container-fulid" style="font-weight: 600">
            <div class="row">
                <div class="position-static">
                    <div class="topnav" id="myTopnav">
                        <c:if test="${sessionScope.ROLE.equals('Admin')}">
                            <a href="DisplayQuestion">Home</a>
                            <a href="CreatePage" class="active">Create Question</a>
                        </c:if>
                        <c:if test="${sessionScope.ROLE.equals('Student')}">
                            <a href="DisplayQuizList">Home</a>
                        </c:if>
                        <a href="DisplayHistory">History</a>
                        <div class="logintopnav">
                            <h3>Hello,<font color="#66ff33">${NAME}</font></h3>
                            <a href="LogOutServlet" >Logout</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fulid">
            <c:set var="SLIST" value="${sessionScope.SLIST}"/>
            <h1>Input the information for the new Question</h1>
            <form action="CreateQuestion" method="POST" onsubmit="alert('Create Question Completed!')">
                <div>
                    Content:<br/>
                    <textarea rows="5" cols="270" name="txtQuestionContent" style="resize: none;" required=""></textarea><br/>
                </div>
                Subject: <select name="cboSubject" required="">
                    <option value="">---Choose---</option>
                    <c:forEach var="listsubject" items="${SLIST}">
                        <option value="${listsubject.subjectID}">${listsubject.subjectID}</option>    
                    </c:forEach>
                </select>
                <div>
                    <div>
                        Answer 1:<br/>
                        <textarea rows="1" cols="270" name="txtAnswerContent" style="resize: none;" required=""></textarea><br/>
                        Is it true? <input type="checkbox" name="ckbStatus0" value="1"/>
                    </div>
                    <div>
                        Answer 2:<br/>
                        <textarea rows="1" cols="270" name="txtAnswerContent" style="resize: none;" required=""></textarea><br/>
                        Is it true? <input type="checkbox" name="ckbStatus1" value="1"/>
                    </div>
                    <div>
                        Answer 3:<br/>
                        <textarea rows="1" cols="270" name="txtAnswerContent" style="resize: none;" required=""></textarea><br/>
                        Is it true? <input type="checkbox" name="ckbStatus2" value="1"/>
                    </div>
                    <div>
                        Answer 4:<br/>
                        <textarea rows="1" cols="270" name="txtAnswerContent" style="resize: none;" required=""></textarea><br/>
                        Is it true? <input type="checkbox" name="ckbStatus3" value="1"/>
                    </div>
                </div>
                <input type="submit" value="Create" class="btn btn-dark" onclick="return confirm('Are u sure about that?')"/>
            </form>
            <form action="SearchPage">
                <input type="submit" value="Cancel" class="btn btn-dark" onclick="return confirm('Are u sure about that?')"/>
            </form>
        </div>
    </body>
</html>
