<!DOCTYPE html>
<html xmlns:v-on="http://www.w3.org/1999/xhtml">

<head>
    <title>Registration</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

</head>
<body>

<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Registration</h3>
            </div>
            <div class="panel-body">
                <form id="form" method="post" action="/registration">
                    <div class="form-group">
                        <label class="control-label"> Full name</label>
                        <input type="text" name="fullName" class="form-control" id="fullName" placeholder="Full name"
                               v-model="fullName" v-on:keyup="validateFullName">
                        <span class="help-block">{{messageName}}</span>
                    </div>

                    <div class="form-group">
                        <label class="control-label"> Builling address</label>
                        <input type="text" name="buillingAddress" class="form-control" id="buillingAddress" placeholder="Builling address"
                               v-model="buillingAddress" v-on:keyup="validateFields">
                        <span class="help-block">{{messageAddress}}</span>
                    </div>

                    <div class="form-group">
                        <label class="control-label"> Login</label>
                        <input type="text" name="login" class="form-control" id="login" placeholder="Login"
                               v-model="login" v-on:keyup="validateLogin">
                        <span class="help-block">{{messageLogin}}</span>
                    </div>

                    <div class="form-group">
                        <label class="control-label"> Password</label>
                        <input type="password" name="password" class="form-control" id="password"
                               placeholder="Password" v-model="password" v-on:keyup="validatePassword">
                        <span class="help-block">{{messagePassword}}</span>
                    </div>
                    <div class="form-group">
                        <label class="control-label"> Re-enter password</label>
                        <input type="password" name="password" class="form-control" id="reEnteredPassword"
                               placeholder="Re-enter password" v-model="reEnteredPassword" v-on:keyup="validatePassword">
                        <span class="help-block">{{messageReEnteredPassword}}</span>
                    </div>
                    <span class="help-block">{{error}}</span>
                    <%if (request.getAttribute("error") != null) {
                    out.println("<span class=help-block>" + request.getAttribute("error").toString() + "</span>");
                    }%>
                    <button type="submit" class="btn btn-primary">Registration</button>
                    <button type="btn btn-default" class="btn btn-default" v-on:click="reset">Reset</button>
                </form>

            </div>
        </div>
    </div>
</div>


<script src="https://unpkg.com/vue@2.2.6"></script>
<script src="registration.js"></script>
</body>

</html>