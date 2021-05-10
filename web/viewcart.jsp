<%-- 
    Document   : viewcart
    Created on : Feb 19, 2021, 5:52:10 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>View Cart</title>
        <style>
            .content__right{
                padding-top: 20px;
                padding-right: -10px;
            }
            .content__right a{
                padding: 0 10px;
            }
        </style>
    </head>
    <body>
        <header>
            <div class="row">
                <div class="col-6">
                    <h1 class="display-4 font-weight-bold">RENTAL CAR</h1>
                </div>
                <div class="col-6 text-center content__right">
                    <c:if test="${not empty sessionScope.USERNAME}">
                        <p>
                            <span style="border-right: 1px solid gray" class="px-2">
                                Welcome, <span class="font-weight-bold">${sessionScope.USERNAME}</span>
                            </span>
                            <a class="px-2" href="logOut">Logout</a>
                        </p>
                    </c:if>
                    <c:if test="${empty sessionScope.USERNAME}">
                        <p>
                            <a style="border-right: 1px solid gray;" href="login.jsp">Login</a>
                            <a href="signup.jsp">Sign up</a>
                        </p>
                    </c:if>
                </div>
            </div>
        </header>
        <section class="alert alert-secondary text-center">
            <p class="display-4 font-weight-bold">VIEW CART</p>
        </section>
        <c:if test="${not empty requestScope.ERRORUPDATEQUANTITY}">
            <p class="alert alert-danger">${requestScope.ERRORUPDATEQUANTITY}</p>
        </c:if>
        <section>
            <div>
                <a href="search.jsp">Back</a>
            </div>
            <div class="container">
                <c:if test="${not empty sessionScope.CUSTCART}">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">Name car</th>
                                <th scope="col">Type</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Price</th>
                                <th scope="col">Date rental</th>
                                <th scope="col">Date return</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${sessionScope.LIST}" varStatus="counter">
                                <%--<c:forEach var="car" items="${sessionScope.LISTCAR}">--%>
                                <%--<c:if test="${car.nameCar eq item.nameCar}">--%>
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${item.nameCar}</td>
                                    <td>${item.cateCar}</td>
                                    <td>
                                        <form action="updateCart" method="POST">
                                            <input type="hidden" name="txtID" value="${item.idCar}" />
                                            <input type="hidden" name="txtName" value="${item.nameCar}" />
                                            <input type="hidden" name="txtRental" value="${item.rentDate}" />
                                            <input type="hidden" name="txtReturn" value="${item.returnDate}" />
                                            <input type="number" name="txtAmount" id="txtAmount" min="1" <%--max="${car.quantity}"--%> value="${item.quantity}" />
                                            <input type="submit" class="btn btn-primary" value="Submit" />
                                        </form>

                                    </td>
                                    <td>${item.price * item.quantity}</td>
                                    <c:set var="total" value="${total + item.price * item.quantity}"/>
                                    <td>${item.rentDate}</td>
                                    <td>${item.returnDate}</td>
                                    <td>
                                        <form action="deleteCart" method="POST">
                                            <input type="hidden" name="txtId" value="${item.idCar}" />
                                            <input type="hidden" name="txtRental" value="${item.rentDate}" />
                                            <input type="hidden" name="txtReturn" value="${item.returnDate}" />
                                            <input class="btn btn-danger" onclick="return confirm('Do you want to delete ${item.nameCar}?')" type="submit" value="Delete" />
                                        </form>
                                    </td>
                                </tr>
                                <%--</c:if>--%>
                                <%--</c:forEach>--%>
                            </c:forEach>
                            <tr>
                                <td colspan="8">
                                    Total: $${total - (total * sessionScope.PERCENT)/100}
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${empty sessionScope.CUSTCART}">
                    <h1 class="display-4 text-center font-weight-bold text-secondary">NO ITEM IN CART!</h1>
                </c:if>
            </div>
        </section>

        <section>
            <c:if test="${not empty sessionScope.CUSTCART}">
                <div class="container">
                    <p>Discount code:</p>
                    <div class="row">
                        <div class="input-group col-4">
                            <form action="discount" method="POST" style="width: 100%;">
                                <input type="hidden" name="txtTotal" value="${total}" />
                                <input type="text" class="form-control d-inline" style="width: 70%;" <c:if test="${not empty sessionScope.CODE}">disabled</c:if> name="txtDiscount" placeholder="(optional)" value="${sessionScope.CODE}" />
                                    <div class="input-group-append d-inline">
                                        <input type="submit"  <c:if test="${not empty sessionScope.CODE}">disabled</c:if> class="btn btn-info" value="use" />
                                    </div>
                                </form>
                            <c:if test="${not empty requestScope.EXPIRED}" >
                                <p class="text-danger">${requestScope.EXPIRED}</p>
                            </c:if>
                            <c:if test="${not empty requestScope.DISCOUNTERROR}">
                                <p class="text-danger">${requestScope.DISCOUNTERROR}</p>
                            </c:if>
                        </div>
                    </div>
                    <form action="checkOut" method="POST">
                        <div class="form-group py-3">
                            <input type="hidden" name="txtTotal" value="${total - (total * sessionScope.PERCENT)/100}" />
                            <input type="submit" class="btn btn-success" onclick="myFunction()" value="check out" />
                        </div>
                    </form>
                </div>
            </c:if>
        </section>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
        <script>
                                function myFunction() {
                                    if (document.getElementById('txtAmount').value === "") {
                                        alert('Please fill quantity');
                                    }
                                }
        </script>
    </body>
</html>
