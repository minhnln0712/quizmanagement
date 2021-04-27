<%-- 
    Document   : registration
    Created on : Feb 17, 2021, 10:07:50 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/css/login.css"%></style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
    </head>
    <body>
        <div class="wrapper fadeInDown">
            <div id="formContent">
                <div class="fadeIn first"><h1>Sign Up</h1></div>
                <form action="SignUp" method="POST">
                    <c:if test="${requestScope.ERROR!= null}"><font color="red">${requestScope.ERROR}</font></c:if><br/>
                    Email:<input type="text" name="txtEmail" value="${param.txtEmail}" class="fadeIn second" placeholder="Email" required=""/><br/>
                    Name:<input type="text" name="txtName" value="${param.txtName}" class="fadeIn third" placeholder="Name" required=""/><br/>
                    Password:<input type="password" name="txtPassword" class="fadeIn third" placeholder="Password" required=""/><br/>
                    Confirm:<input type="password" name="txtConfirmPassword" class="fadeIn third"  placeholder="Confirm Password" required=""/><br/>
                    <input type="submit" value="Sign Up" class="fadeIn fourth" onclick="return confirm('Are u sure about that?')"/>
                </form>
                <div id="formFooter">
                    <form action="LoginPage">
                        <button type="submit" class="btn btn-dark btn-block">Cancel</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
