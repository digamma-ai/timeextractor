chrome.browserAction.onClicked.addListener(function(tab) {
	chrome.tabs.getSelected(null, function(tab) {
		chrome.tabs.sendMessage(tab.id, {
			message : "message"
		});
	});
});
chrome.contextMenus.create({
	'id' : 'contextFalseNegative',
	'enabled' : true,
	'title' : 'Report for not found time extraction',
	"contexts" : [ "selection" ]
});
chrome.contextMenus.create({
	'id' : 'contextFalsePositive',
	'enabled' : true,
	'title' : 'Report for wrong time extraction',
	"contexts" : [ "selection" ]
});

chrome.contextMenus.onClicked.addListener(onClickHandler);

// The onClicked callback function.
function onClickHandler(info, tab) {
	text = info.selectionText;
	var fromUrl = tab.url;
	id = info.menuItemId;
	if (id == 'contextFalsePositive') {
		report(fromUrl, text, 'false positive');
	} else {
		report(fromUrl, text, 'false negative');
	}

};

function report(url, text, type) {
	$
			.ajax({
				url : "https://docs.google.com/a/codeminders.com/forms/d/1HF2EWvocOX_zaXf7Dc1YbR7xpxpGHjfTg0oWp6r7XcE/formResponse",
				data : {
					"entry.1311304202" : url,
					"entry.1451398082" : text,
					"entry.1551453566" : type
				},
				type : "POST",
				dataType : "xml",
				crossDomain : true,
				statusCode : {
					0 : function() {
						alert('Server is not responding, try again later');
					},
					200 : function() {
						alert('Thank you for submitting');

					}
				}
			});
}
