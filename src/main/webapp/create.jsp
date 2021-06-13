<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" ng-app="website">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Twitter Analysis</title>
<link media="all" type="text/css" rel="stylesheet"
	href="/css/all.min.css">
<link media="all" type="text/css" rel="stylesheet"
	href="/css/website.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<script src="/js/lang.js"></script>
</head>
<body>

	<jsp:include page="partials/header.jsp" />
	
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="row">

				<div class="col-lg-12">
					<h1 class="page-header">Create Table</h1>
					<div class="alert alert-info"
						style="background-color: #f9f9f9; border: 1px solid #ddd; color: #444">
						Enter the values</div>
				</div>
				<div class="col-lg-12">

					<form>
						<div class="form-group">
							<label for="exampleFormControlInput1">Email address</label> <input
								type="email" class="form-control" id="exampleFormControlInput1"
								placeholder="name@example.com">
						</div>
						<div class="form-group">
							<label for="exampleFormControlSelect1">Example select</label> <select
								class="form-control" id="exampleFormControlSelect1">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
							</select>
						</div>
						<div class="form-group">
							<label for="exampleFormControlSelect2">Example multiple
								select</label> <select multiple class="form-control"
								id="exampleFormControlSelect2">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
							</select>
						</div>
						<div class="form-group">
							<label for="exampleFormControlTextarea1">Example textarea</label>
							<textarea class="form-control" id="exampleFormControlTextarea1"
								rows="3"></textarea>
						</div>

						<div class="form-group">
							<button type="button" class="btn btn-primary">Add row</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>

	<footer id="footer" class="footer hidden-print"
		ng-include="'partials/footer.html'"
		ng-if="location.path() !== '/login'"></footer>

	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
	<script
		src="https://code.angularjs.org/1.3.0-rc.1/angular-route.min.js"></script>
	<script type="text/javascript" src="/js/website.js"></script>
</body>
</html>