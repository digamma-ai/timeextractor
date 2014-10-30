var URL = "http://timeext.codeminders.com/timeextractor-2/api/generatekey";
var URL_HTTPS = "https://timeext.codeminders.com:8443/timeextractor-2/api/generatekey";

var METHOD_POST = "POST";
var CONTENT_TYPE = "application/json"
var DATA_TYPE = 'json'

var name_m = '<p class = \"bio\"><font color = \"red\">Field name should contain more than 2 symbols and must contain only letters</font></p>';
var email_m = '<p class = \"bio\"><font color = \"red\">Email is invalid. Valid example: ex. codeminders@codeminders.com</font></p>';

$(document).ready()
{
	$('#name').on('input', function() {
		var name = $(this).val();
		valClassChange($(this), validateName(name), "#valid_name", name_m)
	});

	$('#email').on('input', function() {
		var email = $(this).val();
		valClassChange($(this), validateEmail(email), "#valid_email", email_m)
	});

	$('#register').click(function() {
		register();
	});
}

var register = function() {
	var name = $('#name').val();

	var email = $('#email').val();

	var country = $('#country').find('option:selected').val();
	if (validateName(name) && validateEmail(email)) {
		var json = {
			'name' : name,
			'email' : email,
			'country' : country
		}

		var webUrl = document.location.protocol.indexOf("https:") == 0 ? "https"
				: "http";
		var url = URL;
		if (webUrl == 'https') {
			url = URL_HTTPS;
		}
		serverCall(json, url);
	} else {
		valClassChange($('#name'), validateName(name), "#valid_name", name_m)
		valClassChange($('#email'), validateEmail(email), "#valid_email",
				email_m)
	}
}

var serverCall = function(json, url) {
	return $.ajax({
		type : METHOD_POST,
		url : url,
		data : JSON.stringify(json),
		crossDomain : true,
		contentType : CONTENT_TYPE,
		dataType : DATA_TYPE,
	}).done(function(data) {
		var text = "Your API key is:<br><br>  " + data['key'] + "";
		openPopup(text);
	}).fail(function(xhr, textStatus, jqXHR) {
		if (jqXHR == 'Not Found' || xhr.responseText == '') {
			openPopup('Server is not responding, try again later');
		} else {
			openPopup(xhr.responseText);
		}
	});
}

var validateName = function(name) {
	if (name.length < 3) {
		return false;
	}
	var pattern = /^[A-z ]+$/;
	var is_name = pattern.test("" + name);
	if (is_name) {
		return true;
	}
	return false;
}

var validateEmail = function(email) {
	var pattern = /^([a-z0-9_\.-])+@[a-z0-9-]+\.([a-z]{2,4}\.)?[a-z]{2,4}$/i;
	var is_email = pattern.test(email);
	if (is_email) {
		return true;
	}
	return false;

}

var valClassChange = function(element, validationResult,
		validationMessageElement, message) {
	if (validationResult) {
		element.removeClass("invalid");
		$(validationMessageElement).html('');
	} else {
		element.addClass("invalid");
		$(validationMessageElement).html(message);

	}
}