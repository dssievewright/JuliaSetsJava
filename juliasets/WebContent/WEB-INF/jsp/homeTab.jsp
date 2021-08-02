<%@ include file="includeTags.jsp" %>

<h2>Julia Set Image Creator</h2>


<div id="juliaSetPictureContainer">
	<img alt="Generated Julia Set" class="img-fluid" src="${pageContext.request.contextPath}/images/test.png">
</div>


<form class="needs-validation" id="juliaParamsForm" novalidate>
	<div class="row g-3">
		<div class="col-12 col-md-6 col-lg-4 juliaParam" data-js-type="dec">
			<label class="form-label" for="realComponent">Real Component</label>
			<input id="realComponent" class="form-control" value="-0.4" type="number" step="0.01">
			<div class="invalid-feedback" id="invalidRealComponent"></div>
		</div>
		
		<div class="col-12 col-md-6 col-lg-4 me-auto juliaParam" data-js-type="dec">
			<label class="form-label" for="imaginaryComponent">Imaginary Component</label>
			<input id="imaginaryComponent" class="form-control" value="0.6" type="number" step="0.01">
			<div class="invalid-feedback" id="invalidImaginaryComponent"></div>
		</div>
		
    	<div class="col-auto col-lg-3 align-self-end">
			<button type="button" class="btn btn-primary" id="generateJuliaSet">Generate Julia Set</button>
		</div>
	</div>
	
	<!-- TODO: have show/hide extra options -->
	<!-- TODO: include colors options -->
	
	<div class="row g-3">
		<div class="col-12 col-md-6 col-lg-4">
			<div class="form-label">Real Axis Limits</div>
			<div class="input-group">
				<div class="juliaParam" data-js-type="dec">
					<label class="form-label visually-hidden" for="minXValue">Left Edge</label>
					<input id="minXValue" class="form-control" value="-2.0" type="number" step="0.01">
					<div class="invalid-feedback" id="invalidMinXValue"></div>
				</div>
				<span class="input-group-text" id="realLabelMin">\(\leq \mathrm{Re}(z) \leq\)</span>
				<div class="juliaParam" data-js-type="dec">
					<label class="form-label visually-hidden" for="maxXValue">Right Edge</label>
					<input id="maxXValue" class="form-control" value="2.0" type="number" step="0.01">
					<div class="invalid-feedback" id="invalidMaxXValue"></div>
				</div>
			</div>
		</div>
		
		<div class="col-12 col-md-6 col-lg-4">
			<div class="form-label">Imaginary Axis Limits</div>
			<div class="input-group">
				<div class="juliaParam" data-js-type="dec">
					<label class="form-label visually-hidden" for="minYValue">Bottom Edge</label>
					<input id="minYValue" class="form-control" value="-2.0" type="number" step="0.01">
					<div class="invalid-feedback" id="invalidMinYValue"></div>
				</div>
				<span class="input-group-text" id="imaginaryLabelMin">\(\leq \mathrm{Im}(z) \leq\)</span>
				<div class="juliaParam" data-js-type="dec">
					<label class="form-label visually-hidden" for="maxYValue">Top Edge</label>
					<input id="maxYValue" class="form-control" value="2.0" type="number" step="0.01">
					<div class="invalid-feedback" id="invalidMaxYValue"></div>
				</div>
			</div>
		</div>
		
		<div class="col-12 col-md-6 col-lg-4">
			<div class="form-label">Resolution</div>
			<div class="input-group">
				<div class="juliaParam" data-js-type="int">
					<label class="form-label visually-hidden" for="pictureWidth">Width</label>
					<input id="pictureWidth" class="form-control" value="480" type="number" min="1" max="10000" step="1">
					<div class="invalid-feedback" id="invalidWidth"></div>
				</div>
				<span class="input-group-text">x</span>
				<div class="juliaParam" data-js-type="int">
					<label class="form-label visually-hidden" for="pictureHeight">Height</label>
					<input id="pictureHeight" class="form-control" value="480" type="number" min="1" max="10000" step="1">
					<div class="invalid-feedback" id="invalidHeight"></div>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="row g-3">
		<div class="col-12 col-md-6 col-lg-4 juliaParam" data-js-type="int">
			<label class="form-label" for="iterations">Iterations</label>
			<input id="iterations" class="form-control" value="100" type="number" min="1" max="10000" step="1">
			<div class="invalid-feedback" id="invalidIterations"></div>
		</div>
		
		<div class="col-12 col-md-6 col-lg-4 juliaParam" data-js-type="int">
			<label class="form-label" for="maxModulus">Upper Bound</label>
			<input id="maxModulus" class="form-control" value="1000" type="number" step="1" min="1">
			<div class="invalid-feedback" id="invalidMaxModulus"></div>
		</div>
	</div>
	

</form>