var openPopup = function(text) {

	document.getElementById('light1').style.display = 'block';
	document.getElementById('fade1').style.display = 'block';

	var closeButton = "<div align = \"right\"><a href=\"#\" id=\"close\">Close</a></div>";

	$("#light1").html(
			closeButton + '<br>' + "<p class = \"bio\"><h2>" + text
					+ "<br /> <br />");

	$("#close").click(function() {
		document.getElementById('light1').style.display = 'none';
		document.getElementById('fade1').style.display = 'none';
	});

}