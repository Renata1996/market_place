<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <title>Show Items</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

</head>
<body>


<div class="container">
    <div>
        <div class="row col-md-8 col-md-offset-4">
            <h1 class="header">Online marketplace</h1>
        </div>
        <div class=" row col-md-3 col-md-offset-8" id="#logout">
            <%if (request.getAttribute("loginName") != null) {
            out.println("<h3> Log Out <a href=/index>" +
            request.getAttribute("loginName").toString() + "</a></h3>");
            }%>

        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <form method="post" action="/showItems">
                <label>Search</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="searchField">
                    <div class="input-group-btn">
                        <button type="submit" class="btn btn-default dropdown-toggle">Search</button>
                    </div>
                    <div class="col-md-6">
                        <select id="selection" name="selection">
                            <option value="UID">UID</option>
                            <option value="title">Title</option>
                            <option value="description">Description</option>
                        </select>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <%if (request.getAttribute("loginName") == null) {
            out.println("<a href=/index>Log in</a>");
            }%>
            <%if (request.getAttribute("loginName") != null) {
            out.println("<a href=/showItems> all items </a>"+"<a href=/myItems>my items </a>"+"<a href=/editItem> sell</a>");
            }%>
            <div class="table-responsive">

                <table class="table ">
                    <tr>
                        <th width="30">UID</th>
                        <th width="100">Title</th>
                        <th width="700">Descriprion</th>
                        <th width="100">Seller</th>
                        <th width="100">Start price</th>
                        <th width="100">Bid Inc</th>
                        <th width="200">Stop Date</th>
                        <th width="100">Best offer</th>
                        <th width="100">Bidder</th>
                        <th width="200">Bidding</th>
                        <th></th>
                    </tr>

                    <c:forEach items="${items}" var="item">
                        <form method="post" action="/showItems">
                            <tr>
                                <td>${item.key.itemID}</td>
                                <td>${item.key.title}</td>
                                <td>${item.key.description}</td>
                                <td>${item.key.sellerID}</td>
                                <td>${item.key.startPrice}</td>
                                <c:choose>
                                    <c:when test="${!item.key.byNowFlag}">
                                        <td>${item.key.stepRate}</td>
                                        <td>
                                            <jsp:useBean id="dateValue" class="java.util.Date"/>
                                            <jsp:setProperty name="dateValue" property="time"
                                                             value="${item.key.timeLeft}"/>
                                            <fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm"/>
                                        </td>
                                    </c:when>
                                    <c:when test="${item.key.byNowFlag}">
                                        <td>-</td>
                                        <td>-</td>
                                    </c:when>
                                </c:choose>
                                <c:if test="${item.value.bid == 0}">
                                    <td> -</td>
                                    <td>-</td>
                                </c:if>
                                <c:if test="${item.value.bid != 0}">
                                    <td> ${item.value.bid}</td>
                                    <td> ${item.value.userID}</td>
                                </c:if>

                                <c:if test="${item.key.timeLeft >= timeNow || item.key.byNowFlag}">
                                    <c:choose>
                                        <c:when test="${!item.key.byNowFlag}">
                                            <td>
                                                <div class="input-group">
                                                    <input type="number" class="form-control" name="bidText">
                                                    <input type="hidden" name="itemID" value="${item.key.itemID}">
                                                    <input type="hidden" name="startPrice"
                                                           value="${item.value.bid}">
                                                    <input type="hidden" name="minBidInc" value="${item.key.stepRate}">
                                                    <div class="input-group-btn">
                                                        <input type="hidden" name="bid" value="submit">
                                                        <button type="submit" class="btn btn-default dropdown-toggle">
                                                            Bid
                                                        </button>
                                                    </div>
                                                </div>
                                            </td>
                                        </c:when>
                                        <c:when test="${item.key.byNowFlag}">
                                            <td>
                                                <c:if test="${item.value.bid == 0}">
                                                    <input type="hidden" name="buy" value="submit">
                                                    <input type="hidden" name="itemID" value="${item.key.itemID}">
                                                    <input type="hidden" name="startPrice"
                                                           value="${item.key.startPrice}">
                                                    <button type="submit" class="btn btn-default btn-block">Buy
                                                    </button>
                                                </c:if>
                                                <c:if test="${item.value.bid != 0}">
                                                    -
                                                </c:if>
                                            </td>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                                <c:if test="${item.key.timeLeft < timeNow && !item.key.byNowFlag}">
                                    <td>
                                     -
                                    </td>
                                </c:if>
                            </tr>
                        </form>
                    </c:forEach>

                </table>

            </div>
        </div>
    </div>
</div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/vue@2.2.6"></script>
<script src="js/show_items.js"></script>

</body>
</html>