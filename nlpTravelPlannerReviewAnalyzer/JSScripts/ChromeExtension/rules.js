var TEMPORAL_EXTRACTION_URL = "https://localhost:8443/timeextractor/";
var TEMPORAL_EXTRACTION_SERVICE_URL = TEMPORAL_EXTRACTION_URL + "api/rules"
var METHOD_GET = "GET";
var CONTENT_TYPE = "application/json"
var DATA_TYPE = 'json'

var start = function() {
	$.when(rules()).then(function(data, textStatus, jqXHR) {
		console.log(data);
	}).fail(function(data, textStatus, jqXHR) {
		alert("An error occured on server: " + jqXHR);
	});
}

// get rule ids
var rules = function() {
	return $.ajax({
		type : METHOD_GET,
		url : TEMPORAL_EXTRACTION_SERVICE_URL,
		crossDomain : true,
		contentType : CONTENT_TYPE,
		dataType : DATA_TYPE,
	});
}

$(document).ready()
{
	start();
}
