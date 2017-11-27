<!DOCTYPE html>
<html xmlns:v-on="http://www.w3.org/1999/xhtml">

<head>
    <title>Login</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

</head>
<body>

<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Login</h3>
            </div>
            <div class="panel-body">
                <form id="form" method="POST" action="/index">
                    <div class="form-group">
                        <label class="control-label"> Login</label>
                        <input type="text" name="login" class="form-control" id="login" placeholder="Login"
                               v-model="login" v-on:keyup="validateInput">
                        <span class="help-block">{{messageLogin}}</span>
                    </div>
                    <div class="form-group">
                        <label class="control-label"> Password</label>
                        <input type="password" name="password" class="form-control" id="password"
                               placeholder="Password" v-model="password" v-on:keyup="validatePassword">
                        <span class="help-block">{{messagePassword}}</span>
                    </div>
                    <%if (request.getAttribute("error") != null) {
                    out.println("<span class=help-block>" + request.getAttribute("error").toString() + "</span>");
                    }%>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
            <div class="panel-footer">
                <p>You han't got an account <a href="../registration.jsp">Registration</a></p>
                <p>Or enter as guest <a href="../showItems.jsp">I'm guest</a></p>
            </div>
        </div>
    </div>
</div>


<script src="https://unpkg.com/vue@2.2.6"></script>
<script src="login.js"></script>
</body>

</html>