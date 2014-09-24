var TEMPORAL_EXTRACTION_SERVICE_URL = "http://ec2-54-81-15-231.compute-1.amazonaws.com:8080/timeextractor-0.0.2/api/annotate"
var METHOD_POST = "POST";
var CONTENT_TYPE = "application/json"
var DATA_TYPE = 'json'

	$(document)
		.ready(
				function() {
					// added styles for loader and highlight   
					addGlobalStyle('.loader {   position: fixed;        left: 0px;      top: 0px;       width: 100%;    height: 100%;   z-index: 9999;  background: url(http://www.ooyyo.bg/images/loading.gif) 50% 50% no-repeat rgb(249,249,249) }');
					addGlobalStyle('.highlight { background-color: yellow  }');
					
					var html = $("html").html();
					$('body').prepend('<div class="loader"></div>');
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
								window.scroll(0, 0);
								// remove loader
								$(".loader").fadeOut("slow");
							}).fail(function(data, textStatus, jqXHR) {
								 alert("An error occured on server: " + jqXHR);
								 $(".loader").fadeOut("slow");
							});;
				});

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
	//iterate through object
	var tags = [];
	var selected = [];
	for ( var property in data) {
		current_tag = data[property];
		if (current_tag.length == 1) {
			var base_tag = $(html.substring(current_tag[0].htmlTagFrom,
					current_tag[0].htmlTagTo));
			var text = $.trim($(base_tag).clone().children().remove().end()
					.text());
			text = text.replace(/\s+/g, " ");
			var temporal = (text.substring(current_tag[0].from,
					current_tag[0].to));
			var tag = $(current_tag[0].tag).filter(
					function() {
						return $(this).text().toLowerCase() === $(base_tag)
								.text().toLowerCase();
					})
			var result = {
				'tag' : tag,
				'temporal' : temporal,
				'extractedTemporal' : current_tag[0].extractedTemporal
			};
			tags.push(result);
		} else {
			for (var j = 0; j < current_tag.length; j++) {
				var base_tag = $(html.substring(current_tag[j].htmlTagFrom,
						current_tag[j].htmlTagTo));
				var text = $.trim($(base_tag).clone().children().remove().end()
						.text());
				text = text.replace(/\s+/g, " ");
				var tag = $(current_tag[0].tag).filter(
						function() {
							return $(this).text().toLowerCase() === $(base_tag)
									.text().toLowerCase();
						})
				var temporal = (text.substring(current_tag[j].from,
						current_tag[j].to));
				var result = {
					'tag' : tag,
					'temporal' : temporal,
					'extractedTemporal' : current_tag[j].extractedTemporal
				};
				tags.push(result);
			}
		}
	}

	for (var j = 0; j < tags.length; j++) {
		$(tags[j].tag)
				.html(
						function(i, v) {
							return v
									.replace(
											tags[j].temporal,
											"<span data-tooltip aria-haspopup=\"true\" class=\"has-tip highlight\" title=\""
													+ JSON
															.stringify(
																	tags[j].extractedTemporal)
															.replace(/"/g, '\'')
													+ "\">"
													+ tags[j].temporal
													+ "</span>");
						});

	}
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