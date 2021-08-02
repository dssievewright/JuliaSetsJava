<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includeTags.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Julia Sets</title>
				
		<%-- jQuery --%>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		
		<%-- Bootstrap --%>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
		
		<%-- MathJax --%>
		<script src="https://polyfill.io/v3/polyfill.min.js?features=es6"></script>
		<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-chtml.js">
		</script>	
			
		<%-- Custom js --%>
		<script>var contextPath = "${pageContext.request.contextPath}";</script>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/juliaSets.css"/>" /> 
		<script src="<c:url value="/js-min/juliaSets-min.js" />"></script>
	</head>
	<body >
		<h1 class="visually-hidden"><span class="sr-only">Julia Sets</span></h1>
		<a class="visually-hidden-focusable" href="#content" id="skipNav">Skip to main content</a>
		<jsp:include page="navBar.jsp"/>
		<div class="container-fluid">
			<div class="tab-content" id="content">
				<div id="homeTab" class="tab-pane active mainNavPane" role="tabpanel" aria-labelledby="home-nav">
					<jsp:include page="homeTab.jsp"/>
				</div>
				<div id="aboutTab" class="tab-pane mainNavPane" role="tabpanel" aria-labelledby="about-nav">
					<jsp:include page="aboutTab.jsp"/>
				</div>
				<div id="apiTab" class="tab-pane mainNavPane" role="tabpanel" aria-labelledby="api-nav">
					<jsp:include page="apiTab.jsp"/>
				</div>
				<div id="moreInfoTab" class="tab-pane mainNavPane" role="tabpanel" aria-labelledby="moreInfo-nav">
					<jsp:include page="moreInfoTab.jsp"/>
				</div>
			</div>
		</div>
	</body>
</html>