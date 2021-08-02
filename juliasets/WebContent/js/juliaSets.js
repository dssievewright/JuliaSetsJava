//class to hold julia set parameters
class JuliaSetParams {
	constructor() {
		this.realComponent = $("#realComponent").val();
		this.imaginaryComponent = $("#imaginaryComponent").val();
		this.minXValue = $("#minXValue").val();
		this.maxXValue = $("#maxXValue").val();
		this.minYValue = $("#minYValue").val();
		this.maxYValue = $("#maxYValue").val();
		this.pictureWidth = $("#pictureWidth").val();
		this.pictureHeight = $("#pictureHeight").val();
		this.iterations = $("#iterations").val();
		this.maxModulus = $("#maxModulus").val();
	}

	// this method is used to compare two instances of this class
	// its primary use is to check if there are changes between the 
	// params for the current picture shown and the form params
	equals(other) {
		return (other
			&& other instanceof JuliaSetParams
			&& this.realComponent == other.realComponent
			&& this.imaginaryComponent == other.imaginaryComponent
			&& this.minXValue == other.minXValue
			&& this.maxXValue == other.maxXValue
			&& this.minYValue == other.minYValue
			&& this.maxYValue == other.maxYValue
			&& this.pictureWidth == other.pictureWidth
			&& this.pictureHeight == other.pictureHeight
			&& this.iterations == other.iterations
			&& this.maxModulus == other.maxModulus);
	}
}

// class to hold constraints to do client side validation before submitting
// a request to the server
class JuliaSetConstraints {
	constructor(jsonString) {
		//parse input
		let constraints = JSON.parse(jsonString);

		//sql constraints
		this.sqlIntMin = constraints.sqlIntMin;
		this.sqlIntMax = constraints.sqlIntMax;
		this.sqlDecLength = constraints.sqlDecLength;
		this.sqlDecPrecision = constraints.sqlDecPrecision;
		//computed sql fields
		this.maxDecIntDigits = this.sqlDecLength - this.sqlDecPrecision + 1;
		this.sqlDecMax = parseFloat("1e" + (this.maxDecIntDigits));
		this.sqlDecMin = -this.sqlDecMax;
		
		//Julia Picture and Window constraints
		this.iterationsLimit = constraints.iterationsLimit;
		this.modulusLimit = constraints.modulusLimit;
		this.resolutionLimit = constraints.resolutionLimit;
		//set related max fields where appropriate
		$("#iterations").attr("max", this.iterationsLimit);
		$("#maxModulus").attr("max", this.modulusLimit);
		$("#pictureWidth").attr("max", this.resolutionLimit);
		$("#pictureHeight").attr("max", this.resolutionLimit);
	}

	// checks that a decimal is valid (based on SQL limits)
	// this also will set error messages for the form item 
	isValidDecimal(juliaParam) {
		let jp = $(juliaParam);
		let jpFormItem = jp.find(".form-control");
		let jpErrorMsgLoc = jp.find(".invalid-feedback");

		if (!this.validDecimal(jpFormItem.val())) {
			jpFormItem.addClass("is-invalid");
			jpErrorMsgLoc.text("Please enter a number between -10^" + this.maxDecIntDigits + " and 10^" + this.maxDecIntDigits + " (exclusive).");
			//scroll as far down as possible to keep form item on page and show as much of error message as possible
			$('html, body').scrollTop(jpFormItem.closest(".row").offset().top);
			return false;
		}

		jpFormItem.removeClass("is-invalid");
		jpErrorMsgLoc.text("");
		return true;
	}

	// basic check to see if a string translates to a valid decimal for the DB
	validDecimal(stringValue) {
		let floatValue = parseFloat(stringValue);
		return stringValue != ""
			&& !isNaN(stringValue)
			&& floatValue > this.sqlDecMin
			&& floatValue < this.sqlDecMax;
	}
	
	isValidInt(juliaParam){
		let jp = $(juliaParam);
		let jpFormItem = jp.find(".form-control");
		let upperLimit = jpFormItem.attr("max");
		let jpErrorMsgLoc = jp.find(".invalid-feedback");
				
		if(!this.validatePositiveInt(jpFormItem.val(), upperLimit)){
			jpFormItem.addClass("is-invalid");
			jpErrorMsgLoc.text("Please enter a posivite integer whose value is at most " + upperLimit + ".");
			//scroll as far down as possible to keep form item on page and show as much of error message as possible
			$('html, body').scrollTop(jpFormItem.closest(".row").offset().top);
			return false;
		}
		
		jpFormItem.removeClass("is-invalid");
		jpErrorMsgLoc.text("");
		return true;
	}
	
