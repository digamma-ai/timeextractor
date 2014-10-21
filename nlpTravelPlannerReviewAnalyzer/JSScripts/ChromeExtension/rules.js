var TEMPORAL_EXTRACTION_URL = "http://localhost:8080/timeextractor/";
var TEMPORAL_EXTRACTION_SERVICE_URL = TEMPORAL_EXTRACTION_URL + "api/rules"
var METHOD_GET = "GET";
var CONTENT_TYPE = "application/json"
var DATA_TYPE = 'json'

$(document).ready()
{
	var el = document.getElementById("load_rules");
	if (el) {
		el.addEventListener("click", getAllRules);
	}
};

function getAllRules() {
	$.when(rules()).then(function(data, textStatus, jqXHR) {
		addTable(data);
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

function addTable(rules) {
	var myTableDiv = document.getElementById("rules")
	var table = document.createElement('TABLE')
	var tableBody = document.createElement('TBODY')

	table.border = '1'
	table.appendChild(tableBody);

	var heading = new Array();
	heading[0] = ""
	heading[1] = "Group Types"
	heading[2] = "Examples"

	// TABLE COLUMNS
	var tr = document.createElement('TR');
	tableBody.appendChild(tr);
	for (i = 0; i < heading.length; i++) {
		var th = document.createElement('TH')
		th.width = '75';
		th.appendChild(document.createTextNode(heading[i]));
		tr.appendChild(th);
	}
	// TABLE ROWS
	console.log(rules);
	for ( var propt in rules) {
		var tr = document.createElement('TR');
		var td = document.createElement('TD');
		var x = document.createElement("INPUT");
		x.setAttribute("type", "checkbox");
		x.setAttribute("id", propt);
		td.appendChild(x);
		tr.appendChild(td);
		var cell = document.createElement("td");
		var cellText = document.createTextNode(rule_group_name(propt));
		cell.appendChild(cellText);
		tr.appendChild(cell);
		tableBody.appendChild(tr);
	}
	myTableDiv.appendChild(table)
}

var rule_group_name = function(group_name) {
	switch (group_name) {
	case "com.codeminders.labs.timeextractor.rules.timeofday":
		return "Time of day";
	case "com.codeminders.labs.timeextractor.rules.duration":
		return "Duration";
	case "com.codeminders.labs.timeextractor.rules.frequency":
		return "Frequency";
	case "com.codeminders.labs.timeextractor.rules.date":
		return "Date";
	case "com.codeminders.labs.timeextractor.rules.duration.interval":
		return "Duration Interval";
	case "com.codeminders.labs.timeextractor.rules.duration.interval":
		return "Duration Interval";
	case "com.codeminders.labs.timeextractor.rules.date.relative":
		return "Relative dates";
	case "com.codeminders.labs.timeextractor.rules.time":
		return "Time";
	case "com.codeminders.labs.timeextractor.rules.dateinterval":
		return "Date interval";
	case "com.codeminders.labs.timeextractor.rules.repeated":
		return "Repeated";
	case "com.codeminders.labs.timeextractor.rules.date.dayofweek":
		return "Day of week";
	case "com.codeminders.labs.timeextractor.rules.season":
		return "Season";
	case "com.codeminders.labs.timeextractor.rules.timezone":
		return "Timezone";
	case "com.codeminders.labs.timeextractor.rules.timeinterval":
		return "Time intervals";
	default:
		return group_name;
	}

}
