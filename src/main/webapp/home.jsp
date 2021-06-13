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
					<h1 class="page-header">Twitter Analysis</h1>
					<div class="alert alert-info"
						style="background-color: #f9f9f9; border: 1px solid #ddd; color: #444">
						A Project by <b><i>Kiran Raj, </i></b><b><i>Sanjana S
								Krishna, </i></b><b><i>Ananthu Krishnan, </i></b><b><i>Sangeetha,
						</i></b><b><i>Anurag Chowdhary, </i></b>
					</div>
				</div>

				<div class="col-lg-10 col-md-offset-1">
					<div class="row">
						<c:forEach items="${tables}" var="listValue">
						<div class="col-lg-4 dashboard-box">
							<a href="/list/${listValue}">
								<div class="inner-dashboard-box">
									<i class="fa fa-fw fa-database"></i>
									<h5>${listValue}</h5>
								</div>
							</a>
						</div>
						</c:forEach>
						
						
						<div class="col-lg-4 dashboard-box">
							<a href="/create">
								<div class="inner-dashboard-box">
									<i class="fa fa-fw fa-plus"></i>
									<h3>Add Table</h3>
								</div>
							</a>
						</div>
						
					</div>
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