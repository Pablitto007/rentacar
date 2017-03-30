<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Welcome</title>
</head>
<body>
	<section class="container">
		<h1>${greeting}</h1>

		<div class="row">
			<div class="col-md-8">
				<h4>${logged}</h4>
			</div>

			<sec:authorize access="isAnonymous()">
				<div class="col-md-2">
					<a href="<spring:url value="/login" />"
						class="btn btn-success btn-block" role="button">Login</a>
				</div>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<div class="col-md-2">
					<a href="<spring:url value="/logout" />"
						class="btn btn-danger btn-block" role="button">Logout</a>
				</div>
			</sec:authorize>

			<sec:authorize access="hasRole('ADMIN')">
				<div class="col-md-2">
					<a href="<spring:url value="/cars/add" />"
						class="btn btn-info btn-block" role="button">Admin panel</a>
				</div>
			</sec:authorize>
		</div>

		<div class="col-md-6">
			<h3>See our cars, Now new offer:</h3>
		</div>

		<div class="col-md-12">
			<img
				src="<c:url value="/resources/images/bannerWelcome.jpg"></c:url>"
				alt="image" style="width: 100%" />
		</div>

		<div class="col-md-6 col-md-offset-5">
			<h1>
				<a href="<spring:url value="/cars" />">Available Cars</a>
			</h1>
		</div>
	</section>
</body>
</html>
