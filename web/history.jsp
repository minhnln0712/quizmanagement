<%-- 
    Document   : history
    Created on : Feb 22, 2021, 5:12:38 PM
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
        <title>History Page</title>
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
        <div class="container-fulid">
            <c:set var="HLIST" value="${requestScope.HLIST}"/>
            <c:set var="SLIST" value="${requestScope.SLIST}"/>
            <c:set var="MAXPAGENO" value="${requestScope.MAXPAGENO}"/>
            <div>
                <form action="SearchHistory">
                    Subject: <select name="cboSubject">
                        <option value="">---Choose---</option>
                        <c:forEach var="listsubject" items="${SLIST}">
                            <c:if test="${listsubject.subjectID == requestScope.SUBJECTSEARCH}">
                                <option value="${listsubject.subjectID}" selected="">${listsubject.subjectID}</option>    
                            </c:if>
                            <c:if test="${listsubject.subjectID != requestScope.SUBJECTSEARCH}">
                                <option value="${listsubject.subjectID}">${listsubject.subjectID}</option>    
                            </c:if>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Search" class="btn btn-dark"/>
                </form>
            </div>
            <table border="1" class="styled-table" style="background-color: #c2c2d6;color: black">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>History ID</th>
                        <th>Email</th>
                        <th>Subject ID</th>
                        <th>Mark</th>
                        <th>Create Date</th>
                        <th>Detail</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="list" items="${HLIST}" varStatus="counter">
                        <tr class="active-row">
                            <td>${counter.count}</td>
                            <td>${list.historyID}</td>
                            <td>${list.email}</td>
                            <td>${list.subjectID}</td>
                            <td>${list.mark}</td>
                            <td>${list.createDate}</td>
                            <td>
                                <form action="DisplayHistoryDetail">
                                    <input type="hidden" name="historyID" value="${list.historyID}" />
                                    <input type="submit" value="Details" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div style="text-align: center;">
                <c:forEach var="maxpageno" begin="1" end="${MAXPAGENO}">
                    <a class="paging" href="SearchHistoryServlet?pageNo=${maxpageno}&cboSubject=${param.cboSubject}">${maxpageno}</a>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
