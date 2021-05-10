<%-- 
    Document   : login
    Created on : Feb 16, 2021, 5:53:13 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Login</title>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <a href="search.jsp">BACK</a>
        <div class="container">
            <h1 class="text-center display-4 font-weight-normal">Login</h1>
            <form action="login" id="my_captcha_form" method="POST">
                <p class="text-danger">${requestScope.AUTHEN}</p>
                <div class="form-group">
                    <p>Email</p>
                    <input type="email" class="form-control" name="txtEmail" placeholder="Email" required value="${param.txtEmail}" />
                </div>
                <div class="form-group">
                    <p>Password</p>
                    <input type="password" class="form-control" name="txtPassword" placeholder="Password" required value="" />
                </div>
                <div class="g-recaptcha" data-sitekey="6LcaFG4aAAAAAA7067eudC0PI58Ns99U2W8PB8M2"></div>

                <div class="form-group text-right">
                    <a  href="signup.jsp">Sign up</a>
                    <input class="btn btn-success" type="submit" value="login" />
                </div>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
        <script>
            document.getElementById("my_captcha_form").addEventListener("submit", function (evt)
            {

                var response = grecaptcha.getResponse();
                if (response.length == 0)
                {

                    alert("Please verify you are human!");
                    evt.preventDefault();
                    return false;
                }


            });
        </script>
    </body>
</html>
