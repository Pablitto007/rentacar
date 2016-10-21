<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Car</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<h1>${car.brand} ${car.model}</h1>
		</div>
	</section>
	<section class="container">
		<form:form modelAttribute="newTransaction" class="form-horizontal"
			method="post">
			<form:errors path="*" cssClass="alert alert-danger" element="div" />
			<div class="row">
				<div class="col-md-5">
					<img src="<c:url value="/resources/images/${car.vin}.jpg"></c:url>"
						alt="image" style="width: 100%" />
				</div>
				<div class="col-sm-6 col-md-5 col-md-offset-1">
					<h3>${car.brand} ${car.model}</h3>

					<h5>
						<strong>VIN number: </strong>${car.vin}
					</h5>
					<p>
						<strong>Year Of Manufacture: </strong>${car.yearOfManufacture}</p>
					<p>
						<strong>Price per day: </strong>${car.pricePerDay} PLN
					</p>
					<p>
						<strong>Equipment: </strong>${car.description}</p>
					<p>
						<strong>Username: </strong>${username}</p>


					<div class="form-group">
						<label for="start" class="control-label col-md-5">Rent
							from (yyyy/MM/dd)</label>
						<div class="col-md-5">
							<form:input id="start" path="start" type="text"
								class="form-control" />
							<form:errors path="start" cssClass="text-danger" />
						</div>
					</div>

					<div class="form-group">
						<label for="end" class="control-label col-md-5">Rent
							to (yyyy/MM/dd)</label>
						<div class="col-md-5">
							<form:input id="end" path="end" type="text" class="form-control" />
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-2 col-md-offset-6">
					<input type="submit" id="btnAdd"
						class="btn btn-primary btn-block" value="Order now" />
				</div>


				<a href="<spring:url value="/cars" />" class="btn btn-warning" role="button">
					<span class="glyphicon-hand-left glyphicon"></span> back to list
				</a>

			</div>

		</form:form>
	</section>
</body>
</html>