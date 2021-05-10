<%-- 
    Document   : search
    Created on : Feb 16, 2021, 6:15:06 PM
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
        <title>Search Page</title>
        <style>
            .content__right{
                padding-top: 20px;
                padding-right: -10px;
            }
            .content__right a{
                padding: 0 10px;
            }
            .search__form nav{
                display: flex;
                justify-content: space-around;
                padding: 10px 0;
            }
            .car__rent{
                margin: 70px 0;
            }
            #amount:focus{
                outline: none;
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
        <!--HEADER-->
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
        <!--CARD-->
        <section class="car__rent">
            <a class="py-5" href="search.jsp">Back to search</a>
            <c:if test="${not empty sessionScope.LISTCAR}">
                <c:if test="${not empty requestScope.ERRORQUANTITY}">
                    <p class="alert alert-danger text-center">${requestScope.ERRORQUANTITY}</p>
                </c:if>
                <div class="row">
                    <c:forEach var="car" items="${sessionScope.LISTCAR}">
                        <div class="card col-3" style="width: 18rem;">
                            <img name="imgCar" src="${car.imgCar}" class="card-img-top">
                            <div class="card-body">
                                <h5 class="card-title">${car.nameCar}</h5>
                                <p class="card-text">Price: <span class="font-weight-bold">$${car.price}</span></p>
                                <p class="card-text">Color: <span class="font-weight-bold">${car.color}</span></p>
                                <p class="card-text">Quantity: <span class="font-weight-bold">${car.quantity}</span></p>
                                <p class="card-text">Year: <span class="font-weight-bold">${car.year}</span></p>
                                <p class="card-text">
                                    Rate: <c:if test="${car.rate > 0}"><span class="font-weight-bold">${car.rate}/10</span></c:if>
                                    <c:if test="${car.rate < 1}"><span class="font-italic">(No reviews yet)</span></c:if>
                                    </p>
                                <c:if test="${not empty sessionScope.ACCOUNT}">
                                    <form action="addToCart" method="POST">
                                        <input type="hidden" name="txtId" value="${car.id}" />
                                        <input type="hidden" name="txtName" value="${car.nameCar}" />
                                        <input type="hidden" name="txtColor" value="${car.color}" />
                                        <input type="hidden" name="txtQuantity" value="${car.quantity}" />
                                        <input type="hidden" name="txtYear" value="${car.year}" />
                                        <input type="hidden" name="txtPrice" value="${car.price}" />
                                        <input type="hidden" name="txtCate" value="${car.idCategory}" />
                                        <p class="card-text">Amount of car: <input type="number" id="amount" min="1" max="${car.quantity}" required name="txtAmount" value="1" /> </p>
                                        <input type="submit" class="btn btn-primary" value="Add to cart" />
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${empty sessionScope.LISTCAR}">
                <h1 class="text-center text-secondary display-4 font-weight-bold">NO RESULTS FOUND!</h1>
            </c:if>
            <div class="page text-center m-5">
                <c:if test="${sessionScope.NUMBEROFPAGE > 1}">
                    <c:if test="${not empty sessionScope.SEARCHNAME}">
                        <c:forEach var="page" begin="1" end="${sessionScope.NUMBEROFPAGE}">
                            <a class="px-4 text-black <c:if test="${sessionScope.INDEX eq page}"> text-white bg-dark </c:if>" href="searchName?index=${page}">${page}</a>
                        </c:forEach>
                    </c:if>
                    <c:if test="${not empty sessionScope.SEARCHCATE}">
                        <c:forEach var="page" begin="1" end="${sessionScope.NUMBEROFPAGE}">
                            <a class="px-4 text-black <c:if test="${sessionScope.INDEX eq page}"> text-white bg-dark </c:if>" href="searchName?index=${page}">${page}</a>
                        </c:forEach>
                    </c:if>
                </c:if>
            </div>
        </section>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
        <script>
            function myFunction() {
                if (document.getElementById('txtError').value == 'error') {
                    alert('We do not have enough car for you!');
                }
            }
        </script>
    </body>
</html>
