var alert_error = function() {
	$('body')
			.prepend(
					"	<div id=\"light\" class=\"white_content\">			</div>	<div id=\"fade\" class=\"black_overlay\"></div>	");
	addGlobalStyle(".black_overlay{        display: none;         position: fixed;        left: 0px;      top: 0px;       width: 100%;    height: 100%;      background-color: black;        z-index:1001;        -moz-opacity: 0.8;        opacity:.80;        filter: alpha(opacity=80);    }");
	addGlobalStyle("  .white_content {        display: none;         position: fixed;          top: 25%;        left: 25%;      width: 40%;        height: 40%;        padding: 16px;        border: 16px solid orange;        background-color: white;        z-index:1002;        overflow: auto;    }");
}

var openPopup = function(text) {

	document.getElementById('light').style.display = 'block';
	document.getElementById('fade').style.display = 'block';

	var closeButton = "<div align = \"right\"><a href=\"#\" id=\"close\">Close</a></div>";
	var optionsOpen = "<a href=\"#\" id=\"options\">Open options</a>";

	$("#light").html(
			closeButton + '<br>' + "<p class = \"bio\"><h2>" + text
					+ "</h2></p>" + "<br>" + optionsOpen);

	$("#close").click(function() {
		document.getElementById('light').style.display = 'none';
		document.getElementById('fade').style.display = 'none';
	});

	$("#options").click(function() {
		openOptions();
	});
}

var openOptions = function() {
	chrome.runtime.sendMessage({
		options : "options"
	}, function(response) {

	});
}