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
<link href="<c:url value="/css/profile.css" />" rel="stylesheet">

<title>Profile</title>
</head>
<body>

	<c:import url="navbar.jsp"></c:import>
	<div class="container-fluid text-center">
		<div class="row content">
			<c:import url="left-sidebar.jsp"></c:import>
			<div class="col-sm-8 text-left"> 
				<div class="flex-row">
					<div class="flex-1"></div>
					<div align="center" style="padding-top: 5%; padding-right: 22%;">
						   <img src="img/${sessionScope.user.photo}" style="width: 150px; height: 150px; margin: 20px; border-radius: 50%"/>
							<div><h2><b>${sessionScope.user.username}</b></h2></div>
					<div class="flex-1"></div>
					</div>
				</div>
					
	<div id="flip-tabs">
		<ul id="flip-navigation">
			<li class="profile-tab selected" onclick="selectPosts()" id="postsBtn">
				<a id="tab-0" class="profile-tab">Posts</a>
			</li>
			<li class="profile-tab" id="votedBtn">
				<a id="tab-1" onclick="selectVoted()" class="profile-tab">Voted</a>
			</li>
			<li class="profile-tab" id="commentedBtn">
				<a id="tab-2" onclick="selectCommented()" class="profile-tab">Commented</a>
			</li>
		</ul>
		<hr style=" display: block; margin-top: 0.5em; margin-bottom: 0.5em; margin-left; margin-right; border-style: inset; border-width: 1px;">
		<div id="flip-container" style="align-content: center;">
			<div id="postsTab">
				<c:forEach var="post" items="${sessionScope.posts}">
						<tr class="post-content">
							<div class="tp post-content">
								<h2 onclick="openModel(${post.id})">${post.title}</h2>
							</div>
							<c:choose>
								<c:when test="${post.video == true}">
									<div>
										<video width="500" height="420" controls> <source
											src="img/${post.imageURL}" type="video/mp4"
											onclick="openModel(${post.id})" style="max-width: 600px;">
										<source src="img/${post.imageURL}" type="video/ogg"
											onclick="openModel(${post.id})" style="max-width: 600px;">
										Your browser does not support the video tag. </video>
									</div>
								</c:when>
								<c:otherwise>
									<div>
										<img src="img/${post.imageURL}"
											onclick="openModel(${post.id})" style="max-width: 600px;">
									</div>
									<br />
								</c:otherwise>
							</c:choose>
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
						</tr>
					</c:forEach>
			</div>
			<div id="votedTab" class="tab-hidden">
			<c:forEach var="post" items="${sessionScope.voted}">
						<tr class="post-content">
							<div class="tp post-content">
								<h2 onclick="openModel(${post.id})">${post.title}
									${post.id}</h2>
							</div>
							<c:choose>
								<c:when test="${post.video == true}">
									<div>
										<video width="500" height="420" controls> <source
											src="img/${post.imageURL}" type="video/mp4"
											onclick="openModel(${post.id})" style="max-width: 600px;">
										<source src="img/${post.imageURL}" type="video/ogg"
											onclick="openModel(${post.id})" style="max-width: 600px;">
										Your browser does not support the video tag. </video>
									</div>
								</c:when>
								<c:otherwise>
									<div>
										<img src="img/${post.imageURL}"
											onclick="openModel(${post.id})" style="max-width: 600px;">
									</div>
									<br />
								</c:otherwise>
							</c:choose>
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
						</tr>
					</c:forEach>
			</div>
			<div id="commentedTab" class="tab-hidden">
			<c:forEach var="post" items="${sessionScope.commented}">
						<tr class="post-content">
							<div class="tp post-content">
								<h2 onclick="openModel(${post.id})">${post.title}
									${post.id}</h2>
							</div>
							<c:choose>
								<c:when test="${post.video == true}">
									<div>
										<video width="500" height="420" controls> <source
											src="img/${post.imageURL}" type="video/mp4"
											onclick="openModel(${post.id})" style="max-width: 600px;">
										<source src="img/${post.imageURL}" type="video/ogg"
											onclick="openModel(${post.id})" style="max-width: 600px;">
										Your browser does not support the video tag. </video>
									</div>
								</c:when>
								<c:otherwise>
									<div>
										<img src="img/${post.imageURL}"
											onclick="openModel(${post.id})" style="max-width: 600px;">
									</div>
									<br />
								</c:otherwise>
							</c:choose>
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
						</tr>
					</c:forEach>
			</div>
		</div>
	</div>
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
	<script src="<c:url value="/js/profile.js" />"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>