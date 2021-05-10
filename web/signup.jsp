<%-- 
    Document   : signup
    Created on : Feb 16, 2021, 6:41:29 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Sign Up Page</title>
    </head>
    <body>
        <a href="search.jsp">BACK</a>
        <div class="container">
            <h1 class="text-center display-4 font-weight-normal">Create new account</h1>
            <form action="signUp" method="POST">
                <div class="form-group">
                    <p>Email <span class="text-danger font-weight-bold px-5">${requestScope.EMAILEXIST}</span></p>
                    <input type="email" class="form-control" required name="txtEmail" maxlength="100" placeholder="Email" value="${param.txtEmail}" />
                </div>
                <div class="form-group">
                    <p>Name</p>
                    <input type="text" class="form-control" required placeholder="Name" name="txtName" value="${param.txtName}" />
                </div>
                <div class="form-group">
                    <p>Password</p>
                    <input type="password" class="form-control" required placeholder="Password" name="txtPassword" value="" />
                </div>
                <div class="form-group">
                    <p>Phone</p>
                    <input type="tel" class="form-control" name="txtPhone" placeholder="Phone number" pattern="[0-9]{10}" value="${param.txtPhone}" />
                </div>
                <div class="form-group">
                    <p>Address</p>
                    <input type="text" class="form-control" required name="txtAddress" placeholder="Address" value="${param.txtAddress}" />
                </div>
                <div class="form-group text-right">
                    <a href="login.jsp">Back to login page</a>
                    <input class="btn btn-success" type="submit" value="Create new account" />
                </div>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </body>
</html>
