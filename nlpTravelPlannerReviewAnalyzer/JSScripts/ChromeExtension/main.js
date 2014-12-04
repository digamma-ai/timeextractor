var TEMPORAL_EXTRACTION_URL_HTTP = "http://timeext.codeminders.com/timeextractor-2/api/annotate";
var TEMPORAL_EXTRACTION_URL_HTTPS = "https://timeext.codeminders.com:8443/timeextractor-2/api/annotate";

var METHOD_POST = "POST";
var CONTENT_TYPE = "application/json"
var DATA_TYPE = 'json'
var TEMPORAL_ID = "_temporal_id";
var highlighted = false;

chrome.runtime.onMessage.addListener(function(request, sender) {
	if (!highlighted) {
		chrome.storage.sync.get({
			'connectionType' : 'http',
			'ruleIds' : '',
			'only_latest_dates' : ''
		}, function(obj) {
			var connectionType = obj['connectionType'];
			var rulesToIgnore = obj['ruleIds'];
			var onlyLatestDays = obj['only_latest_dates'];
			var url = serverURL(connectionType);
			start(url, rulesToIgnore, onlyLatestDays);
		});
	} else {
		start(null, null);
	}
});

$(document).ready()
{
	var imgURL = chrome.extension.getURL("images/loading.gif");
	addGlobalStyle('.highlight { background-color: yellow  }');
	addGlobalStyle(".loader_nlp_ext {   position: fixed; display: none;     left: 0px;      top: 0px;       width: 100%;    height: 100%;   z-index: 9999; background:url('"
			+ imgURL + "') 50% 50% no-repeat rgb(249,249,249) }");
	alert_error();
}

var start = function(url, rulesToIgnore, onlyLatestDays) {
	var all_tags = $("*");
	for (var i = 0; i < all_tags.length; i++) {
		$(all_tags[i]).attr(TEMPORAL_ID, TEMPORAL_ID + i);
	}
	if (highlighted) {
		location.reload(false);
		return;
	}
	var webUrl = document.location.protocol.indexOf("https:") == 0 ? "https"
			: "http";
	var currentUrl = url.indexOf("https://") == 0 ? "https" : "http";

	if (webUrl == 'https' && currentUrl == 'http') {
		message = "You should change in extension 'options' connection type for HTTPS.";
		openPopup(message);
		return;
	}

	$('body').prepend('<div class="loader_nlp_ext"></div>');
	$(".loader_nlp_ext").show();
	var currentdate = new Date();
	var offset = new Date().getTimezoneOffset();
	var html = $("html").html();
	var json_to_get_temporal = [ {
		'id' : '1',
		'key' : '6d9aea0514fcf1766f31fff6672fd3e12239b25246c83e9669b13a4a98a93588',
		'email' : 'timeextractor@gmail.com',
		'html' : html,
		'timezone_offset' : offset,
		date : currentdate,
		rules_to_ignore : rulesToIgnore,
		only_latest_dates : onlyLatestDays
	} ];
	$.when(temporalData(json_to_get_temporal, url)).then(
			function(data, textStatus, jqXHR) {
				highlighted = true;
				highlight(html, data);
				$(".loader_nlp_ext").fadeOut("slow");

			}).fail(function(xhr, textStatus, jqXHR) {
		$(".loader_nlp_ext").fadeOut("slow");

		if (jqXHR == 'Not Found' || xhr.responseText == '') {
			openPopup('Server is not responding, try again later');
		} else {
			openPopup(xhr.responseText);
		}
	});
	;
};

// get temporal data from text service

var temporalData = function(json, url) {
	return $.ajax({
		type : METHOD_POST,
		url : url,
		data : JSON.stringify(json),
		crossDomain : true,
		contentType : CONTENT_TYPE,
		dataType : DATA_TYPE,
	});
}

// server URL depends on connectionType

var serverURL = function(connectionType) {
	switch (connectionType) {
	case "https":
		return TEMPORAL_EXTRACTION_URL_HTTPS;
	default:
		return TEMPORAL_EXTRACTION_URL_HTTP;
	}

}

// function to highlight text on html page from position

var highlight = function(html, data) {
	// iterate through object
	var tags = [];
	var selected = [];

	for ( var property in data) {
		current_tag = data[property];
		for (var j = 0; j < current_tag.length; j++) {
			var myDiv = $('[' + TEMPORAL_ID + '='
					+ current_tag[j]['temporalId'] + ']');
			// clear from <script> tags
			var myDiv = $(myDiv).html(
					$(myDiv).clone().find("script,noscript,style").remove()
							.end().html());
			// get text
			var text = getText(myDiv);
			text = $.trim(text.replace(/\s+/g, " "));
			var temporal = (text.substring(current_tag[j].from,
					current_tag[j].to));
			generatedGCUrl = generateGCUrl(current_tag[j].extractedTemporal);

			select = {
				"temporal" : temporal,
				"myDiv" : myDiv,
				"current_tag" : current_tag[j],
				"gcUrl" : generatedGCUrl
			}

			selected.push(select);
		}

		for (var i = 0; i < selected.length; i++) {
			replace(selected[i].myDiv, selected[i].temporal,
					selected[i].current_tag, selected[i].gcUrl);
		}
	}
}

