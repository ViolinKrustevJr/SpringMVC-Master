<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="<c:url value="/css/post.css" />" rel="stylesheet">
<link href="<c:url value="/css/upload.css" />" rel="stylesheet">
<base href="http://localhost:8080/9gag.com/">
<title>Videos</title>
</head>
<body>

	<c:import url="navbar.jsp"></c:import>

	<div class="container-fluid text-center">
		<div class="row content">
			<c:import url="left-sidebar.jsp"></c:import>
			<div class="col-sm-8 text-center">
				<c:forEach var="post" items="${videos}">
						<div class="tp">
							<h2 onclick="openModel(${post.id})">${post.title}${post.id}
							</h2>
						</div>
						<div>
							<video width="500" height="420" controls> <source
								src="img/${post.imageURL}" type="video/mp4"
								onclick="openModel(${post.id})" style="max-width: 600px;">
							<source src="img/${post.imageURL}" type="video/ogg"
								onclick="openModel(${post.id})" style="max-width: 600px;">
							Your browser does not support the video tag. </video>
						</div>
						<div id="${post.id}" onclick="countPoints(${post.id})">
							click to see points</div>
						<div id="${post.id}+c"></div>
						<div class="tf">
							<button class="L mainButton" onclick="upvotePost(${post.id})">
								<span class="glyphicon glyphicon-thumbs-up"></span>
							</button>
							<button class="D mainButton" onclick="downvotePost(${post.id})">
								<span class="glyphicon glyphicon-thumbs-down"></span>
							</button>
							<button class="C mainButton" onclick="openModel(${post.id})">
								<span class="glyphicon glyphicon-pencil"></span>
							</button>
						</div>
					
				</c:forEach>
			</div>
			<c:import url="right-sidebar.jsp"></c:import>
		</div>
	</div>
	<div class="model" id="simpleModel">
		<div id="2" class="model-content">
			<div class="right">
				<div class="model-header">
					<span class="closeBtn">&times;</span>
					<h2 id="title"></h2>
				</div>
				<div class="model-comments"></div>
			</div>
			<div class="left">
				<div class="content-post">
					<img id="pic" width="500" height="500">
				</div>
				<div class="model-footer">
					<button class="L mainButton" onclick="upvotePost(${post.id})">
						<span class="glyphicon glyphicon-thumbs-up"></span>
					</button>
					<button class="D mainButton" onclick="downvotePost(${post.id})">
						<span class="glyphicon glyphicon-thumbs-down"></span>
					</button>
					<button class="C mainButton" onclick="openModel(${post.id})">
						<span class="glyphicon glyphicon-pencil"></span>
					</button>
				</div>
			</div>
		</div>
	</div>

	<script src="<c:url value="/js/post.js" />"></script>
	<script src="<c:url value="/js/upload.js" />"></script>
	<script src="<c:url value="/js/search.js" />"></script>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>