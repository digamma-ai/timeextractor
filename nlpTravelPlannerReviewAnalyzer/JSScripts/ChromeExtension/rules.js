var TEMPORAL_EXTRACTION_URL = "http://timeext.codeminders.com:8080/timeextractor-2/api/rules";
var METHOD_GET = "GET";
var CONTENT_TYPE = "application/json"
var DATA_TYPE = 'json'
var allRules;
// Saves options to chrome.storage
function save_options() {
	var arrayOfRulesToBeIgnored = [];
	var connectionType = document.getElementById('connection').value;
	if (connectionType == 'https') {
		open();
	}
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

var checkAll = function() {
	$('#select_all').click(function() {
		$('body').find(':checkbox').prop('checked', true);
	});
}

var uncheckAll = function() {
	$('#unselect_all').click(function() {
		$('body').find(':checkbox').prop('checked', false);

	});
}

var close = function() {
	$("#close").click(function() {
		document.getElementById('light').style.display = 'none';
		document.getElementById('fade').style.display = 'none';
	});
}

var open = function() {

	document.getElementById('light').style.display = 'block';
	document.getElementById('fade').style.display = 'block';

}

$(document).ready()
{

	var el = document.getElementById("load_rules");
	var save = document.getElementById("save");

	if (el && save && close) {
		el.addEventListener("click", getAllRules);
		save.addEventListener("click", save_options);
	}
	document.addEventListener('DOMContentLoaded', restore_options);
	close();
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
		checkAll();
		uncheckAll();
	}).fail(function(data, textStatus, jqXHR) {
		alert("An error occured on server: " + jqXHR);
	});
}

// get rule ids
var rules = function() {
	return $.ajax({
		type : METHOD_GET,
		url : TEMPORAL_EXTRACTION_URL,
		crossDomain : true,
		contentType : CONTENT_TYPE,
		dataType : DATA_TYPE,
	});
}

function addTable(rules) {
	var html = '';
	html += "<p class =\"bio\"><a href = \"#\" id = \"select_all\">Check all</a>"
	html += "  <a href = \"#\" id = \"unselect_all\">Uncheck all</a></p>"
	html += "<table class = \"reference\"><tbody>";
	for ( var prop in rules) {
		html += '<tr>';
		html += '<td>' + "<input type='checkbox' id=\"" + prop
				+ "\" class=\"checkbox\" ></input>" + '</td>';
		html += '<td><b>' + rule_group_name(prop)['type'] + '</b></td>';
		html += '<td>' + rule_group_name(prop)['example'] + '</td>';
		html += "</tr>";
	}
	html += "</tbody></table>"

	$(html).appendTo('#rules');

}