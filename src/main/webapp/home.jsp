<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title>BigDataApplication</title>

</head>
<body>

	<h1>Welcome to UI application</h1>

	<div class="container-fluid">

		<table class="table">
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
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>




	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>

	<!-- Option 2: Separate Popper and Bootstrap JS -->
	<!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
    -->
</body>
</html>