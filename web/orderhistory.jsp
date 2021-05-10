<%-- 
    Document   : orderhistory
    Created on : Mar 6, 2021, 11:01:45 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Order History</title>
    </head>
    <body>
        <h1 class="alert alert-secondary text-warning text-center">ORDER HISTORY</h1>
        <a href="showCategory">BACK</a>
        <form action="searchOrder" method="POST">
            <div class="d-flex justify-content-center">
                <div>
                    <input type="radio" <c:if test="${sessionScope.SELECTORDERSEARCH eq 1}">checked</c:if> onclick="myFunction()" name="rd" value="1" />
                    <input type="text" <c:if test="${sessionScope.SELECTORDERSEARCH eq 2}">disabled</c:if> id="name" name="txtSearch" required placeholder="Search name..." value="${sessionScope.SEARCHNAMEORDER}" />
                    </div>
                    <div class="px-5">
                        <input type="radio" <c:if test="${sessionScope.SELECTORDERSEARCH eq 2}">checked</c:if> onclick="myFunction1()" name="rd" value="2" />
                    <input type="date" <c:if test="${sessionScope.SELECTORDERSEARCH eq 1}">disabled</c:if> id="date" required name="txtOrderDate" value="${sessionScope.SEARCHDATEORDER}" />
                    </div>
                    <div>
                        <input type="submit" class="btn btn-primary" value="Search" />
                    </div>
                </div>
            </form>
        <c:if test="${not empty requestScope.ERRORORDER}">
            <p class="alert alert-danger">${requestScope.ERRORORDER}</p>
        </c:if>
        <c:if test="${not empty sessionScope.LISTORDER}">
            <c:if test="${not empty sessionScope.LISTORDERDETAIL}">
                <c:forEach var="order" items="${sessionScope.LISTORDER}">
                    <c:set var="total" value="0"/>
                    <p>Bill: #${order.idOrder}</p>
                    <p>Date order: ${order.dateOrder}</p>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Car name</th>
                                <th>color</th>
                                <th>Year</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Rental date</th>
                                <th>Return date</th>
                                <th>Status</th>
                                <th>Rate</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="orderDetail" items="${sessionScope.LISTORDERDETAIL}">
                                <c:if test="${order.idOrder eq orderDetail.idOrder}">
                                    <tr>
                                        <td <c:if test="${orderDetail.status eq false}">style="text-decoration: line-through"</c:if>>${orderDetail.carName}</td>
                                        <td <c:if test="${orderDetail.status eq false}">style="text-decoration: line-through"</c:if>>${orderDetail.color}</td>
                                        <td <c:if test="${orderDetail.status eq false}">style="text-decoration: line-through"</c:if>>${orderDetail.year}</td>
                                        <td <c:if test="${orderDetail.status eq false}">style="text-decoration: line-through"</c:if>>${orderDetail.quantity}</td>
                                        <td <c:if test="${orderDetail.status eq false}">style="text-decoration: line-through"</c:if>>${orderDetail.price}</td>
                                        <td <c:if test="${orderDetail.status eq false}">style="text-decoration: line-through"</c:if>>${orderDetail.rentDate}</td>
                                        <td <c:if test="${orderDetail.status eq false}">style="text-decoration: line-through"</c:if>>${orderDetail.returnDate}</td>
                                            <td>
                                            <c:if test="${orderDetail.status eq true}">
                                                activate
                                            </c:if>
                                            <c:if test="${orderDetail.status eq false}">
                                                inactivate
                                            </c:if>
                                        </td>
                                        <td>
                                            <form action="feedBack" method="POST">
                                                <input type="hidden" name="txtIdOrder" value="${order.idOrder}"/>
                                                <input type="hidden" name="txtIdCar" value="${orderDetail.idCar}"/>
                                                <input type="hidden" name="txtReturnDate" value="${orderDetail.returnDate}"/>
                                                <input type="hidden" name="txtRentalDate" value="${orderDetail.rentDate}"/>
                                                <input type="number" <c:if test="${orderDetail.status eq false}">disabled</c:if> name="txtRate" min="1" max="10" value="${orderDetail.rate}" />
                                                <input type="submit" <c:if test="${orderDetail.status eq false}">disabled</c:if> class="btn btn-success" value="feedback" />
                                                </form>
                                            </td>
                                            <td>                       
                                                <form action="deleteOrder" method="POST">
                                                    <input type="hidden" name="txtIdCar" value="${orderDetail.idCar}" />
                                                <input type="hidden" name="txtNameCar" value="${orderDetail.carName}" />
                                                <input type="hidden" name="txtIdOrder" value="${order.idOrder}" />
                                                <input type="hidden" name="txtRentDate" value="${orderDetail.rentDate}" />
                                                <input type="submit" class="btn btn-danger" onclick="return confirm('Do you want to cancel ${orderDetail.carName}?')" <c:if test="${orderDetail.status eq false}">disabled</c:if> value="Delete" /> 
                                                </form>
                                            </td>
                                        </tr>
                                    <c:set var="total" value="${total + orderDetail.price * orderDetail.quantity}"/>
                                </c:if>
                            </c:forEach>
                        <p>Total: ${total - (total * order.percent)/100 }</p>
                    </tbody>
                </table>
            </c:forEach>
        </c:if>
        <c:if test="${empty sessionScope.LISTORDERDETAIL}">
            <p class="display-4 text-secondary text-center">NO RESULT TO SHOW</p>
        </c:if>
    </c:if>
    <c:if test="${empty sessionScope.LISTORDER}">
        <p class="display-4 text-secondary text-center">NO RESULT TO SHOW</p>
    </c:if>


    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <script>
                                                    function myFunction() {
                                                        document.getElementById('name').disabled = false;
                                                        document.getElementById('date').disabled = true;
                                                    }
                                                    function myFunction1() {
                                                        document.getElementById('name').disabled = true;
                                                        document.getElementById('date').disabled = false;
                                                    }
    </script>
</body>
</html>
