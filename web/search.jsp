<%-- 
    Document   : display
    Created on : Feb 19, 2021, 2:03:16 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/css/table.css"%></style>
<style><%@include file="/WEB-INF/css/paging.css"%></style>
<style><%@include file="/WEB-INF/css/header.css"%></style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <c:set var="SLIST" value="${sessionScope.SLIST}"/>
        <c:set var="QLIST" value="${requestScope.QLIST}"/>
        <c:set var="MAP" value="${requestScope.MAP}"/>
        <c:set var="MAXPAGENO" value="${requestScope.MAXPAGENO}"/>
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
            <form action="SearchQuestion" style="text-align: center;">
                Name: <input type="text" name="txtQuestionName" value="${requestScope.QUESTIONNAME}"/>
                Status: <select name="cboStatus">
                    <c:if test="${requestScope.STATUS != null}">
                        <c:if test="${requestScope.STATUS == '1'}">
                            <option value="1" selected="">Activated</option>
                            <option value="0">Deactivated</option>    
                        </c:if>
                        <c:if test="${requestScope.STATUS == '0'}">
                            <option value="1">Activated</option>
                            <option value="0" selected="">Deactivated</option>
                        </c:if>
                    </c:if>
                    <c:if test="${requestScope.STATUS == null}">
                        <option value="1" selected="">Activated</option>
                        <option value="0">Deactivated</option>
                    </c:if>
                </select>
                Subject: <select name="cboSubject">
                    <option value="">---Choose---</option>
                    <c:forEach var="listsubject" items="${SLIST}">
                        <c:if test="${listsubject.subjectID == requestScope.SUBJECT}">
                            <option value="${listsubject.subjectID}" selected="">${listsubject.subjectID}</option>
                        </c:if>
                        <c:if test="${listsubject.subjectID != requestScope.SUBJECT}">
                            <option value="${listsubject.subjectID}">${listsubject.subjectID}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <input type="submit" value="Search" class="btn btn-dark"/>
            </form>
        </div>
        <div class="container-fulid">
            <table border="1" class="styled-table" style="background-color: #c2c2d6">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Subject ID</th>
                        <th>Question</th>
                        <th>Correct Answer</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="list" items="${QLIST}" varStatus="counter">
                        <tr class="active-row">
                            <td>${counter.count}</td>
                            <td>${list.subjectID}</td>
                            <td>${list.questionContent}</td>
                            <td>
                                <c:forEach var="map" items="${MAP}">
                                    <c:if test="${map.key == list.questionID}">
                                        <c:forEach var="value" items="${map.value}">
                                            <c:if test="${value.status == '0'}">
                                                ${value.answerContent}<br/>
                                            </c:if>
                                            <c:if test="${value.status == '1'}">
                                                <font color="red">${value.answerContent}</font><br/>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:if test="${list.status == '0'}">
                                    <form action="RevertQuestion">
                                        <input type="submit" value="Revert" class="btn btn-light" onclick="return confirm('Are u sure about that?')"/>
                                        <input type="hidden" name="txtQuestionID" value="${list.questionID}" />
                                        <input type="hidden" name="txtQuestionName" value="${requestScope.QUESTIONNAME}" />
                                        <input type="hidden" name="cboStatus" value="${requestScope.STATUS}" />
                                        <input type="hidden" name="cboSubject" value="${requestScope.SUBJECT}" />
                                        <input type="hidden" name="pageNo" value="${requestScope.PAGENO}" />
                                    </form>
                                </c:if>
                                <c:if test="${list.status == '1'}">
                                    <form action="DeleteQuestion">
                                        <input type="submit" value="Delete" class="btn btn-light" onclick="return confirm('Are u sure about that?')"/>
                                        <input type="hidden" name="txtQuestionID" value="${list.questionID}" />
                                        <input type="hidden" name="txtQuestionName" value="${requestScope.QUESTIONNAME}" />
                                        <input type="hidden" name="cboStatus" value="${requestScope.STATUS}" />
                                        <input type="hidden" name="cboSubject" value="${requestScope.SUBJECT}" />
                                        <input type="hidden" name="pageNo" value="${requestScope.PAGENO}" />
                                    </form>
                                </c:if>
                            </td>
                            <td>
                                <form action="DisplayQuestionUpdate">
                                    <input type="submit" value="Update" class="btn btn-light"/>
                                    <input type="hidden" name="txtQuestionID" value="${list.questionID}" />
                                    <input type="hidden" name="txtQuestionName" value="${requestScope.QUESTIONNAME}" />
                                    <input type="hidden" name="cboStatus" value="${requestScope.STATUS}" />
                                    <input type="hidden" name="cboSubject" value="${requestScope.SUBJECT}" />
                                    <input type="hidden" name="pageNo" value="${requestScope.PAGENO}" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="container-fulid">
            <div style="text-align: center;">
                <c:forEach var="maxpageno" begin="1" end="${MAXPAGENO}">
                    <a class="paging" href="SearchQuestion?pageNo=${maxpageno}&txtQuestionName=${requestScope.QUESTIONNAME}&cboStatus=${requestScope.STATUS}&cboSubject=${requestScope.SUBJECT}">${maxpageno}</a>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
