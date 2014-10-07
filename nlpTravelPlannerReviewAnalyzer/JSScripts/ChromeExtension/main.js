// local var TEMPORAL_EXTRACTION_SERVICE_URL = "http://localhost:8080/timeextractor/api/annotate"

var TEMPORAL_EXTRACTION_URL = "http://localhost:8080/timeextractor/";
var TEMPORAL_EXTRACTION_SERVICE_URL = TEMPORAL_EXTRACTION_URL + "api/annotate"
var LOADING_BAR_IMAGE_URL = TEMPORAL_EXTRACTION_URL + "images/loading.gif";
var METHOD_POST = "POST";
var CONTENT_TYPE = "application/json"
var DATA_TYPE = 'json'
var TEMPORAL_ID = "_temporal_id";

chrome.runtime.onMessage.addListener(function(request, sender) {
	start();
});

var start = function() {
	// added styles for loader and highlight
	// addGlobalStyle('.loader { position: fixed; left: 0px; top: 0px; width:
	// 100%; height: 100%; z-index: 9999; background:
	// url('+LOADING_BAR_IMAGE_URL+') 50% 50% no-repeat rgb(249,249,249) }');
	addGlobalStyle('.highlight { background-color: yellow  }');

	var all_tags = $("*");
	for (var i = 0; i < all_tags.length; i++) {
		$(all_tags[i]).attr(TEMPORAL_ID, TEMPORAL_ID + i);
	}

	var html = $("html").html();
	// $('body').prepend('<div class="loader"></div>');
	// wait until text is cleaned
	var json_to_get_temporal = [ {
		'id' : '1',
		'html' : html,
		date : "2014-07-27"
	} ];
	$.when(temporalData(json_to_get_temporal)).then(
			function(data, textStatus, jqXHR) {
				highlight(html, data);
				// return to the top of page
				// window.scroll(0, 0);
				// remove loader
				// $(".loader").fadeOut("slow");
			}).fail(function(data, textStatus, jqXHR) {
		alert("An error occured on server: " + jqXHR);
		// $(".loader").fadeOut("slow");
	});
	;
};

// get temporal data from text service

var temporalData = function(json) {
	return $.ajax({
		type : METHOD_POST,
		url : TEMPORAL_EXTRACTION_SERVICE_URL,
		data : JSON.stringify(json),
		crossDomain : true,
		contentType : CONTENT_TYPE,
		dataType : DATA_TYPE,
	});
}

// function to highlight text on html page from position

var highlight = function(html, data) {
	// iterate through object
	var tags = [];
	var selected = [];
	for ( var property in data) {
		current_tag = data[property];
		if (current_tag.length == 1) {
			var myDiv = $('[' + TEMPORAL_ID + '='
					+ current_tag[0]['temporalId'] + ']');
			var text = $(myDiv).text().trim();
			text = text.replace(/\s+/g, " ");
			var temporal = (text.substring(current_tag[0].from,
					current_tag[0].to));
			replace(myDiv, temporal, current_tag)
		} else {
			for (var j = 0; j < current_tag.length; j++) {
				var myDiv = $('[' + TEMPORAL_ID + '='
						+ current_tag[j]['temporalId'] + ']');
				var text = $(myDiv).text().trim();
				text = text.replace(/\s+/g, " ");
				var temporal = (text.substring(current_tag[j].from,
						current_tag[j].to));
				replace(myDiv, temporal, current_tag)
			}
		}
	}
}

var replace = function(tag, temporal, current_tag) {
	console.log(tag);
	console.log(current_tag);
	$(tag)
			.replaceText(
					temporal,
					"<span data-tooltip aria-haspopup=\"true\" class=\"has-tip highlight\" title=\""
							+ JSON.stringify(current_tag[0].extractedTemporal)
									.replace(/"/g, '\'')
							+ " "
							+ "locale: "
							+ JSON.stringify(current_tag[0].locale).replace(
									/"/g, '\'')
							+ " "
							+ "confidence: "
							+ JSON.stringify(current_tag[0].confidence)
							+ "\">"
							+ temporal + "</span>");

}

jQuery.fn.textWalk = function(fn) {
	this.contents().each(jwalk);

	function jwalk() {
		var nn = this.nodeName.toLowerCase();
		if (nn === '#text') {
			fn.call(this);
		} else if (this.nodeType === 1 && this.childNodes && this.childNodes[0]
				&& nn !== 'textarea') {
			$(this).contents().each(jwalk);
		}
	}
	return this;
};

String.prototype.replaceAll = function(search, replace) {
	return this.split(search).join(replace);
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
