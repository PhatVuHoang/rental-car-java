<%-- 
    Document   : search1
    Created on : Mar 2, 2021, 7:41:18 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w==" crossorigin="anonymous" />
        <title>Search page</title>
        <style>
            .content__right{
                padding-top: 20px;
                padding-right: -10px;
            }
            .content__right a{
                padding: 0 10px;
            }
            .user__cart{
                padding-top: 20px;
            }
            .user__cart a{
                color: black;
            }
            .user__cart a:hover{
                text-decoration: none;
                color: cornflowerblue;
            }
        </style>
    </head>
    <body>
        <header>
            <div class="row">
                <div class="col-6">
                    <h1 class="font-weight-bold display-4">RENTAL CAR</h1>
                </div>
                <div class="col-4 text-center content__right">
                    <c:if test="${not empty sessionScope.USERNAME}">
                        <p>
                            <span style="border-right: 1px solid gray;" class="px-2">Welcome, <span class="font-weight-bold">${sessionScope.USERNAME}</span></span>
                            <a href="logOut">logout</a>
                            <span style="border-left: 1px solid gray;" class="px-2">
                                <a href="orderHistory">order history</a>
                            </span>
                        </p>
                    </c:if>
                    <c:if test="${empty sessionScope.USERNAME}">
                        <p>
                            <a style="border-right: 1px solid gray;" href="login.jsp">Login</a>
                            <a href="signup.jsp">Sign up</a>
                        </p>
                    </c:if>
                </div>
                <div class="col-2 text-center user__cart">
                    <a href="viewcart.jsp"><i class="fa fa-shopping-cart"></i> View cart</a>
                </div>
            </div>
        </header>
        <section class="search">
            <div class="container">
                <c:if test="${not empty requestScope.ERRORDATE}">
                    <p class="alert alert-danger">${requestScope.ERRORDATE}</p>
                </c:if>
                <div class="row py-5">
                    <div class="col-6 px-4">
                        <form action="searchName" method="POST">
                            <div class="form-group">
                                <p><input class="mr-3" onclick="myFunction()" type="radio" <c:if test="${sessionScope.SELECT eq 1}">checked</c:if> name="rad" value="1" />Search by name:</p>
                                <input type="text" id="input" class="form-control" <c:if test="${sessionScope.SELECT eq 2}">disabled</c:if> placeholder="Search name car..." required name="txtSearch" value="${sessionScope.SEARCHNAME}"/>
                                </div>
                                <div class="form-group">
                                    <p><input class="mr-3" onclick="myFunction1()" <c:if test="${sessionScope.SELECT eq 2}">checked</c:if> type="radio" name="rad" value="2" />Search by category:</p>
                                <select id="dropDown" name="cbCategory" <c:if test="${sessionScope.SELECT eq 1}">disabled</c:if> class="form-control">
                                    <c:forEach var="category" items="${sessionScope.CATEGORY}">
                                        <option <c:if test="${sessionScope.SEARCHCATE eq category.nameCategory}">selected</c:if>>${category.nameCategory}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <p>Rental date:</p>
                                <input type="date" class="form-control" name="txtRentDate" required value="${sessionScope.RENTDATE}"/>
                            </div>
                            <div class="form-group">
                                <p>Return date:</p>
                                <input type="date" class="form-control" name="txtReturnDate" required value="${sessionScope.RETURNDATE}"/>
                            </div>
                            <div class="form-group">
                                <p>Quantity</p>
                                <input type="number" class="form-control" name="txtQuantitySearch" min="1" required value="1"/>
                            </div>
                            <input type="submit" class="btn btn-success" value="Search"/>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>        
        <script>
                                        function myFunction() {
                                            document.getElementById('dropDown').disabled = true;
                                            document.getElementById('input').disabled = false;
                                        }
                                        function myFunction1() {
                                            document.getElementById('input').disabled = true;
                                            document.getElementById('dropDown').disabled = false;
                                        }
        </script>
    </body>
</html>
