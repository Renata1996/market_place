<!DOCTYPE html>
<html xmlns:v-on="http://www.w3.org/1999/xhtml">

<head>
    <title>Edit Item</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

</head>
<body>

<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Edit Item</h3>
            </div>
            <div class="panel-body">
                <form id="form" method="post" action="/editItem">

                    <%if (request.getAttribute("UID") != null) {
                    String input ="input type=hidden name=UID value=";
                    out.println("<"+input+
                     request.getAttribute("UID").toString()+">");
                    }%>

                    <div class="form-group">
                        <label class="control-label"> Title</label>
                        <input type="text" name="title" class="form-control" id="title" placeholder="Title"
                               v-model="title" v-on:keyup="validateTitle">
                        <span class="help-block">{{messageTitle}}</span>
                    </div>

                    <div class="form-group">
                        <label class="control-label"> Description</label>
                        <input type="text" name="description" class="form-control" id="description" placeholder="Description"
                               v-model="description">
                        <span class="help-block">{{messageDescription}}</span>
                    </div>

                    <div class="form-group">
                        <label class="control-label"> Start price</label>
                        <input type="number" name="startPrice" class="form-control" id="startPrice"
                               placeholder="0"
                               v-model="startPrice" v-on:keyup="validatePrice">
                        <span class="help-block">{{messagePrice}}</span>
                    </div>

                    <div class="form-group">
                        <label class="control-label"> Time Left</label>
                        <input type="text" name="time" class="form-control" id="time"
                               placeholder="0:0" v-model="time" v-on:keyup="validateChecked">
                        <span class="help-block">{{messageTime}}</span>
                    </div>

                    <div class="form-group">
                        <label class="control-label"> Buy It Now</label>
                        <input type="checkbox" name="checkBox" id="checkbox" v-model="checked" v-on:click="agreeForm(this.form)">
                        <label for="checkbox">{{ messageChecked }}</label>
                    </div>

                    <div class="form-group">
                        <label class="control-label"> Bid Increment</label>
                        <input type="number" name="increment" class="form-control" id="increment"
                               placeholder="0" v-model="increment">
                        <span class="help-block">{{messageIncrement}}</span>
                    </div>


                    <span class="help-block">{{messageError}}</span>
                    <button type="submit" class="btn btn-primary">Edit</button>
                    <button type="btn btn-default" class="btn btn-default" v-on:click="reset">Reset</button>
                </form>
            </div>


            <div class="panel-footer">
                <p>Show all <a href="/showItems">items</a></p>
                <p>Show my <a href="/myItems">items</a></p>
            </div>
        </div>
    </div>
</div>


<script src="https://unpkg.com/vue@2.2.6"></script>
<script src="js/edit_item.js"></script>
</body>

</html>