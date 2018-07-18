<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="<c:url value="/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/css/index.css" />" rel="stylesheet">
<link href="<c:url value="/css/upload.css" />" rel="stylesheet">

<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#myNavbar">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a class="navbar-brand"><span
			style="color: #AB0707; font-size: 22px;"><b>FUN</b></span><span
			style="color: white; font-size: 22px;"><i>FACTORY</i></span></a>
	</div>
	<div class="collapse navbar-collapse" id="myNavbar">
		<ul class="nav navbar-nav">
			<li><a href="/9gag.com/">Home</a></li>
			<li><a href="videos">Videos <span
					class="glyphicon glyphicon-film"></span></a></li>
			<form class="navbar-form navbar-left" action="/action_page.php">
				<div class="input-group">
					<input type="text" class="form-control" id="search" autocomplete="off"
						placeholder="Search">
					<div id="autocomplete-results"></div>
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</form>
		</ul>
		<c:choose>
			<c:when test="${sessionScope.user != null}">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<div class="dropdown" style="display: inline-block">
							<img src="img/${sessionScope.user.photo}" class="img-circle"
								id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu">
								<li><a class="dropdown-item"
									onclick="location.href='profile'">Profile</a></li>
								<li><a class="dropdown-item"
									onclick="location.href='showSettings'">Settings</a></li>
								<li><a class="dropdown-item"
									onclick="location.href='logout'">Log out</a></li>
							</ul>
						</div>
					</li>
					<li><button id="uploadButton"
							class="btn btn-danger navbar-btn" onclick="openModelPost()">+
							Upload</button></li>
				</ul>

			</c:when>
			<c:otherwise>
				<ul class="nav navbar-nav navbar-right">
					<li><a onclick="location.href='showlogin'"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
					<li><a onclick="location.href='showRegister'"><span
							class="glyphicon glyphicon-user"></span> Sign Up</a></li>
				</ul>
			</c:otherwise>
		</c:choose>
	</div>
</div>
</nav>

<div class="model-upload-p" id="simpleModelPost">
	<div class="upload-post" id="upload-post">
		<div class="upload-header">
			<span id="close1" class="closeBtnPost">&times;</span>
			<h2>Upload a Post</h2>
		</div>
		<div class="upload-img" id="upload-img">
			<h3>Upload Image</h3>
		</div>
		<div class="upload-video" id="upload-video">
			<h3>Upload Video</h3>
		</div>
	</div>
	<div class="upload-model" id="upload-model">
		<div class="upload-header">
			<span id="close2" class="closeBtnPost">&times;</span> <img src=""
				id="currentPhoto">
			<form method="post" action="upload/post" id="ajax-upload"
				enctype="multipart/form-data">
				<table>
					<tr style="text-align: left">
						<td>File</td>
						<td><input type="file" name="file" accept=""
							onchange="handleFile(this.files)" required></td>
					</tr>
					<tr style="text-align: left">
						<td>Description</td>
						<td><textarea name="description" rows="5" colls="16" required></textarea></td>
					</tr>
					<tr style="text-align: left">
						<td>Tag</td>
						<td><input type="text" name="tag" required></td>
					</tr>
					<tr style="text-align: left">
						<td>Section</td>
						<td><select style="width: 179px; height: 30px" name="section"
							required>
								<c:forEach var="section" items="${sections}">
									<option value="${section.id}">${section.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr style="text-align: left">
						<td></td>
						<td><input type="submit" value="upload file"></td>
					</tr>

					<tr style="text-align: left; opacity: 0">
						<td>Type</td>
						<td><input type="text" name="type" id="typeRadio" required></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<script src="<c:url value="/js/upload.js" />"></script>
<script src="<c:url value="/js/post.js" />"></script>