var unhighlight = function(html) {
	highlighted = false;
	$('body').html(old_html);

}

function getText(elems) {
	var extractedText = extractTextWithWhitespaceWorker(elems);
	return extractedText;
}

function extractTextWithWhitespaceWorker(elems) {
	var ret = "";
	var elem;

	for (var i = 0; elems[i]; i++) {
		elem = elems[i];

		if (elem.nodeType === 3 // text node
				|| elem.nodeType === 4) // CDATA node
		{
			ret += elem.nodeValue;
		}

		if (elem.nodeName === "DIV" || elem.nodeName === "UL"
				|| elem.nodeName === "LI" || elem.nodeName === "P") {
			ret += "\n";
		}

		if (elem.nodeType !== 8) // comment node
		{
			ret += getText(elem.childNodes);
		}
	}

	return ret;
}

/* replace text in tag function */

var replace = function(tag, temporal, current_tag, gcUrl) {
	if (current_tag['temporalType'] == 'TIME_DATE'
			|| current_tag['temporalType'] == 'DATE_INTERVAL'
			|| current_tag['temporalType'] == 'SET') {
		$(tag).replaceText(temporal,
				replaceAString(gcUrl, current_tag, temporal));
	} else {
		$(tag)
				.replaceText(temporal,
						replaceSimpleString(current_tag, temporal));
	}

}

var replaceSimpleString = function(current_tag, temporal) {
	return "<span data-tooltip aria-haspopup=\"true\" class=\"has-tip highlight\" title=\""
			+ JSON.stringify(current_tag.extractedTemporal).replace(/"/g, '\'')
			+ " "
			+ "locale: "
			+ JSON.stringify(current_tag.locale).replace(/"/g, '\'')
			+ " "
			+ "confidence: "
			+ JSON.stringify(current_tag.confidence)
			+ "\">"
			+ temporal + "</span>";
}

var replaceAString = function(gcUrl, current_tag, temporal) {
	return "<a href = \""
			+ gcUrl
			+ "\" target = \"_blank\" ><span data-tooltip aria-haspopup=\"true\" class=\"has-tip highlight\" title=\""
			+ JSON.stringify(current_tag.extractedTemporal).replace(/"/g, '\'')
			+ " " + "locale: "
			+ JSON.stringify(current_tag.locale).replace(/"/g, '\'') + " "
			+ "confidence: " + JSON.stringify(current_tag.confidence) + "\">"
			+ temporal + "</span></a>";
}

// generate google calendar URL

var generateGCUrl = function(extractedTemporal) {
	return "http://www.google.com/calendar/event?action=TEMPLATE&dates="
			+ generateDatesPartOfGCString(extractedTemporal);
}

var generateDatesPartOfGCString = function(extractedTemporal) {
	var obj = extractedTemporal[0];
	var dates = '';
	var startDateTime = obj['startDateTime'];
	var endDateTime = obj['endDateTime'];
	dates = generateDate(startDateTime) + "T" + generateTime(startDateTime)
			+ "/" + generateDate(endDateTime) + "T" + generateTime(endDateTime);
	return dates;
}

var generateDate = function(dateTime) {
	var d = new Date();
	var year = d.getFullYear().toString();
	var month = (d.getMonth() + 1).toString();
	var day = d.getDate().toString();

	if (dateTime != null && dateTime.hasOwnProperty('date')) {
		var date = dateTime['date'];
		if (date != null) {
			if (date['year'] != null && date['year'] != 0) {
				year = date['year'].toString();

			}
			if (date['month'] != null && date['month'] != 0) {
				month = date['month'].toString();

			}
			if (date['day'] != null && date['day'] != 0) {
				day = date['day'].toString();

			}
		}
	}
	month = month.replace(/\b(\d{1})\b/g, '0$1');
	day = day.replace(/\b(\d{1})\b/g, '0$1');
	return "" + year + month + day;
}

var generateTime = function(dateTime) {
	var hours = '00';
	var minutes = '00';
	var seconds = '00';
	var timezone = 'Z';

	if (dateTime != null && dateTime.hasOwnProperty('time')) {
		var time = dateTime['time'];
		if (time != null) {
			if (time['hours'] != null) {
				hours = time['hours'].toString();
				hours = hours.replace(/\b(\d{1})\b/g, '0$1');
			}
			if (time['minutes'] != null) {
				minutes = time['minutes'].toString();
				minutes = minutes.replace(/\b(\d{1})\b/g, '0$1');

			}

		}
	}
	return "" + hours + minutes + seconds + timezone;

}
// add custom css style to page
function addGlobalStyle(css) {
	var head, style;
	head = document.getElementsByTagName('head')[0];
	if (!head) {
		return;
	}
	style = document.createElement('style');
	style.type = 'text/css';
	style.innerHTML = css;
	head.appendChild(style);
}
