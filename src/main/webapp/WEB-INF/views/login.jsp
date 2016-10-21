<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Login</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Login</h1>
			</div>
		</div>
	</section>
<div class="container">
    <div class="row">
		<div class="col-md-4 col-md-offset-4">
    		<div class="panel panel-default">
			  	<div class="panel-heading">
			    	<h3 class="panel-title">Login</h3>
			 	</div>
			  	<div class="panel-body">
			  	<c:if test="${not empty error}">
					<div class="alert alert-danger">
						Incorrect login or password
					</div>
				</c:if>
				<c:url var="loginUrl" value="/login"/> 
			    	<form action="${loginUrl}" method="post">
                    <fieldset>
			    	  	<div class="form-group">
			    		    <input class="form-control" placeholder="Email" name='email' type="text">
			    		</div>
			    		<div class="form-group">
			    			<input class="form-control" placeholder="Password" name='password'  type="password" value="">
			    		</div>
			    		<input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
			    		<a href="/rentacar/registration" class="btn btn-info btn-block">Registration</a>
			    	</fieldset>
			      	</form>
			    </div>
			</div>
		</div>
	</div>
</div>
</body>
