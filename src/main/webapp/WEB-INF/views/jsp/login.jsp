<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/css/login.css" />" rel="stylesheet">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

<c:import url="navbar.jsp"></c:import>
<div class="container">
    <div class="row">
        <div class="col-md-12">
      <h5 style="color: red"><i>${error}</i></h5>
      <h5 style="color: blue"><i>${success}</i></h5>
            <div class="wrap">
                <p class="form-title">
                    Sign In</p>
                <form class="login" action="login" method="post">
                <input type="text" placeholder="Username"  name="username"/>
                <input type="password" placeholder="Password"  name="password"/>
                <input type="submit" value="Sign In" class="btn btn-success btn-sm" />
               <p style="color: silver;padding: 20px"><i>Don't&nbsp;have&nbsp;an&nbsp;account?</i><a href="showRegister"><b>&nbsp;Register&nbsp;here</b></a></p>
                </form>
            </div>
        </div>
    </div>
</div>


<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="<c:url value="/js/login.js" />"></script>
</body>
</html>