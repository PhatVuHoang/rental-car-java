<%-- 
    Document   : verify
    Created on : Mar 1, 2021, 7:04:41 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Verify Page</title>
    </head>
    <body>
        <div class="container py-5">
            <h1>Verify code</h1>
            <c:if test="${not empty requestScope.ERRORCODE}">
                <p class="alert alert-danger">${requestScope.ERRORCODE}</p>
            </c:if>
            <form action="verifyCode" method="POST">
                <div class="form-group">
                    <input class="form-control" type="text" placeholder="(6 characters)" name="txtVerify" value="" />
                </div>
                <a href="login.jsp">Back to login page</a>
                <input class="btn btn-success" type="submit" value="Verify account" />
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </body>
</html>
