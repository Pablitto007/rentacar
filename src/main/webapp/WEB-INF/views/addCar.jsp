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
<title>Add Car</title>
</head>
<body>
	<div class="container">
		<h2>Adding new car</h2>
		<p>Fill all required fields</p>
	</div>

	<section class="container"> <form:form
		modelAttribute="newCar" class="form-horizontal"
		enctype="multipart/form-data">
			<fieldset>
				<legend>Add new car</legend>

				<div class="form-group">
					<label class="control-label col-lg-2" for="vin">VIN</label>
					<div class="col-lg-8">
						<form:input id="vin" path="vin" type="text" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="brand"> Brand</label>
					<div class="col-lg-8">
						<form:input id="brand" path="brand" type="text"
							class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="model">Model</label>
					<div class="col-lg-8">
						<form:input id="model" path="model" type="text"
							class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="yearOfManufacture">Year</label>
					<div class="col-lg-8">
						<form:input id="yearOfManufacture" path="yearOfManufacture"
							type="text" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="pricePerDay">Price
						per day</label>
					<div class="col-lg-8">
						<form:input id="pricePerDay" path="pricePerDay" type="text"
							class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="description">Description</label>
					<div class="col-lg-8">
						<form:textarea id="description" path="description" type="text"
							rows="2" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="carImage"> Add
						Car Image </label>
					<div class="col-lg-10">
						<form:input id="carImage" path="carImage" type="file"
							class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary"
							value="Add" />
					</div>
				</div>
			</fieldset>
	</form:form> </section>
</body>
</html>