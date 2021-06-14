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
					<div class="alert alert-info"
						style="background-color: #f9f9f9; border: 1px solid #ddd; color: #444; overflow: auto; padding: 12px;">
						<h4 style="float: left; margin-top: 8px;">Table :
							${tablename}</h4>
						<form action="/delete/${tablename}" method="GET">
							<input type="button" class="btn btn-danger"
								onclick="confirmDelete(this.form)" value="Delete Table"
								style="float: right; margin-left: 10px;"></input>
						</form>
						<a class="btn btn-primary" href="/put/${tablename}"
							style="float: right;">Add a row</a>
					</div>
				</div>
				<div class="col-lg-12">
					<div class="table-responsive">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th scope="col"></th>
									<c:forEach items="${lists[0]}" var="entry">
										<th scope="col">${entry.key}</th>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${lists}" var="map">
									<tr>
										<c:forEach var="entry" items="${map}" varStatus="_status">
											<c:if test="${_status.count eq 1}">
												<td><a href="/delete/${tablename}/${entry.value}"
													class="btn btn-xs btn-danger"><i
														class="fa fa-fw fa-trash-o"></i></a></td>
											</c:if>
										</c:forEach>

										<c:forEach items="${map}" var="entry">
											<td><c:out value="${entry.value}" /></td>
										</c:forEach>
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

	<script>

		function confirmDelete(form){
			var r = confirm("Are you sure you want to delete this table?");
			if(r==true){
				form.submit();
			} else{
				return;
			}
		}
		
	</script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
	<script
		src="https://code.angularjs.org/1.3.0-rc.1/angular-route.min.js"></script>
	<script type="text/javascript" src="/js/website.js"></script>
</body>
</html>