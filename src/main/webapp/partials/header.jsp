<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-default navbar-static-top" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<img src="/images/logo-cp.png" />
	</div>

	<ul class="nav navbar-top-links navbar-right">

		<li class="dropdown"><a class="logout-btn" href="/"> <i
				class="fa fa-home fa-bw"></i> Home
		</a></li>
	</ul>
	<div class=" sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<c:forEach items="${tables}" var="listValue">
					<li><a href="/list/${listValue}"><i class="fa fa-fw fa-database"></i>${listValue}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</nav>


<script src="js/lang.js"></script>
<script src="packages/sleeping-owl/admin/all.min.js"></script>