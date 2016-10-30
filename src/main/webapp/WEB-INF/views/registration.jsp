<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Registration</title>
</head>
<body>
<div class="container">
  <h2>${register}</h2>
  <div class="btn-group btn-group-justified">
    <a href="<spring:url value="/registration"/>" class="btn btn-info">Person</a>
    <a href="<spring:url value="/registrationCompany" />" class="btn btn-default">Company</a>
  </div>
</div>

<section class="container">
		<form:form modelAttribute="newCustomerPerson" class="form-horizontal" method="post">
			<fieldset>
				<legend>Register Person Form</legend>
				
					<form:errors path="*" cssClass="alert alert-danger" element ="div"/>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="email">E-
					<spring:message code="registration.form.email.label"/>
					</label>
					<div class="col-lg-8">
						<form:input id="email" path="email" type="text"
							class="form-control"/>
							<form:errors path="email" cssClass="text-danger" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="password">Password</label>
					<div class="col-lg-8">
						<form:input id="password" path="password" type="password"
							class="form-control"/>
							<form:errors path="password" cssClass="text-danger" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="personName">Name</label>
					<div class="col-lg-8">
						<form:input id="personName" path="personName" type="text"
							class="form-control"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="personSurname">Surname</label>
					<div class="col-lg-8">
						<form:input id="personSurname" path="personSurname" type="text"
							class="form-control"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="streetAddress">Address</label>
					<div class="col-lg-8">
						<form:input id="streetAddress" path="streetAddress" type="text"
							class="form-control"/>
							<form:errors path="streetAddress" cssClass="text-danger" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="postalCode">Postal Code</label>
					<div class="col-lg-8">
						<form:input id="postalCode" path="postalCode" placeholder="00-000" type="text"
							class="form-control"/>
							<form:errors path="postalCode" cssClass="text-danger" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="city">City</label>
					<div class="col-lg-8">
						<form:input id="city" path="city" type="text"
							class="form-control"/>
							<form:errors path="city" cssClass="text-danger" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="idCard">Id Card number</label>
					<div class="col-lg-8">
						<form:input id="idCard" path="idCard" type="text"
							class="form-control"/>
					</div>
				</div>
					
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
					<input type="submit" id="btnAdd" class="btn btn-primary" value="Register"/>
					</div>
				</div>
				
				
			</fieldset>
		</form:form>
	</section>
	
	<div class="col-md-2 col-md-offset-7">
			<a href="<spring:url value="/" />" class="btn btn-info btn-block"
				role="button"> <span class="glyphicon-hand-left glyphicon">
			</span>Home Page
			</a>
		</div>

</body>
</html>
