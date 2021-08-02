<%@ include file="includeTags.jsp" %>

<header class="container-fluid navbar-dark" style="background:#1a0354">
	<nav class="navbar navbar-expand-md navbar-dark" id="headerNav">
	    <a class="navbar-brand" href="#">Julia Sets</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNavbar" aria-controls="mainNavbar" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="mainNavbar">
	      	<ul class="navbar-nav nav" id="myTab" role="tablist">
	      		<li class="nav-item" role="presentation">
				   	<a class="nav-link active mainNavLink" role="tab" tabindex="0" id="home-nav" data-bs-toggle="tab" data-bs-target="#homeTab" aria-controls="homeTab" aria-selected="true">Home</a>
					</li>
				  	<li class="nav-item" role="presentation">
				    	<a class="nav-link mainNavLink" role="tab" tabindex="0" id="about-nav" data-bs-toggle="tab" data-bs-target="#aboutTab" aria-controls="aboutTab" aria-selected="false">About Julia Sets</a>
					</li>
					<li class="nav-item" role="presentation">
				   	<a class="nav-link mainNavLink" role="tab" tabindex="0" id="api-nav" data-bs-toggle="tab" data-bs-target="#apiTab" aria-controls="apiTab" aria-selected="false">API</a>
					</li>
					<li class="nav-item" role="presentation">
						<a class="nav-link mainNavLink" role="tab" tabindex="0" id="moreInfo-nav" data-bs-toggle="tab" data-bs-target="#moreInfoTab" aria-controls="moreInfoTab" aria-selected="false">More Info</a>
					</li>
				</ul>
	    </div>
	</nav>
</header>