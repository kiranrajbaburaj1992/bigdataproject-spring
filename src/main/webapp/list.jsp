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
					 	<h1 class="page-header"></h1>
					    <div class="alert alert-info" style="background-color: #f9f9f9;border: 1px solid #ddd;color: #444;overflow: auto;padding: 12px;">
        					<h4 style="float: left;margin-top: 8px;">Table : ${tablename}</h4>
        					<a class="btn btn-primary" href="/add" style="float: right;">Add a row</a>
    					</div>
				</div>
				<div class="col-lg-12">
					<div class="table-responsive">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th scope="col">Rowkey #id</th>
									<th scope="col">screen_name</th>
									<th scope="col">name</th>
									<th scope="col">friends_count</th>
									<th scope="col">followers_count</th>
									<th scope="col">statuses_count</th>
									<th scope="col">verified</th>
									<th scope="col">utc_offset</th>
									<th scope="col">time_zone</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${lists}" var="listValue">
									<tr>
										<th scope="row"><c:out value="${listValue.id}" /></th>
										<td><c:out value="${listValue.screen_name}" /></td>
										<td><c:out value="${listValue.name}" /></td>
										<td><c:out value="${listValue.friends_count}" /></td>
										<td><c:out value="${listValue.followers_count}" /></td>
										<td><c:out value="${listValue.statuses_count}" /></td>
										<td><c:out value="${listValue.verified}" /></td>
										<td><c:out value="${listValue.utc_offset}" /></td>
										<td><c:out value="${listValue.time_zone}" /></td>
                                    	<td><a href="/delete/${tablename}/${listValue.id}" class="btn btn-danger"><i class="fa fa-fw fa-trash-o"></i></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
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