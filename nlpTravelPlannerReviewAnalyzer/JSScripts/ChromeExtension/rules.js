var TEMPORAL_EXTRACTION_URL = "http://ec2-54-81-15-231.compute-1.amazonaws.com:8080/timeextractor-2/";
var TEMPORAL_EXTRACTION_SERVICE_URL = TEMPORAL_EXTRACTION_URL + "api/rules"
var METHOD_GET = "GET";
var CONTENT_TYPE = "application/json"
var DATA_TYPE = 'json'
var allRules;
// Saves options to chrome.storage
function save_options() {
	var arrayOfRulesToBeIgnored = [];
	var connectionType = document.getElementById('connection').value;
	var rules = [];
	$('input[type=checkbox]').each(function() {
		var sThisVal = (this.checked ? $(this).val() : "");
		if (sThisVal == 'on') {
			rules.push($(this).attr('id'));
		}
	});
	var id = saveArrayOfRuleIds(rules);
	chrome.storage.sync.set({
		connectionType : connectionType,
		rules : rules,
		ruleIds : id
	}, function() {
		var status = document.getElementById('status');
		status.textContent = 'Options saved.';
		setTimeout(function() {
			status.textContent = '';
		}, 750);
	});
}

function restore_options() {
	chrome.storage.sync.get({
		connectionType : 'http',
	}, function(items) {
		document.getElementById('connection').value = items.connectionType;
	});

	chrome.storage.sync.get({
		rules : 'rules',
	}, function(items) {
		$('input[type=checkbox]').each(function() {
			var isInArray = $.inArray($(this).attr('id'), items['rules']);
			if (isInArray != -1) {
				$(this).prop("checked", true);
			}
		});
	});
}

$(document).ready()
{
	var el = document.getElementById("load_rules");
	var save = document.getElementById("save");

	if (el && save) {
		el.addEventListener("click", getAllRules);
		save.addEventListener("click", save_options);
	}
	document.addEventListener('DOMContentLoaded', restore_options);
};

function saveArrayOfRuleIds(arrayOfRuleNames) {
	var id = [];
	for ( var name in arrayOfRuleNames) {
		var ruleName = arrayOfRuleNames[name];
		var rules = allRules[ruleName];
		for (var i = 0; i < rules.length; i++) {
			id.push(rules[i].id);
		}
	}
	return id;

}

function getAllRules() {
	$.when(rules()).then(function(data, textStatus, jqXHR) {
		restore_options();
		allRules = data;
		addTable(data);
		$("#load_rules").hide();
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
