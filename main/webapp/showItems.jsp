
<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<head>
    <title>Show Items</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

</head>
<body>

<div class="header">
    <h1 class="header">Online marketplace</h1>
</div>
<div class="container">
    <div>
        <div class=" row col-md-3 col-md-offset-8" id="#logout">
           <%if (request.getAttribute("loginName") != null) {
                        out.println("<h3> LogOut <a href=../index.jsp onclick=logout>" + request.getAttribute("loginName").toString() + "</a></h3>");
           }%>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <label>Search</label>
            <div class="input-group">
                <input type="text" class="form-control">
                <div class="input-group-btn">
                    <button type="button" class="btn btn-default dropdown-toggle">Search</button>
                </div>
                <div class="col-md-6">
                    <select id="selection">
                        <option value="UID">UID</option>
                        <option value="title">Title</option>
                        <option value="description">Description</option>
                    </select>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <a href="../show_items/showItems.html">all items</a>
            <a href="../show_my_items/myItems.html">my items</a>
            <a href="../edit_item/editItem.html">sell</a>
            <div class="table-responsive">

                <table class="table ">
                    <tr>
                        <th width="30">UID</th>
                        <th width="100">Title</th>
                        <th width="700">Descriprion</th>
                        <th width="100">Seller</th>
                        <th width="100">Start price</th>
                        <th width="100">Bid Inc</th>
                        <th width="100">Best offer</th>
                        <th width="100">Bidder</th>
                        <th width="200">Stop Date</th>
                        <th width="200">Bidding</th>
                    </tr>

                    <c:forEach items="${items}" var="item">
                        <tr>
                            <td>${item.itemID}</td>
                            <td> ${item.title}</td>
                            <td> ${item.description}</td>
                            <td>${item.sellerID}</td>
                            <td>${item.startPrice}</td>
                            <td>${item.stepRate}</td>
                        </tr>
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
<script src="show_items.js"></script>

</body>
</html>