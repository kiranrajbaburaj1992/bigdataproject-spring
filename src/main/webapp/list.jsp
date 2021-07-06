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
						<button disabled="disabled" class="btn btn-success" id="myBtn"
							onclick='drawGraphs();'
							style="float: right; margin-right: 10px;">Visualize</button>
					</div>
				</div>

				<!-- The Modal -->
				<div id="myModal" class="modal">
					<!-- Modal content -->
					<div class="modal-content" style="display: flex;">
						<span class="close">&times;</span>
						<div style="display: inline-block; width: 100%;">
							<div id="myBarChart"
								style="height: 500px; width: 50%; float: left;"></div>
							<div id="myPieChart"
								style="height: 500px; width: 50%; float: left;"></div>
							<div id="myLineChart"
								style="width: 50%; height: 500px; float: left;"></div>
							<div id="myScatterChart" style="width: 50%; height: 500px; float: left;"></div>
						</div>
					</div>
				</div>


				<div class="col-lg-12">
					<div class="table-responsive" style="overflow-x: scroll;">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th scope="col"></th>
									<c:forEach items="${lists[0]}" var="entry">
										<th scope="col"><input type="checkbox"
											onchange='handlexyChange(this,"${entry.key}")' name="xyaxis" />
											${entry.key}</th>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<!--<c:if test="${not state.first}">-->
								<!--</c:if>-->
								<c:forEach items="${lists}" var="map" varStatus="state">
									<tr>
										<c:forEach var="entry" items="${map}" varStatus="_status">
											<c:if test="${_status.count eq 1}">
												<c:set var="rowidval" scope="application"
													value="${entry.value}" />
												<td><a href="/delete/${tablename}/${entry.value}"
													class="btn btn-xs btn-danger"><i
														class="fa fa-fw fa-trash-o"></i></a></td>
											</c:if>
										</c:forEach>

										<c:forEach items="${map}" var="entry">
											<td>
												<form action="/put/${tablename}" method="POST">
													<input type="text" value="${entry.value}" name="cfvalue"
														style="background: none; border: 0px; padding: 6px;"
														onblur="this.form.submit()" /> <input type="hidden"
														name="rowid" value="${rowidval}" /> <input type="hidden"
														name="cf" value="${entry.key}" />
												</form> <!--<c:out value="${entry.value}" />-->
											</td>
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
		ng-include="'/partials/footer.html'"
		ng-if="location.path() !== '/login'"></footer>

	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
	<script
		src="https://code.angularjs.org/1.3.0-rc.1/angular-route.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
	<script type="text/javascript"
		src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript" src="/js/website.js"></script>
	<script type="text/javascript" src="/js/jsgraph.js"></script>

	<script>
	
		function confirmDelete(form) {
			var r = confirm("Are you sure you want to delete this table?");
			if (r == true) {
				form.submit();
			} else {
				return;
			}
		}

		
		// Get the modal
		var modal = document.getElementById("myModal");

		// Get the button that opens the modal
		var btn = document.getElementById("myBtn");

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks the button, open the modal 
		// btn.onclick = function() {
		//modal.style.display = "block";
		// }

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}


		var xAxis = null;
		var yAxis = null;

		function handlexyChange(checkbox,keyVal) {
		    if(checkbox.checked == true){
		        if(xAxis==null){
		        	xAxis = keyVal;
			        btn.disabled = false;
		        }
		        else{
			        yAxis = keyVal;
			        btn.disabled = false;
		        }
		    }else{
		        btn.disabled = false;
		    	if(yAxis!=null)
		        	yAxis = null;
		        else
			        xAxis = null;
		   }
		}


		

		google.charts.load('current', {
			'packages' : [ 'corechart' ]
		});
		//google.charts.setOnLoadCallback(drawChart);
		function drawGraphs(arr) {
			var arr = ${jsonArray};
			var xaxislabel = xAxis;
			var yaxislabel = yAxis;
			var graphArr = [[xaxislabel, yaxislabel]];
			for (var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				var xaxis = obj[xaxislabel];
				var yaxis = parseInt(obj[yaxislabel]);
				graphArr.push([xaxis,yaxis]);
			}
			modal.style.display = "block";

			var data = google.visualization.arrayToDataTable(graphArr);

			var options = {
				title : 'X-Axis: '+xaxislabel+' , Y-Axis: '+yaxislabel
			};

			var barChart = new google.visualization.BarChart(document
					.getElementById('myBarChart'));
			barChart.draw(data, options);

			var pieChart = new google.visualization.PieChart(document.getElementById('myPieChart'));
			pieChart.draw(data, options);

			var lineChart = new google.visualization.LineChart(document.getElementById('myLineChart'));
			lineChart.draw(data, options);

			var scatterChart = new google.visualization.ScatterChart(document.getElementById('myScatterChart'));
			scatterChart.draw(data, options);
		}
	</script>
</body>
</html>