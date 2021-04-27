<%-- 
    Document   : login
    Created on : Feb 18, 2021, 3:54:42 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/css/login.css"%></style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="828898265438-6fl8e70dtib95fqg88agg2rs77p98jcf.apps.googleusercontent.com">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <div class="wrapper fadeInDown">
            <div id="formContent">
                <div class="fadeIn first"><h1>Login</h1></div>
                <form action="Login" method="POST">
                    <c:if test="${requestScope.ERROR!= null}"><font color="red">${requestScope.ERROR}</font></c:if><br/>
                        <input type="hidden" name="txtName" class="fadeIn third" id="name"/><br/>
                        <input type="hidden" name="logingoogle" value="" id="logingoogle" /><br/>
                        Email:<input type="text" name="txtEmail" value="${param.txtEmail}" class="fadeIn second" placeholder="Email" id="email" required=""/><br/>
                    Pass:<input type="password" name="txtPassword" class="fadeIn third" placeholder="Password" required=""/><br/>
                    <input type="submit" value="Login" class="fadeIn fourth" id="Login"/>
                </form>
                <script>
                    function onSignIn(googleUser) {
                        var profile = googleUser.getBasicProfile();
                        document.getElementById("email").value = profile.getEmail();
                        document.getElementById("name").value = profile.getName();
                        document.getElementById("logingoogle").value = "logingoogle";
                        document.getElementById("Login").click();
                        signOut();
                    }

                </script>
                <script>
                    function signOut() {
                        var auth2 = gapi.auth2.getAuthInstance();
                        auth2.signOut().then(function () {
                            console.log('User signed out.');
                            document.getElementById("userID").value = null;
                            document.getElementById("password").value = null;
                        });
                    }
                </script>
                <div id="formFooter">
                    OR : <div class="fadeIn fitht" style="margin-left: 40%;"><div class="g-signin2" data-onsuccess="onSignIn"></div></div>
                    <form action="SignUpPage" class="fadeIn sixth">
                        <p style="text-align: center;">No account?</p>
                        <input type="submit" value="Registrate Here"/>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
