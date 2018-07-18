<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<title>Settings</title>
</head>
<body>

	<c:import url="navbar.jsp"></c:import>

	<div class="container-fluid text-center">
		<div class="row content">
			<c:import url="left-sidebar.jsp"></c:import>
			<div class="col-sm-8 text-center">
				<h5 style="color: red">
					<i>${error}</i>
				</h5>
				<h5 style="color: blue">
					<i>${success}</i>
				</h5>
				<div style="padding-left: 40%; padding-top: 5%">
					<form class="form-horizontal" action="update" method="post"
						enctype="multipart/form-data">
						<fieldset>

							<!-- Form Name -->
							<legend><h2>Settings</h2></legend>

							<div align="center">
								<img src="img/${sessionScope.user.photo}"
									style="width: 150px; height: 150px; border-radius: 50%">
							</div>
							<div>
								<input type="file" name="avatar" style="padding-left: 30%; padding-top: 5%; padding-bottom: 2%">
							</div>

							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="textinput">First&nbsp;name&nbsp;</label>
								<div class="col-md-4">
									<input id="textinput" type="text" placeholder="user id"
										class="form-control input-md" name="firstName"
										value="${sessionScope.user.firstName}">
								</div>
							</div>

							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="textinput">Last&nbsp;name&nbsp;</label>
								<div class="col-md-4">
									<input id="textinput" type="text" placeholder="your name"
										class="form-control input-md" name="lastName"
										value="${sessionScope.user.lastName}">
								</div>
							</div>

							<div style="padding-right: 14%; padding-bottom: 2%">
								<label>Gender&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <select
									style="width: 180px; height: 30px" name="gender">
									<c:forEach var="gender" items="${sessionScope.genders}">
										<c:choose>
											<c:when test="${gender.id == sessionScope.user.genderId}">
												<option selected="selected" value="${gender.id}">${gender.type}</option>
											</c:when>
											<c:otherwise>
												<option value="${gender.id}">${gender.type}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>

							<div style="padding-right: 15%; padding-bottom: 2%">
								<label>Country&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <select
									style="width: 180px; height: 30px" name="country">
									<c:forEach var="country" items="${sessionScope.countries}">
										<c:choose>
											<c:when test="${country.id == sessionScope.user.countryId}">
												<option selected="selected" value="${country.id}">${country.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${country.id}">${country.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>

							<div style="padding-right: 18%; padding-bottom: 2%;">
								<label>Biography&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<textarea name="biography" rows="5" cols="22"
									style="resize: none;">${sessionScope.user.biography}</textarea>
							</div>
							<!-- Button -->
							<div class="form-group">
								<div class="col-md-8" style="padding-left: 36%">
									<button id="btnsave" name="btnsave" class="btn btn-success">Save&nbsp;Changes</button>
								</div>
							</div>

						</fieldset>
					</form>

					<form action="deleteAccount" method="post">
						<input type="submit" value="Delete account" style="background-color: red; 
								color: white; border-radius: 4px; border-style: none;padding-top: 6px;
								padding-right: 12px; padding-bottom: 6px; padding-left: 12px; "/>
					</form>
				</div>
			</div>
			<c:import url="right-sidebar.jsp"></c:import>
		</div>
	</div>

<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>