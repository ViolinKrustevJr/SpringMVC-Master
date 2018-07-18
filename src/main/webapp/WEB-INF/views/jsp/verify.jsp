<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Verification page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="<c:url value="/css/post.css" />" rel="stylesheet">
<link href="<c:url value="/css/upload.css" />" rel="stylesheet">
<base href="http://localhost:8080/9gag.com/">
</head>
<body>

	<c:import url="navbar.jsp"></c:import>
	<div class="container-fluid text-center">
		<div class="row content">
			<c:import url="left-sidebar.jsp"></c:import>
			<div class="col-sm-8 text-center">

				<div style="padding-left: 40%; padding-top: 15%">
				<h5 style="color: red">
					<i>${error}</i>
				</h5>
					<form class="form-horizontal" action="activate" method="post">
						<fieldset>

							<!-- Form Name -->
							<legend><h2>Activate account</h2></legend>

							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="textinput">Enter&nbsp;username:&nbsp;</label>
								<div class="col-md-4">
									<input id="textinput" type="text" placeholder="Username"
										class="form-control input-md" name="username">
								</div>
							</div>

							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="textinput">Enter&nbsp;code:&nbsp;</label>
								<div class="col-md-4">
									<input id="textinput" type="text" placeholder="Activation code"
										class="form-control input-md" name="code">
								</div>
							</div>

							<!-- Button -->
							<div class="form-group">
								<div class="col-md-8" style="padding-left: 36%">
									<button id="btnsave" name="btnsave" class="btn btn-success">Activate</button>
								</div>
							</div>

						</fieldset>
					</form>
				</div>
			</div>
			<c:import url="right-sidebar.jsp"></c:import>
		</div>
	</div>

	<script src="<c:url value="/js/post.js" />"></script>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>