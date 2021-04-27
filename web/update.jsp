<%-- 
    Document   : update
    Created on : Feb 20, 2021, 9:37:57 PM
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
        <title>Update Page</title>
    </head>
    <body>
        <div class="container-fulid" style="font-weight: 600">
            <div class="row">
                <div class="position-static">
                    <div class="topnav" id="myTopnav">
                        <c:if test="${sessionScope.ROLE.equals('Admin')}">
                            <a href="DisplayQuestion" class="active">Home</a>
                            <a href="CreatePage">Create Question</a>
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
            <c:set var="QLIST" value="${requestScope.QLIST}"/>
            <c:set var="SLIST" value="${requestScope.SLIST}"/>
            <c:set var="MAP" value="${requestScope.MAP}"/>
            <c:set var="UPDATEID" value="${requestScope.UPDATEID}"/>
            <h1>Update the information for the Question</h1>
            <form action="UpdateQuestion">
                <input type="hidden" name="txtQuestionName" value="${requestScope.QUESTIONNAME}" />
                <input type="hidden" name="cboStatuss" value="${requestScope.STATUS}" />
                <input type="hidden" name="cboSubjectt" value="${requestScope.SUBJECT}" />
                <input type="hidden" name="pageNo" value="${requestScope.PAGENO}" />
                <input type="hidden" name="txtQuestionID" value="${UPDATEID}" />
                <c:forEach var="qlist" items="${QLIST}">
                    <c:if test="${qlist.questionID == UPDATEID}">
                        <textarea rows="5" cols="270" name="txtQuestionContent" style="resize: none;" required="">${qlist.questionContent}</textarea><br/>
                        Subject: <select name="cboSubject">
                            <c:forEach var="slist" items="${SLIST}">
                                <c:if test="${qlist.subjectID == slist.subjectID}">
                                    <option value="${slist.subjectID}" selected="">${slist.subjectID}</option>
                                </c:if>
                                <c:if test="${qlist.subjectID != slist.subjectID}">
                                    <option value="${slist.subjectID}">${slist.subjectID}</option>    
                                </c:if>
                            </c:forEach>
                        </select><br/>
                        <c:forEach var="map" items="${MAP}">
                            <c:if test="${map.key == UPDATEID}">
                                <c:forEach var="alist" items="${map.value}" varStatus="counter">
                                    Answer ${counter.count}<br/>
                                    <input type="hidden" name="txtAnswerID" value="${alist.answerID}" /><br/>
                                    <textarea rows="1" cols="270" name="txtAnswerContent" style="resize: none;" required="">${alist.answerContent}</textarea><br/>
                                    <c:if test="${alist.status == '1'}">
                                        Is it true?<input type="checkbox" name="chkStatus${counter.count - 1}" value="1" checked=""/><br/>
                                    </c:if>
                                    <c:if test="${alist.status == '0'}">
                                        Is it true?<input type="checkbox" name="chkStatus${counter.count - 1}" value="1"/><br/>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:forEach>
                <input type="submit" value="Update" class="btn btn-dark" onclick="return confirm('Are u sure about that?')"/>
            </form>
            <form action="SearchPage">
                <input type="submit" value="Cancel" class="btn btn-dark" onclick="return confirm('Are u sure about that?')"/>
            </form>
        </div>
    </body>
</html>