	validatePositiveInt(stringValue, maximum){
		let floatValue = parseFloat(stringValue);
		let intValue = parseInt(stringValue);
		
		return stringValue != ""
			&& !isNaN(stringValue)
			&& intValue > 0
			&& intValue <= maximum
			&& floatValue == intValue;
	}
	
}

// initialize objects - the empty constructors are meaningless, but this
// allows for ctrl+click behavior in a nice javascript editor.  These will 
// be correctly repopulated after screen loads (see getConstraints and generateImage functions)
var currentImageValues = new JuliaSetParams(); // holds the params for the image shown on the screen
var juliaSetConstraints = new JuliaSetConstraints("{}"); // object to hold the server and DB constraints

$(document).ready(function() {
	getConstraints();

	//the Generate Julia Set button
	$("#generateJuliaSet").on("click", function() {
		generateImage();
	})

	// have enter key in nav tab behave like clicking
	$(".mainNavLink").on("keypress", function(event) {
		if (event.keyCode == 13) {// enter key
			this.click();
		}
	});

	// Skip Nav
	$("#skipNav").on("click", function(event) {
		skipNav(this, event);
	});

	//validations for changes in inputs
	$(".juliaParam").on("change", function() {
		validateJuliaParam(this);
	});
});

// processor for skip nav. "t" value is DOM element for skip nav and the href value
// contains the item we want to skip to.
// ideally should be handled by browser but I saw some slight inconsistencies
function skipNav(t, e) {
	let content = $(t).attr("href");
	$(content).focus();
	e.stopPropagation();
}

// function to get field level constraints. only called on page load
// also calls generateImage to verify that the image matches the current params
// on the screen
function getConstraints() {
	$.ajax({
		url: getContextPath() + "/getConstraints",
		type: "GET",
		success: function(result) {
			// set the constraints
			juliaSetConstraints = new JuliaSetConstraints(result);
			// regenerate image just in case it does not match params on screen
			generateImage();
		},
		error: function(error) {
			//TODO: externalize error Handling for uniformity
			console.log("error");
			console.log(error.responseText);
		}
	});
}

// function to handle client-side validation and generation of Julia Set images
function generateImage() {
	let newData = new JuliaSetParams();

	if (validateImageGeneration(newData)) {
		//TODO: set cursor to busy and various waiting stuff...
		
		$.ajax({
			url: getContextPath() + "/generateJuliaSet.html",
			type: "GET",
			contentType: "application/json",
			data: newData,
			success: function(result) {
				// replace the image
				$('#juliaSetPictureContainer').html(result);

				//now that it's processed, reset the current image values
				currentImageValues = newData;
			},
			error: function(error) {
				//TODO: externalize error Handling for uniformity
				console.log("error");
				console.log(error.responseText);
			}
		});
	}
}

// helper method for image set generation
// retuires newData be different from current image's data and 
// revalidates parameters.
function validateImageGeneration(newData) {
	return !newData.equals(currentImageValues) && allParamsValid();
}

// validates all parameters
function allParamsValid() {
	let isValid = true;

	$(".juliaParam").each(function() {
		// once a validation is false, isValid will always be false
		// but we still continue to validate other fields to display
		// all appropriate errors to user
		isValid = isValid && validateJuliaParam(this);
	});

	return isValid;
}

// validates a single Julia parameter
// called on any change of a field
function validateJuliaParam(param) {
	let dataType = $(param).attr("data-js-type");
	if (dataType == "dec") {
		return juliaSetConstraints.isValidDecimal(param);
	} else if (dataType == "int") {
		return juliaSetConstraints.isValidInt(param);
	}
	return true;
}

//getter for context path.  This is set in head tag in jsp on screen load
function getContextPath() {
	return contextPath;
}

// comment for my consideration: 
// I could disable and enable Generate Julia Set button when 
// any form item is invalid or they all become valid.
// I decided against this as it does no harm

// TODO: have enter key in form elements (including enter or click on submit button) 
// go to  first form element that is invalid.
// if all are valid, click the generate button
