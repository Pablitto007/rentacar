<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Cars</title>
</head>
<body>
	<div class="container">
		<div class="col-md-8">
			<h2>Cars available</h2>
		</div>
		<div class="col-md-2 col-md-offset-9">
			<a href="<spring:url value="/" />" class="btn btn-info btn-block"
				role="button"> <span class="glyphicon-hand-left glyphicon">
			</span>Home Page
			</a>
		</div>
		<div class="col-md-8">
			<h4>Click on car to see details</h4>
		</div>
	</div>

	<section class="container">
	<div class="row">
		<c:forEach items="${cars}" var="car">
			<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
				<div class="thumbnail">
					<img src="<c:url value="/resources/images/${car.vin}.jpg"></c:url>"
						alt="image" style="width: 100%" />

					<div class="caption">
						<h3>${car.brand}</h3>
						<h4>${car.model}</h4>
						<p>Year Of Manufacture: ${car.yearOfManufacture}</p>
						<p>Price per day: ${car.pricePerDay} PLN</p>
						<p>Equipment: ${car.description}</p>
						<p>
							<a href=" <spring:url value="/cars/car?id=${car.carId}" /> "
								class="btn btn-primary"> <span
								class="glyphicon-info-sign glyphicon"></span> Reservation
							</a>
						</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	</section>
</body>
</html>